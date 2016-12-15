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
}
