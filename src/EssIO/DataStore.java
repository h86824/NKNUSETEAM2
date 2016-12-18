package EssIO;
import java.util.TreeSet;

import EssObject.*;

public class DataStore {
	public TreeSet<Country> countrySet;
	public EventSchedule eventSchedule;
	
	public DataStore(){
		readCountry();
		readTeam();
		readAthlete();
		readEventSchedule();
	}
	
	public EventSchedule getEventSchedule(){
		return eventSchedule;
	}
	
	public void setEvnetSchedule(EventSchedule eventSchedule){
		this.eventSchedule = eventSchedule;
	}
	
	public TreeSet<Country> getCountry(){
		return countrySet;
	}
	
	private void readCountry(){
		countrySet = new CountryIO().read();
	}
	
	private void readTeam(){
		for(Country country: countrySet){
			country.setTeam(new TeamIO(country.getName()).read() );
		}
	}
	
	private void readAthlete(){
		for(Country country: countrySet){
			for(Team team : country.getTeam()){
				team.setAthlete(new AthleteIO(country.getName(), team.getName()).read());
			}
		}
	}
	
	private void readCoach(){
		for(Country country: countrySet){
			for(Team team : country.getTeam()){
				
			}
		}
	}
	
	private void readEventSchedule(){
		eventSchedule = new EventScheduleIO().read();
	}
	
	public void addCountry(String countryName){
		countrySet.add(new Country(countryName));
		ObjectIO<Country> countryIO = new CountryIO();
		countryIO.write(countrySet);
		ObjectIO<Team> teamIO = new TeamIO(countryName);
		teamIO.write(new TreeSet<Team>());
		readCountry();
		readTeam();
		readAthlete();
	}
	
	public void deleteCountry(String countryName){
		ObjectIO<Country> countryIO = new CountryIO();
		for(Country i : countrySet){
			if(i.getName().equals(countryName)){
				countrySet.remove(i);
				break;
			}
		}
		countryIO.write(countrySet);
	}
	
	public void addTeam(String countryName , String teamName){
		ObjectIO<Team> teamIO = new TeamIO(countryName);
		TreeSet<Team> teamSet = new TreeSet<Team>();
		for(Country i : countrySet){
			if(i.getName().equals(countryName)){
				teamSet = i.getTeam();
				break;
			}
		}
		teamSet.add(new Team(teamName , null ,null));
		teamIO.write(teamSet);
		ObjectIO<Athlete> athleteIO = new AthleteIO(countryName , teamName);
		athleteIO.write(new TreeSet<Athlete>());
		readCountry();
		readTeam();
		readAthlete();
	}
	
	public void deleteTeam(String countryName ,String teamName){
		ObjectIO<Team> teamIO = new TeamIO(countryName);
		TreeSet<Team> tempTeam = new TreeSet<Team>();
		for(Country i : countrySet){
			if(i.getName().equals(countryName)){
				for(Team j : i.getTeam()){
					if(j.getName().equals(teamName)){
						tempTeam = i.getTeam();
						tempTeam.remove(j);
						i.setTeam(tempTeam);
						teamIO.write(tempTeam);
						break;
					}
				}
			}
		}
	}
}
