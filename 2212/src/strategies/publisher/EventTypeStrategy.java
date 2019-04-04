package strategies.publisher;
import events.AbstractEvent;

public class EventTypeStrategy implements IStrategy {

	private String channel;
	
	//when publisher relies on strategy to post the event to a channel
	//not really sure how to fill this in tbh without an event parameter
	@Override
	public void doPublish(int publisherId) {
		

	}
	
	//when publisher relies on strategy to create the event and post to a channel
	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		channel = chooseChannel(event.getEventType());
		System.out.println("publisher " + publisherId + "publishes event " + event + "to channel " + channel);
	}
	
	
	//this method has the same name in every strategy class, will that be an issue?
	public String chooseChannel(long eventType) {
		//based on the eventID we are going to return a channel to post to			
		//eventID 0 = news event, therefore post to news channel
		if (eventType == 0) {
			return "News";
		}
		//1 = fictional event, so post to fictional stories channel
		else if(eventType == 1) {
			return "Fictional Stories";
		}
		//2 = scholarly event, so post to scholarly articles channel
		else if(eventType == 2) {
			return "Scholarly Articles";
		}
		else
			//default is general channel
			return "General";
	}	
	
	//may be needed later
	//output specifications state channel dispatcher should say
	//"Channel x has event y from publisher x"
	public String getChannel() {
		return this.channel;
	}
	//if i have getChannel in every strategy class will they conflict?
}
