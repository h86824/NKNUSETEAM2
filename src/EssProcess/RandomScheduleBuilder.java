package EssProcess;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import EssObject.EventSchedule;
import EssObject.Team;
import EssObject.Time;

public class RandomScheduleBuilder {
	TreeSet<String> team;
	TreeSet<Time> time;
	public RandomScheduleBuilder(TreeSet<String> team, TreeSet<Time> time){
		this.team = team;
		this.time = time;
	}
	
	public EventSchedule getSchedule(){
		EventSchedule returnES = new EventSchedule();
		ArrayList<String> teamList = new ArrayList<String>();
		ArrayList<Time> timeList = new ArrayList<Time>();
		
		for(String i : team)
			teamList.add(i);
		for(Time i : time)
			timeList.add(i);
		Random ranNumber = new Random();
		while((teamList.size() > 1) && (!timeList.isEmpty())){
			int tempNum = 0;
			Time nowTime = timeList.get(tempNum);
			timeList.remove(tempNum);
			tempNum = ranNumber.nextInt(teamList.size());
			String teamA = teamList.get(tempNum);
			teamList.remove(tempNum);
			tempNum = ranNumber.nextInt(teamList.size());
			String teamB = teamList.get(tempNum);
			teamList.remove(tempNum);
			returnES.addEvent("",nowTime, "siteA", teamA, teamB);
		}
		return returnES;
	}
}
