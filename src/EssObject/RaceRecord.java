package EssObject;

public class RaceRecord implements Comparable<RaceRecord>{
	private Time time;
	private String site;
	private String teamA;
	private String teamB;
	private int teamAResult,teamBResult;
	public RaceRecord(int year, int month, int day, int hour,int minute, String site, String teamA ,String teamB){
		time = new Time(year , month , day , hour , minute);
		this.site = site;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	public RaceRecord(Time time, String site, String teamA, String teamB){
		this.time = time;
		this.site = site;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	public void setResult(int teamAResult,int teamBResult){
		this.teamAResult=teamAResult;
		this.teamBResult=teamBResult;
	}
	public String getResult(){
		return teamAResult+":"+teamBResult;
	}
	public Time getTime(){
		return time;
	}
	
	public String getSite(){
		return site;
	}
	
	public String getTeamA(){
		return teamA;
	}
	
	public String getTeamB(){
		return teamB;
	}
	public boolean equals(Object o){
		if(this.getClass() != o.getClass())return false;
		RaceRecord raceRecord = (RaceRecord)o;
		if(this.time.getYear() != raceRecord.time.getYear()){
			return false;
		}
		else if(this.time.getMonth() != raceRecord.time.getMonth()){
			return false;
		}
		else if(this.time.getDay() != raceRecord.time.getDay()){
			return false;
		}
		else if(this.time.getHour() != raceRecord.time.getHour()){
			return false;
		}
		else if(this.time.getMinute() != raceRecord.time.getMinute()){
			return false;
		}
		else if(!this.site.equals(raceRecord.site) ){
			return false;
		}
		else if(this.teamA.equals(raceRecord.teamA)&&this.teamB.equals(raceRecord.teamB)){
			return false;
		}
		else if(this.teamA.equals(raceRecord.teamB)&&this.teamB.equals(raceRecord.teamA)){
			return false;
		}
		else return true;
	}
	@Override
	public int compareTo(RaceRecord o) {
		if(this.time.getYear() != o.time.getYear()){
			return this.time.getYear() - o.time.getYear();
		}
		else if(this.time.getMonth() != o.time.getMonth()){
			return this.time.getMonth() - o.time.getMonth();
		}
		else if(this.time.getDay() != o.time.getDay()){
			return this.time.getDay() - o.time.getDay();
		}
		else if(this.time.getHour() != o.time.getHour()){
			return this.time.getHour() - o.time.getHour();
		}
		else if(this.time.getMinute() != o.time.getMinute()){
			return this.time.getMinute() - o.time.getMinute();
		}
		else{
			return this.getTeamA().compareTo(o.getTeamA());
		}
		
	}
	public String toString(){
		return String.format("%d/%d/%d %02d:%02d %s vs %s winner:%s"
				, time.getYear(), time.getMonth(), time.getDay(), time.getHour(), time.getMinute()
				,teamA ,teamB,getResult());
	}
	
}
