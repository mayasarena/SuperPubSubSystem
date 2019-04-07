package strategies.publisher;
import java.util.StringTokenizer;
import events.AbstractEvent;

public class BodyStrategy implements IStrategy {

	private String channel;
	
	//unsure how to implement 
	@Override
	public void doPublish(int publisherId) {
		System.out.println("publisher " + publisherId + " uses their strategy to create and publish their event");
	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		channel = chooseChannel(event.getPayload().getBody());
		System.out.println("publisher " + publisherId + " publishes event " + event + " to channel " + channel);
	}
	
	public String chooseChannel(String body) {
		//string tokenizer breaks up the input string, body, into individual words
		StringTokenizer token = new StringTokenizer(body);
		//loop while there is another word available 
		while(token.hasMoreTokens()) {
			String word = token.nextToken();
			//switch case for key words, return channel name that fits
			//channel names taken from channel.chl
			switch(word) {
				case "food":
					return "food";
				case "snack":
					return "snack";
				case "cars":
					return "cars";
				case "planes":
					return "planes";
				//can add more cases if necessary, will need to add any extra channels to channels.chl	
			}
		}
		//default is general channel
		return "general";
	}
	
	public String getChannel() {
		return this.channel;
	}
	
}
