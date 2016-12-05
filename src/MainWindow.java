
import java.util.ArrayList;

import EssIO.*;
import EssObject.*;
import EssProcess.*;
public class MainWindow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ObjectIO<Device> deviceIO = new DeviceIO();
		ArrayList<Device> device = new ArrayList<Device>();
		device.add(new Device("球拍" ,50));
		device.add(new Device("籃球" ,20));
		deviceIO.write(device);
		
		device = deviceIO.read();
		
		for(Device i :device){
			System.out.println(i);
		}*/
		
		Team[] team = new Team[10];
		Time[] time = new Time[5];
		for(int i = 0 ; i < 10 ; i++)
			team[i] = new Team(String.format("team %d" ,i + 1), null, null);
		for(int i = 0 ; i < 5 ; i++)
			time[i] = new Time(2016, 12, 5, 10, i*10);
		
		RandomScheduleBuilder RSB = new RandomScheduleBuilder(team, time);
		EventSchedule ES = RSB.getSchedule();
		
		for(Event i : ES.getEvents()){
			System.out.println(i);
		}
		System.out.println("finish");
	}

}
