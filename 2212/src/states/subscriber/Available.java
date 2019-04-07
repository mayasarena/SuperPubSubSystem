package states.subscriber;

import events.AbstractEvent;

public class Available implements IState {

	@Override
	public void handleEvent(AbstractEvent event, String channelName) {
		System.out.println(" at an available state by reading the event.");
	}

}
