package orchestration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import events.AbstractEvent;
import events.EventFactory;
import events.EventMessage;
import events.EventType;
import pubSubServer.AbstractChannel;
import pubSubServer.ChannelAccessControl;
import pubSubServer.ChannelDiscovery;
import pubSubServer.SubscriptionManager;
import publishers.AbstractPublisher;
import publishers.PublisherFactory;
import publishers.PublisherType;
import states.subscriber.StateName;
import strategies.publisher.StrategyName;
import subscribers.AbstractSubscriber;
import subscribers.SubscriberFactory;
import subscribers.SubscriberType;

public class Orchestration {
	//private static ChannelAccessControl cac;

	public static void main(String[] args) {

		List<AbstractPublisher> listOfPublishers = new ArrayList<>();
		List<AbstractSubscriber> listOfSubscribers = new ArrayList<>();
		Orchestration testHarness = new Orchestration();
		try {
			listOfPublishers = testHarness.createPublishers();
			listOfSubscribers = testHarness.createSubscribers();

			List<AbstractChannel> channels = ChannelDiscovery.getInstance().listChannels();
			// For demonstration purposes only
			try {
				BufferedReader initialChannels = new BufferedReader(new FileReader(new File("Channels.chl")));
				List<String> channelList = new ArrayList<String>();
				String line = "";
				while ((line = initialChannels.readLine()) != null)
					channelList.add(line);
				int subscriberIndex = 0;
				for (AbstractSubscriber subscriber : listOfSubscribers) {
					subscriber.subscribe(channelList.get(subscriberIndex % channelList.size()));
					subscriberIndex++;
				}
				initialChannels.close();
			} catch (IOException ioe) {
				System.out.println("Loading Channels from file failed proceeding with random selection");
				for (AbstractSubscriber subscriber : listOfSubscribers) {
					int index = (int) Math.round((Math.random() * 10)) / 3;
					SubscriptionManager.getInstance().subscribe(channels.get(index).getChannelTopic(), subscriber);
				}
			}
			/*for (AbstractPublisher publisher : listOfPublishers) {
				publisher.publish();
			}*/

			BufferedReader commandInput = new BufferedReader(new FileReader(new File("input.txt")));
			String line = "";
			while ((line = commandInput.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line);
				String cmd = st.nextToken();
				if (cmd.equals("PUB")) {
					int pubId = Integer.parseInt(st.nextToken());
					String eventType = st.nextToken();
					String messageHead = st.nextToken();
					String payload = st.nextToken();
					EventMessage message = new EventMessage(messageHead, payload);
					AbstractEvent toPub = EventFactory.createEvent(eventType, pubId, message);
					listOfPublishers.get(pubId).publish(toPub);
				}
				if (cmd.equals("SUB")) {
					int subId = Integer.parseInt(st.nextToken());
					String channelName = st.nextToken();
					listOfSubscribers.get(subId).subscribe(channelName);

				}
				if (cmd.equals("BLOCK")) {
					int blockId = Integer.parseInt(st.nextToken());
					String channelName = st.nextToken();
					ChannelAccessControl.getInstance().blockSubcriber(listOfSubscribers.get(blockId), channelName);
				}
				if (cmd.equals("UNBLOCK")) {
					int blockId = Integer.parseInt(st.nextToken());
					String channelName = st.nextToken();
					ChannelAccessControl.getInstance().unBlockSubscriber(listOfSubscribers.get(blockId), channelName);
				}
			}
			commandInput.close();

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			System.out.println("Will now terminate");
			return;
		}
		/*for (AbstractPublisher publisher : listOfPublishers) {
			publisher.publish();
		}*/

	}

	private List<AbstractPublisher> createPublishers() throws IOException {
		List<AbstractPublisher> listOfPublishers = new ArrayList<>();
		AbstractPublisher newPub;
		BufferedReader StrategyBufferedReader = new BufferedReader(new FileReader(new File("Strategies.str")));
		while (StrategyBufferedReader.ready()) {
			String PublisherConfigLine = StrategyBufferedReader.readLine();
			String[] PublisherConfigArray = PublisherConfigLine.split("\t");
			int[] PublisherConfigIntArray = new int[2];
			for (int i = 0; i < PublisherConfigArray.length; i++)
				PublisherConfigIntArray[i] = Integer.parseInt(PublisherConfigArray[i]);
			newPub = PublisherFactory.createPublisher(PublisherType.values()[PublisherConfigIntArray[0]],
					StrategyName.values()[PublisherConfigIntArray[1]]);
			listOfPublishers.add(newPub);
		}
		StrategyBufferedReader.close();
		return listOfPublishers;
	}

	private List<AbstractSubscriber> createSubscribers() throws IOException {
		List<AbstractSubscriber> listOfSubscribers = new ArrayList<>();
		AbstractSubscriber newSub;
		BufferedReader StateBufferedReader = new BufferedReader(new FileReader(new File("States.sts")));
		while (StateBufferedReader.ready()) {
			String StateConfigLine = StateBufferedReader.readLine();
			String[] StateConfigArray = StateConfigLine.split("\t");
			int[] StateConfigIntArray = new int[2];
			for (int i = 0; i < StateConfigArray.length; i++)
				StateConfigIntArray[i] = Integer.parseInt(StateConfigArray[i]);
			newSub = SubscriberFactory.createSubscriber(SubscriberType.values()[StateConfigIntArray[0]],
					StateName.values()[StateConfigIntArray[1]]);
			listOfSubscribers.add(newSub);
		}
		StateBufferedReader.close();
		return listOfSubscribers;
	}

}
