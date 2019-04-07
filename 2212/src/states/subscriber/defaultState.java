package states.subscriber;

import events.AbstractEvent;

public class defaultState implements IState {

	@Override
	public void handleEvent(AbstractEvent event, String channelName) {
		System.out.println(" in the default state");
	}

}
