package EssObject;

import java.util.TreeSet;

public class EventSchedule {
	TreeSet<Event> events;
	
	public EventSchedule(){
		events = new TreeSet<Event>();
	}
	
	public void addEvent(Time time , Site site, Team teamA, Team teamB){
		events.add(new Event(time , site, teamA, teamB));
	}
	
	public void addEvent(int year, int month, int day, int hour, int minute, Site site, Team teamA, Team teamB){
		events.add(new Event(new Time(year , month , day , hour , minute), site ,teamA, teamB));
	}
	
	public boolean removeEvent(int year, int month, int day, int hour, int minute ,Site site ,Team teamA ,Team teamB){
		return events.remove(new Event(year, month, day, hour, minute , site, teamA , teamB));
	}
	
	public boolean removeEvent(Event event){
		return events.remove(event);
	}
	
	public TreeSet<Event> getEvents(){
		return events;
	}
	
}