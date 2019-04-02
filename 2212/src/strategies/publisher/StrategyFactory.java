package strategies.publisher;

/**
 * @author kkontog, ktsiouni, mgrigori
 * creates new {@link IStrategy } objects
 * contributes to the Strategy design pattern
 * implements the FactoryMethod design pattern   
 */
public class StrategyFactory {

	
	/**
	 * creates a new {@link IStrategy} using an entry from the {@link StrategyName} enumeration
	 * @param strategyName a value from the {@link StrategyName} enumeration specifying the strategy to be created 
	 * @return the newly created {@link IStrategy} instance 
	 */
	public static IStrategy createStrategy(StrategyName strategyName) {
		IStrategy strategy;
		switch(strategyName) {
			case Title:
				strategy = new TitleStrategy();
				return strategy;
			case Body:
				strategy = new BodyStrategy();
				return strategy;
			default:
				strategy = new EventTypeStrategy();
				return strategy;
		}
	}
	
	
}
