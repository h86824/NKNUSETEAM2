package EssObject;
/**
 * 
 * @author 普皓群
 *
 */
public class Event implements Comparable<Event>{
	private Time time;
	private String site;
	private String teamA;
	private String teamB;
	public Event(int year, int month, int day, int hour,int minute, String site, String teamA ,String teamB){
		time = new Time(year , month , day , hour , minute);
		this.site = site;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	
	public Event(Time time, String site, String teamA, String teamB){
		this.time = time;
		this.site = site;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	
	public void setTime(Time time){
		this.time = time;
	}
	
	public Time getTime(){
		return time;
	}
	
	public void setSite(String site){
		this.site = site;
	}
	
	public String getSite(){
		return site;
	}
	
	public void setTeaA(String teamA){
		this.teamA = teamA;
	}
	
	public String getTeamA(){
		return teamA;
	}
	
	public void setTeaB(String teamB){
		this.teamB = teamB;
	}
	
	public String getTeamB(){
		return teamB;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.getClass() != o.getClass())return false;
		Event event = (Event)o;
		if(this.time.getYear() != event.time.getYear())return false;
		else if(this.time.getMonth() != event.time.getMonth())return false;
		else if(this.time.getDay() != event.time.getDay())return false;
		else if(this.time.getHour() != event.time.getHour())return false;
		else if(this.time.getMinute() != event.time.getMinute())return false;
		else if(this.site.equals(event.site) )return false;
		else if(this.teamA.equals(event.teamA)&&this.teamB.equals(event.teamB))return false;
		else if(this.teamA.equals(event.teamB)&&this.teamB.equals(event.teamA))return false;
		else return true;
	}
	
	@Override
	public int compareTo(Event o) {
		if(this.time.getYear() != o.time.getYear())
			return this.time.getYear() - o.time.getYear();
		else if(this.time.getMonth() != o.time.getMonth())
			return this.time.getMonth() - o.time.getMonth();
		else if(this.time.getDay() != o.time.getDay())
			return this.time.getDay() - o.time.getDay();
		else if(this.time.getHour() != o.time.getHour())
			return this.time.getHour() - o.time.getHour();
		else
			return this.time.getMinute() - o.time.getMinute();
	}
	
	@Override
	public String toString(){
		return String.format("%d/%d/%d %02d:%02d %s vs %s"
				, time.getYear(), time.getMonth(), time.getDay(), time.getHour(), time.getMinute()
				,teamA ,teamB);
	}
}