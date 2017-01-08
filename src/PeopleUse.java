import java.io.File;

import EssGUI.MainFrame;
import EssIO.ObjectIO;

public class PeopleUse {
	public static void main(String[] args) {
		
		checkPath();
		
		MainFrame MF = new MainFrame(new String[]{"查詢賽程", "查詢選手"});
	}
	
	private static void checkPath(){
		for(String path : new String[]{ObjectIO.PATH,ObjectIO.PATH+"country", ObjectIO.PATH+"eventSchedule"}){
			File file = new File(path);
			file.mkdirs();
		}
	}
}
