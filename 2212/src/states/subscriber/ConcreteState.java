package states.subscriber;

import events.AbstractEvent;
import states.subscriber.IState;


/**
 * @author mmurad7
 * 
 */
public class ConcreteState extends AbstractState {
	
	/**
	 * @param 
	 */
	protected ConcreteState(IState concreteState) {
		this.state = concreteState;
	}

	/* (non-Javadoc)
	 */
	@Override
	
	public void handleEvent(AbstractEvent event, String channelName) {};

}
