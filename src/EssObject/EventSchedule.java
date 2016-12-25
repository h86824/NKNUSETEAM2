package EssObject;

import java.util.TreeSet;

public class EventSchedule implements Comparable<EventSchedule>{
	private String name;
	private String project;
	private TreeSet<Event> events;
	
	public EventSchedule(String name ,String project){
		this.name = name;
		this.project = project;
		events = new TreeSet<Event>();
	}
	
	public void addEvent(Event event){
		events.add(event);
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
	
	public void setProject(String project){
		this.project = project;
	}
	
	public String getProject(){
		return project;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public TreeSet<Event> getEvents(){
		return events;
	}

	@Override
	public int compareTo(EventSchedule o) {
		if(this.project.equals(o.project))
			this.project.compareTo(o.project);
		return this.name.compareTo(o.name);
	}
	
	
}