package states.subscriber;
import states.subscriber.IState;
import events.AbstractEvent;

/**
 * @author mmurad7
 */
public abstract class AbstractState implements IState {

	protected IState state = null;
	/**
	 * @param event an event which is published
	 * @param channelName the channel that published the event
	 * 
	 */
	public void handleEvent(AbstractEvent event, String channelName) {};
		
}
