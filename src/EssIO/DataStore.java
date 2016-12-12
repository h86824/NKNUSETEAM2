package EssIO;
import java.util.TreeSet;

import EssObject.*;

public class DataStore {
	public TreeSet<Country> countrySet;
	
	public DataStore(){
		readCountry();
		readTeam();
		readAthlete();
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
}
