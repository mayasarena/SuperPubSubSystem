package events;


/**
 * @author kkontog, ktsiouni, mgrigori
 * the enumeration of available options that can be used to create
 * an Event using the {@link EventFactory#createEvent(EventType, int, EventMessage)} 
 * method
 * 
 * for every new @link {@link AbstractEvent} subclass created, a new entry should be added here
 *  
 */
public enum EventType {
	News, Fictional, Scholarly
}
//let news have event id 0
//let fictional have event id 1
//let scholarly have event id 2