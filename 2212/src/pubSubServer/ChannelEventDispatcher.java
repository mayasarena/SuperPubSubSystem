package pubSubServer;

import java.util.List;

import events.AbstractEvent;
import publishers.AbstractPublisher;


/**
 * @author kkontog, ktsiouni, mgrigori
 * MUST IMPLEMENT the Singleton design pattern
 * Class providing an interface for {@link AbstractPublisher} objects to cover their publishing needs 
 */
public class ChannelEventDispatcher {
	
	//change to static?
	private static ChannelEventDispatcher instance = null;
	private ChannelPoolManager cpManager = null;
	
	//static method to create an instance of a ChannelAccessControl class
	public static ChannelEventDispatcher getInstance() {
		//To ensure one instance is created
		if (instance == null)
			instance = new ChannelEventDispatcher();
		return instance;
	}
	
	//private constructor restricted to this class
	private ChannelEventDispatcher() {
		if (cpManager == null)
			cpManager = ChannelPoolManager.getInstance();
	}
	
	
	/**
	 * @param event event to be published
	 * @param listOfChannels list of channel names to which the event must be published to 
	 */
	public void postEvent(AbstractEvent event, List<String> listOfChannels) {
		
		for(String channelName : listOfChannels) {
			AbstractChannel channel = cpManager.findChannel(channelName);
			if(channel == null) {
				channel = ChannelCreator.getInstance().addChannel(channelName);
			}
			
			channel.publishEvent(event);
			System.out.println("channel " + channelName + " has event " + event + " from publisher " + this.hashCode());
		}
	}
	
	
}
