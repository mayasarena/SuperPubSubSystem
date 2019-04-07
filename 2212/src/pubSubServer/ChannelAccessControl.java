
package pubSubServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import subscribers.AbstractSubscriber;


/**
 * @author kkontog, ktsiouni, mgrigori
 * MUST Implements the Singleton design pattern
 * Class that acts as an access control module that allows for the 
 * blocking and unblocking of specific subscribers for specific channels
 * 
 */
public class ChannelAccessControl {
	
	//start - edit zach
	//static variable instance of type ChannelAccessControl
	private static ChannelAccessControl instance = null;
	
	//changed to static
	private static Map<String, List<AbstractSubscriber>> blackList = new HashMap<>(); //A list of subscribers who have been blocked
	
	//private constructor restricted to this class
	private ChannelAccessControl() {
	}
	
	//static method to create an instance of a ChannelAccessControl class
	public static ChannelAccessControl getInstance() {
		//To ensure one instance is created
		if (instance == null)
			instance = new ChannelAccessControl();
		return instance;
	}
	// end
	
	/**
	 * 
	 * blocks the provided subscriber from accessing the designated channel
	 * @param subscriber an instance of any implementation of {@link AbstractSubscriber}
	 * @param channelName a String value representing a valid channel name
	 */
	public void blockSubcriber(AbstractSubscriber subscriber, String channelName) {
		
		List<AbstractSubscriber> blockedSubscribers = blackList.getOrDefault(channelName, new ArrayList<AbstractSubscriber>());
		blockedSubscribers.add(subscriber);
		blackList.put(channelName, blockedSubscribers);
		System.out.println("Subcriber " + subscriber + " is blocked on channel" + channelName);
	}

	/**
	 * unblocks the provided subscriber from accessing the designated channel
	 * @param subscriber an instance of any implementation of {@link AbstractSubscriber}
	 * @param channelName a String value representing a valid channel name
	 */
	public void unBlockSubscriber(AbstractSubscriber subscriber, String channelName) {
		
		List<AbstractSubscriber> blockedSubscribers;
		if((blockedSubscribers = blackList.get(channelName)) == null)
			return;
		blockedSubscribers.remove(subscriber);
		System.out.println("Subcriber " + subscriber + " is unblocked on channel" + channelName);
	}

	
	/**
	 * checks if the provided subscriber is blocked from accessing the specified channel
	 * @param subscriber an instance of any implementation of {@link AbstractSubscriber}
	 * @param channelName a String value representing a valid channel name
	 * @return true if blocked false otherwise
	 */
	//changed to public and static
	public static boolean checkIfBlocked(AbstractSubscriber subscriber, String channelName) {
		
		List<AbstractSubscriber> blockedSubscribers;
		if((blockedSubscribers = blackList.get(channelName)) == null)
			return false;
		return (blockedSubscribers.contains(subscriber));
	}
}
