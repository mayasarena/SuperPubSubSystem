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

public class TitleStrategy implements IStrategy {
	
	private String channel;
	
	//unsure how to implement
	@Override
	public void doPublish(int publisherId) {
		EventMessage msg = new EventMessage("default", "default");
		AbstractEvent event = EventFactory.createEvent("Scholarly", publisherId, msg);
		
		List<String> ChannelPost = new ArrayList();
		ChannelPost.add("cars");
		ChannelPost.add("planes");
		ChannelEventDispatcher.getInstance().postEvent(event, ChannelPost);
		System.out.println("publisher " + publisherId + " publishes event" + event + "by Default strategy");
	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		List<String> ChannelPost = new ArrayList();
		ChannelPost.add("cars");
		ChannelPost.add("planes");
		ChannelEventDispatcher.getInstance().postEvent(event, ChannelPost);
		

		System.out.println("publisher " + publisherId + " publishes event " + event + " by Default strategy");
	}
	//see body & event type strategy for comments
	public String chooseChannel(String title) {
		
		StringTokenizer token = new StringTokenizer(title);
		
		while(token.hasMoreTokens()) {
			String word = token.nextToken();
			
			switch(word) {
				case "food":
					return "food";
				case "snack":
					return "snack";
				case "cars":
					return "cars";
				case "planes":
					return "planes";
			}
		}
		return "general";
	}
	
	public String getChannel() {
		return this.channel;
	}
	
	
}
