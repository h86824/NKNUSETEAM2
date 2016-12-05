package EssProcess;

import java.util.ArrayList;
import java.util.Random;
import EssObject.EventSchedule;
import EssObject.Team;
import EssObject.Time;

public class RandomScheduleBuilder {
	Team[] team;
	Time[] time;
	public RandomScheduleBuilder(Team[] team, Time[] time){
		this.team = team;
		this.time = time;
	}
	
	public EventSchedule getSchedule(){
		EventSchedule returnES = new EventSchedule();
		ArrayList<Team> teamList = new ArrayList<Team>();
		ArrayList<Time> timeList = new ArrayList<Time>();
		
		for(Team i : team)
			teamList.add(i);
		for(Time i : time)
			timeList.add(i);
		Random ranNumber = new Random();
		while((teamList.size() > 1) || (!timeList.isEmpty())){
			int tempNum = ranNumber.nextInt(timeList.size());
			Time nowTime = timeList.get(tempNum);
			timeList.remove(tempNum);
			tempNum = ranNumber.nextInt(teamList.size());
			Team teamA = teamList.get(tempNum);
			teamList.remove(tempNum);
			tempNum = ranNumber.nextInt(teamList.size());
			Team teamB = teamList.get(tempNum);
			teamList.remove(tempNum);
			returnES.addEvent(nowTime, null, teamA, teamB);
		}
		return returnES;
	}
}
