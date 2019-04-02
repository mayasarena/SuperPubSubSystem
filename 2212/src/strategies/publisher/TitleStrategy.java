package strategies.publisher;

import java.util.StringTokenizer;

import events.AbstractEvent;

public class TitleStrategy implements IStrategy {

	@Override
	public void doPublish(int publisherId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPublish(AbstractEvent event, int publisherId) {
		// TODO Auto-generated method stub

	}

	public static String chooseChannel(String title) {
		
		StringTokenizer token = new StringTokenizer(title);
		
		while(token.hasMoreTokens()) {
			String word = token.nextToken();
			
			switch(word) {
				case "food":
					return "food";
				case "delicious":
					return "food";
				case "yummy":
					return "food";
				case "snack":
					return "snack";
				case "cars":
					return "cars";
				case "driving":
					return "cars";
				case "planes":
					return "planes";
				case "flying":
					return "planes";
			}
		}
		return "general";
	}
	
	
}
