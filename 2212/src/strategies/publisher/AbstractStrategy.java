package strategies.publisher;


/**
 * @author kkontog, ktsiouni, mgrigori
 * the base Interface for the Subscriber hierarchy
 */
public abstract class AbstractStrategy implements IStrategy {
	
	private String stateName;
	//private String channel;
	
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
	
	//methods I created, i think this is the right way to do it instead of
	//making a string called channel in each strategy class?
	//couldn't figure out how to call getChannel for the individual strategy within its own class
	/*
	public String getChannel() {
		return channel;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	*/
}
