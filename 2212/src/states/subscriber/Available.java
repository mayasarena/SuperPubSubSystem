package states.subscriber;

import events.AbstractEvent;

public class Available implements IState {

	@Override
	public void handleEvent(AbstractEvent event, String channelName) {
		System.out.println("Subscriber handles event" + event.getEventType() + 
				"published on channel" + channelName + 
				"at an available state by reading the event.");
	}

}
