package EssIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.TreeSet;

import EssObject.Event;
import EssObject.EventSchedule;

public class EventScheduleIO implements ObjectIO<EventSchedule>{
	private String filePath = ObjectIO.PATH;
	public EventScheduleIO(){
		filePath += "eventSchedule/eventSchedule";
	}
	
	public TreeSet<EventSchedule> read() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(filePath+".txt") ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner inputFile = new Scanner(input);
		TreeSet<EventSchedule> returnSet = new TreeSet<EventSchedule>();
		while(inputFile.hasNext()){
			String project = inputFile.next().split("=")[1];
			String name = inputFile.next().split("=")[1];
			
			EventSchedule eventSechedule = new EventSchedule(name ,project);
			Scanner eventInput = null;
			try {
				eventInput = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(filePath+"_"+project+"_"+name+".txt") ,"UTF-8")));
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				e.printStackTrace();
			}
			
			while(eventInput.hasNext()){
				int year = Integer.parseInt(eventInput.next().split("=")[1]);
				int month = Integer.parseInt(eventInput.next().split("=")[1]);
				int day = Integer.parseInt(eventInput.next().split("=")[1]);
				int hour = Integer.parseInt(eventInput.next().split("=")[1]);
				int minute = Integer.parseInt(eventInput.next().split("=")[1]);
				String site = eventInput.next().split("=")[1];
				String teamA = eventInput.next().split("=")[1];
				String teamB = eventInput.next().split("=")[1];
				eventSechedule.addEvent(year, month, day, hour, minute, site, teamA, teamB);
			}
			returnSet.add(eventSechedule);
			eventInput.close();
		}
		
		inputFile.close();
		return returnSet;
	}

	public void write(TreeSet<EventSchedule> eventScheduleSet) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath+".txt") ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(EventSchedule eventSchedule : eventScheduleSet){
				out.write("project="+eventSchedule.getProject()+"\r\n");
				out.write("name="+eventSchedule.getName()+"\r\n");
				
				BufferedWriter evneOut = null;
				try {
					evneOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath+"_"+eventSchedule.getProject()+"_"+eventSchedule.getName()+".txt") ,"UTF-8"));
				}
				catch (UnsupportedEncodingException | FileNotFoundException e) {
					e.printStackTrace();
				}
				
				for(Event i: eventSchedule.getEvents()){
					evneOut.write("year="+i.getTime().getYear()+"\r\n");
					evneOut.write("month="+i.getTime().getMonth()+"\r\n");
					evneOut.write("day="+i.getTime().getDay()+"\r\n");
					evneOut.write("hour="+i.getTime().getHour()+"\r\n");
					evneOut.write("minute="+i.getTime().getMinute()+"\r\n");
					evneOut.write("site="+i.getSite()+"\r\n");
					evneOut.write("teamA="+i.getTeamA()+"\r\n");
					evneOut.write("teamB="+i.getTeamB()+"\r\n");
				}
				evneOut.flush();
				evneOut.close();
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(EventSchedule eventSchedule){
		new File(filePath+"_" + eventSchedule.getProject() + "_" + eventSchedule.getName() +".txt").delete();
	}
	
}
