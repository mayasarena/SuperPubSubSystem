package strategies.publisher;

import java.util.ArrayList;
import java.util.List;

import events.AbstractEvent;
import events.EventMessage;
import events.EventType;
import pubSubServer.AbstractChannel;
import pubSubServer.ChannelDiscovery;
import pubSubServer.ChannelEventDispatcher;
import events.EventFactory;
public class GeneralStrategy implements IStrategy {

	@Override
	public void doPublish(int publisherId) {
		EventMessage msg = new EventMessage("default", "default");
		AbstractEvent event = EventFactory.createEvent("Fictional", publisherId, msg);
		List<String> ChannelPost = new ArrayList();
		ChannelPost.add("general");
		ChannelPost.add("main");
		ChannelEventDispatcher.getInstance().postEvent(event, ChannelPost);
		System.out.println("publisher " + publisherId + " publishes event" + event + "by Default strategy");
	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		List<String> ChannelPost = new ArrayList();
		ChannelPost.add("general");
		ChannelPost.add("main");
		ChannelEventDispatcher.getInstance().postEvent(event, ChannelPost);
		

		System.out.println("publisher " + publisherId + " publishes event " + event + " by Default strategy");
	}
}
