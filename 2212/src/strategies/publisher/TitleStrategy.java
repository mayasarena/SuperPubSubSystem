package strategies.publisher;

import java.util.StringTokenizer;

import events.AbstractEvent;

public class TitleStrategy implements IStrategy {
	
	private String channel;
	
	//unsure how to implement
	@Override
	public void doPublish(int publisherId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		channel = chooseChannel(event.getPayload().getHeader());
		System.out.println("publisher " + publisherId + "publishes event " + event + "to channel " + channel);

	}
	//see body & event type strategy for comments
	public static String chooseChannel(String title) {
		
		StringTokenizer token = new StringTokenizer(title);
		
		while(token.hasMoreTokens()) {
			String word = token.nextToken();
			
			switch(word) {
				case "food":
					return "food";
				case "snack":
					return "snack";
				case "cars":
					return "cars";

				case "planes":
					return "planes";
			}
		}
		return "general";
	}
	
	public String getChannel() {
		return this.channel;
	}
	
	
}
