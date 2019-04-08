package strategies.publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import events.AbstractEvent;
import events.EventMessage;
import events.EventType;
import pubSubServer.AbstractChannel;
import pubSubServer.ChannelDiscovery;
import pubSubServer.ChannelEventDispatcher;
import events.EventFactory;

public class CookingStrategy implements IStrategy {

	private String channel;
	
 
	@Override
	public void doPublish(int publisherId) {
		EventMessage msg = new EventMessage("default", "default");
		AbstractEvent event = EventFactory.createEvent("News", publisherId, msg);
		
		List<String> ChannelPost = new ArrayList();
		ChannelPost.add("food");
		ChannelPost.add("snacks");
		ChannelEventDispatcher.getInstance().postEvent(event, ChannelPost);
		System.out.println("publisher " + publisherId + " publishes event" + event + "by Default strategy");
	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		List<String> ChannelPost = new ArrayList();
		ChannelPost.add("food");
		ChannelPost.add("snacks");
		ChannelEventDispatcher.getInstance().postEvent(event, ChannelPost);
		

		System.out.println("publisher " + publisherId + " publishes event " + event + " by Default strategy");
	}
	
	public String chooseChannel(String body) {
		//string tokenizer breaks up the input string, body, into individual words
		StringTokenizer token = new StringTokenizer(body);
		//loop while there is another word available 
		while(token.hasMoreTokens()) {
			String word = token.nextToken();
			//switch case for key words, return channel name that fits
			//channel names taken from channel.chl
			switch(word) {
				case "food":
					return "food";
				case "snack":
					return "snack";
				//can add more cases if necessary, will need to add any extra channels to channels.chl	
			}
		}
		//default is food channel
		return "food";
	}
	
	public String getChannel() {
		return this.channel;
	}
	
}
