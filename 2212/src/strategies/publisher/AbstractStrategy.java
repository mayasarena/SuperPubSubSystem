package strategies.publisher;


/**
 * @author kkontog, ktsiouni, mgrigori
 * the base Interface for the Subscriber hierarchy
 */
public abstract class AbstractStrategy implements IStrategy {
	
	private String stateName;
	
	/**
	 * Default Constructor for the abstract class AbstractState
	 * @param stateName must be a valid state
	 */
	public AbstractStrategy(String stateName) {
		this.stateName = stateName;
	}
	
	protected String getStateName() {
		return this.stateName;
	}
	
}
