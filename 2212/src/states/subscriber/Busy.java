package states.subscriber;

import events.AbstractEvent;

public class Busy implements IState {

	@Override
	public void handleEvent(AbstractEvent event, String channelName) {
		System.out.println(" and handles it at a busy state by saving the event for later.");
	}

}
