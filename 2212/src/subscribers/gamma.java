package subscribers;

import events.AbstractEvent;
import pubSubServer.ChannelAccessControl;
import pubSubServer.SubscriptionManager;
import states.subscriber.StateFactory;
import states.subscriber.StateName;

public class gamma extends AbstractSubscriber{

	protected gamma() {
		state = StateFactory.createState(StateName.defaultState);
	}
	
	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#setState(states.subscriber.StateName)
	 */
	public void setState(StateName stateName) {
		state = StateFactory.createState(stateName);
		System.out.println("Subcriber " + this + " is on state " + stateName);
	}
	
	
	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#alert(events.AbstractEvent, java.lang.String)
	 */
	@Override
	public void alert(AbstractEvent event, String channelName) {
		boolean check;
		check = ChannelAccessControl.checkIfBlocked(this, channelName);
		if(check == false) {
			System.out.println("Subscriber " + this + " receives event " + event + " published on channel " + channelName);
			state.handleEvent(event, channelName);
		}
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#subscribe(java.lang.String)
	 */
	@Override
	public void subscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);	
		System.out.println("Subcriber " + this + " subscribes to channel " + channelName);
	}

	/* (non-Javadoc)
	 * @see subscribers.ISubscriber#unsubscribe(java.lang.String)
	 */
	@Override
	public void unsubscribe(String channelName) {
		SubscriptionManager.getInstance().subscribe(channelName, this);
		System.out.println("Subcriber " + this + " unsubscribes to channel " + channelName);
		
	}

	
}
