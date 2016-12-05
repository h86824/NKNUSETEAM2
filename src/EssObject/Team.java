package EssObject;

public class Team {
	private String name;
	private Athlete[] athleteArray;
	private Coach[] coachArray;
	
	public Team(String name, Athlete[] athleteArray, Coach[] coachArray){
		this.name = name;
		this.athleteArray = athleteArray;
		this.coachArray = coachArray;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAthlete(Athlete[] athleteArray){
		this.athleteArray = athleteArray;
	}
	
	public Athlete[] getAthlete(){
		return athleteArray;
	}
	
	public void setCoach(Coach[] coachArray){
		this.coachArray = coachArray;
	}
	
	public Coach[] getCoach(){
		return coachArray;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.getClass() != o.getClass())return false;
		Team event = (Team)o;
		return false;
	}
	
	@Override
	public String toString(){
		return "" + name;
	}
}
