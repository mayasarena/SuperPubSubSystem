package states.subscriber;


/**
 * @author kkontog, ktsiouni, mgrigori
 * the base Interface for the Subscriber hierarchy
 */
public abstract class AbstractState implements IState {
	
	private String stateName;
	
	/**
	 * Default Constructor for the abstract class AbstractState
	 * @param stateName must be a valid state
	 */
	public AbstractState(String stateName) {
		this.stateName = stateName;
	}
	
	protected String getStateName() {
		return this.stateName;
	}
	
}
