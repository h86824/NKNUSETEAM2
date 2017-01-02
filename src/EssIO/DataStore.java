package EssIO;
import java.util.Iterator;
import java.util.TreeSet;

import EssObject.*;

public class DataStore {
	public TreeSet<Country> countrySet;
	public TreeSet<EventSchedule> eventScheduleSet;
	
	public DataStore(){
		readCountry();
		readTeam();
		readAthlete();
		readEventSchedule();
	}
	
	public TreeSet<EventSchedule> getEventSchedule(){
		return eventScheduleSet;
	}
	
	public void setEvnetSchedule(TreeSet<EventSchedule> eventScheduleSet){
		this.eventScheduleSet = eventScheduleSet;
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
		eventScheduleSet = new EventScheduleIO().read();
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
				for(Team j : i.getTeam().toArray(new Team[i.getTeam().size()])){
					deleteTeam(countryName, j.getName());
				}
				countrySet.remove(i);
				countryIO.delete(i);
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
						teamIO.delete(j);
						break;
					}
				}
			}
		}
	}
	
	public void addAthlete(String countryName, String teamName, Athlete athlete){
		ObjectIO<Athlete> athleteIO = new AthleteIO(countryName, teamName);
		TreeSet<Athlete> athleteSet = new TreeSet<Athlete>();
		Team refTeam = getReference(countryName , teamName);
		athleteSet = refTeam.getAthlete();
		athleteSet.add(athlete);
		athleteIO.write(athleteSet);
		readCountry();
		readTeam();
		readAthlete();	
	}
	
	public void deleteAthlete(String countryName, String teamName, Athlete athlete){
		ObjectIO<Athlete> athleteIO = new AthleteIO(countryName, teamName);
		TreeSet<Athlete> athleteSet = new TreeSet<Athlete>();
		Team refTeam = getReference(countryName , teamName);
		athleteSet = refTeam.getAthlete();
		athleteSet.remove(athlete);
		athleteIO.write(athleteSet);
		readCountry();
		readTeam();
		readAthlete();
	}
	
	public void addEventSchedule(EventSchedule eventSchedule){
		eventScheduleSet.add(eventSchedule);
		ObjectIO<EventSchedule> eventScheduleIO = new EventScheduleIO();
		eventScheduleIO.write(eventScheduleSet);
		readEventSchedule();
	}
	
	public void deleteEventSchedule(EventSchedule eventSchedule){
		eventScheduleSet.remove(eventSchedule);
		ObjectIO<EventSchedule> eventScheduleIO = new EventScheduleIO();
		eventScheduleIO.write(eventScheduleSet);
		eventScheduleIO.delete(eventSchedule);
		readEventSchedule();
	}
	
	/*取得參考*/
	public Country getReference(String countryName){
		for(Country i : countrySet){
			if(i.getName().equals(countryName))
				return i;
		}
		return null;
	}
	
	public Team getReference(String countryName , String teamName){
		for(Country i : countrySet){
			if(i.getName().equals(countryName)){
				for(Team j : i.getTeam()){
					if(j.getName().equals(teamName))
						return j;
				}
			}
		}
		return null;
	}
}
