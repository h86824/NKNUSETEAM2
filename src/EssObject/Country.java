package EssObject;

import java.util.TreeSet;

public class Country implements Comparable<Country>{
	private String name;
	private TreeSet<Team> team;
	
	public Country(){
		this("");
	}
	
	public Country(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setTeam(TreeSet<Team> team){
		this.team = team;
	}
	
	public TreeSet<Team> getTeam(){
		return team;
	}

	@Override
	public int compareTo(Country o) {
		return this.name.compareTo(o.name);
	}
}
