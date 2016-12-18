
import java.util.ArrayList;
import java.util.TreeSet;

import EssGUI.*;
import EssIO.*;
import EssObject.*;
import EssProcess.*;
public class MainWindow {

	public static void main(String[] args) {
		TreeSet<Country> countrySet = new TreeSet<Country>();
		countrySet.add(new Country("台灣"));
		countrySet.add(new Country("史瓦濟蘭"));
		countrySet.add(new Country("聖多美普林西比"));
		countrySet.add(new Country("布吉納法索"));
		//countrySet.add(new Country("日本"));
		new CountryIO().write(countrySet);
		String COUNTRY = "台灣";
		String BALL = "棒球";
		ObjectIO<Team> ObjIO = new TeamIO(COUNTRY);
		TreeSet<Team> teamSet = new TreeSet<Team>();
		TreeSet<Athlete> athleteSet = new TreeSet<Athlete>();
		ObjectIO<Athlete> athleteIO = new AthleteIO(COUNTRY, BALL);
		athleteSet.add(new Athlete(BALL+"選手A", "男", 18, BALL, 155, 65, COUNTRY, null, null));
		athleteSet.add(new Athlete(BALL+"選手B", "男", 19, BALL, 165, 75, COUNTRY, null, null));
		athleteSet.add(new Athlete(BALL+"選手C", "男", 18, BALL, 158, 45, COUNTRY, null, null));
		athleteIO.write(athleteSet);
		teamSet.add(new Team(BALL, athleteSet, null));
		
		BALL = "籃球";
		athleteSet = new TreeSet<Athlete>();
		athleteIO = new AthleteIO(COUNTRY , BALL);
		athleteSet.add(new Athlete(BALL+"選手A", "男", 18, BALL, 155, 65, COUNTRY, null, null));
		athleteSet.add(new Athlete(BALL+"選手B", "男", 19, BALL, 165, 75, COUNTRY, null, null));
		athleteSet.add(new Athlete(BALL+"選手C", "男", 18, BALL, 158, 45, COUNTRY, null, null));
		athleteIO.write(athleteSet);
		teamSet.add(new Team(BALL, athleteSet, null));
		ObjIO.write(teamSet);
		
		COUNTRY = "聖多美普林西比";
		BALL = "棒球";
		ObjIO = new TeamIO(COUNTRY);
		teamSet = new TreeSet<Team>();
		athleteSet = new TreeSet<Athlete>();
		athleteIO = new AthleteIO(COUNTRY, BALL);
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
		
		COUNTRY = "史瓦濟蘭";
		BALL = "棒球";
		ObjIO = new TeamIO(COUNTRY);
		teamSet = new TreeSet<Team>();
		athleteSet = new TreeSet<Athlete>();
		athleteIO = new AthleteIO(COUNTRY, BALL);
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
		
		COUNTRY = "布吉納法索";
		BALL = "棒球";
		ObjIO = new TeamIO(COUNTRY);
		teamSet = new TreeSet<Team>();
		athleteSet = new TreeSet<Athlete>();
		athleteIO = new AthleteIO(COUNTRY, BALL);
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
		
		EventSchedule ES = new EventSchedule();
		ES.addEvent("棒球", 2016, 12, 14, 8, 0, "棒球場A", "台灣", "布吉納法索");
		ES.addEvent("棒球", 2016, 12, 14, 9, 0, "棒球場A", "聖多美普林西比", "布吉納法索");
		ES.addEvent("棒球", 2016, 12, 14, 10, 0, "棒球場A", "台灣", "聖多美普林西比");
		new EventScheduleIO().write(ES);
		MainFrame MF = new MainFrame();
		
		System.out.println("finish");
	}

}
