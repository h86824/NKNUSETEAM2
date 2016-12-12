
import java.util.ArrayList;
import java.util.TreeSet;

import EssGUI.*;
import EssIO.*;
import EssObject.*;
import EssProcess.*;
public class MainWindow {

	public static void main(String[] args) {
		String COUNTRY = "台灣";
		String BALL = "棒球";
		ObjectIO<Team> ObjIO = new TeamIO(COUNTRY);
		TreeSet<Team> teamSet = new TreeSet<Team>();
		TreeSet<Athlete> athleteSet = new TreeSet<Athlete>();
		ObjectIO<Athlete> athleteIO = new AthleteIO(COUNTRY, BALL);
		athleteSet.add(new Athlete("選手A", "男", 18, BALL, 155, 65, COUNTRY, null, null));
		athleteSet.add(new Athlete("選手B", "男", 19, BALL, 165, 75, COUNTRY, null, null));
		athleteSet.add(new Athlete("選手C", "男", 18, BALL, 158, 45, COUNTRY, null, null));
		athleteIO.write(athleteSet);
		teamSet.add(new Team(BALL, athleteSet, null));
		
		BALL = "籃球";
		athleteSet = new TreeSet<Athlete>();
		athleteIO = new AthleteIO(COUNTRY , BALL);
		athleteSet.add(new Athlete("選手A", "男", 18, BALL, 155, 65, COUNTRY, null, null));
		athleteSet.add(new Athlete("選手B", "男", 19, BALL, 165, 75, COUNTRY, null, null));
		athleteSet.add(new Athlete("選手C", "男", 18, BALL, 158, 45, COUNTRY, null, null));
		athleteIO.write(athleteSet);
		teamSet.add(new Team(BALL, athleteSet, null));
		ObjIO.write(teamSet);
		
		DataStore DS = new DataStore();
		for(Country country : DS.countrySet){
			for(Team team : country.getTeam()){
				for(Athlete athlete: team.getAthlete()){
					System.out.println(athlete);
				}
			}
		}
		
		MainFrame MF = new MainFrame();
		
		System.out.println("finish");
	}

}
