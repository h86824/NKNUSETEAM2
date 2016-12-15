package EssIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class EventScheduleIO{
	private String filePath = ObjectIO.PATH;
	public EventScheduleIO(){
		filePath += "eventSchedule/eventSchedule.txt";
	}
	
	public EventSchedule read() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner inputFile = new Scanner(input);
		EventSchedule returnSet = new EventSchedule();
		if(inputFile.hasNext()){
			String name = inputFile.next().split("=")[1];
			returnSet.setName(name);
		}
		while(inputFile.hasNext()){
			String name = inputFile.next().split("=")[0];
			int year = Integer.parseInt(inputFile.next().split("=")[1]);
			int month = Integer.parseInt(inputFile.next().split("=")[1]);
			int day = Integer.parseInt(inputFile.next().split("=")[1]);
			int hour = Integer.parseInt(inputFile.next().split("=")[1]);
			int minute = Integer.parseInt(inputFile.next().split("=")[1]);
			String site = inputFile.next().split("=")[1];
			String teamA = inputFile.next().split("=")[1];
			String teamB = inputFile.next().split("=")[1];
			returnSet.addEvent(name, year, month, day, hour, minute, site, teamA, teamB);
		}
		inputFile.close();
		return returnSet;
	}

	public void write(EventSchedule inputSet) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			out.write("name="+inputSet.getName()+"\r\n");
			for(Event i: inputSet.getEvents()){
				out.write("name="+i.getName()+"\r\n");
				out.write("year="+i.getTime().getYear()+"\r\n");
				out.write("month="+i.getTime().getMonth()+"\r\n");
				out.write("day="+i.getTime().getDay()+"\r\n");
				out.write("hour="+i.getTime().getHour()+"\r\n");
				out.write("minute="+i.getTime().getMinute()+"\r\n");
				out.write("site="+i.getSite()+"\r\n");
				out.write("teamA="+i.getTeamA()+"\r\n");
				out.write("teamB="+i.getTeamB()+"\r\n\r\n");
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
