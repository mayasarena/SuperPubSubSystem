package strategies.publisher;

import events.AbstractEvent;

public class GeneralStrategy implements IStrategy {

	@Override
	public void doPublish(int publisherId) {
		System.out.println("publisher " + publisherId + " uses their strategy to create and publish their event");

	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		System.out.println("publisher " + publisherId + " publishes event " + event + " to channel general");
	}
}
