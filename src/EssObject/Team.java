package EssObject;

import java.util.TreeSet;

public class Team implements Comparable<Team>{
	private String name;
	private TreeSet<Athlete> athleteArray;
	private TreeSet<Coach> coachArray;
	
	public Team(String name, TreeSet<Athlete> athleteArray, TreeSet<Coach> coachArray){
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
	
	public void setAthlete(TreeSet<Athlete> athleteArray){
		this.athleteArray = athleteArray;
	}
	
	public TreeSet<Athlete> getAthlete(){
		return athleteArray;
	}
	
	public void setCoach(TreeSet<Coach> coachArray){
		this.coachArray = coachArray;
	}
	
	public TreeSet<Coach> getCoach(){
		return coachArray;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.getClass() != o.getClass())
			return false;
		Team team = (Team)o;
		if(this.name != team.name)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "" + name;
	}

	@Override
	public int compareTo(Team o) {
		return this.name.compareTo(o.name);
	}
}
