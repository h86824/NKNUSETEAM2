package EssObject;

import java.util.TreeSet;

public class EventSchedule {
	private String name;
	private TreeSet<Event> events;
	
	public EventSchedule(){
		events = new TreeSet<Event>();
	}
	
	public EventSchedule(String name){
		events = new TreeSet<Event>();
	}
	
	public void addEvent(Time time , String site, String teamA, String teamB){
		events.add(new Event(time , site, teamA, teamB));
	}
	
	public void addEvent(int year, int month, int day, int hour, int minute, String site, String teamA, String teamB){
		events.add(new Event(new Time(year , month , day , hour , minute), site ,teamA, teamB));
	}
	
	public boolean removeEvent(int year, int month, int day, int hour, int minute ,String site ,String teamA ,String teamB){
		return events.remove(new Event(year, month, day, hour, minute , site, teamA , teamB));
	}
	
	public boolean removeEvent(Event event){
		return events.remove(event);
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public TreeSet<Event> getEvents(){
		return events;
	}
	
}