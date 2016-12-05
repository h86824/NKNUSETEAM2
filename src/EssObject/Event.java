package EssObject;

public class Event implements Comparable<Event>{
	Time time;
	Site site;
	Team teamA;
	Team teamB;
	Event(int year, int month, int day, int hour,int minute, Site site, Team teamA ,Team teamB){
		time = new Time(year , month , day , hour , minute);
		this.site = site;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	
	Event(Time time, Site site, Team teamA ,Team teamB){
		this.time = time;
		this.site = site;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.getClass() != o.getClass())return false;
		Event event = (Event)o;
		if(this.time.year != event.time.year)return false;
		else if(this.time.month != event.time.month)return false;
		else if(this.time.day != event.time.day)return false;
		else if(this.time.hour != event.time.hour)return false;
		else if(this.time.minute != event.time.minute)return false;
		else if(this.site.equals(site) )return false;
		else if(this.teamA.equals(teamA))return false;
		else if(this.teamB.equals(teamB))return false;
		else return true;
	}
	
	@Override
	public int compareTo(Event o) {
		if(this.time.year != o.time.year)
			return this.time.year - o.time.year;
		else if(this.time.month != o.time.month)
			return this.time.month - o.time.month;
		else if(this.time.day != o.time.day)
			return this.time.day - o.time.day;
		else if(this.time.hour != o.time.hour)
			return this.time.hour - o.time.hour;
		else
			return this.time.minute - o.time.minute;
	}
	
	@Override
	public String toString(){
		return String.format("%d/%d/%d %02d:%02d %s vs %s"
				, time.year, time.month, time.day, time.hour, time.minute
				,teamA ,teamB);
	}
}