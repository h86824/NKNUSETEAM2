
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;

import EssGUI.*;
import EssIO.*;
import EssObject.*;

public class Main {

	public static void main(String[] args) {
		
		checkPath();
		
		MainFrame MF = new MainFrame();
	}
	
	private static void checkPath(){
		for(String path : new String[]{ObjectIO.PATH,ObjectIO.PATH+"country", ObjectIO.PATH+"eventSchedule"}){
			File file = new File(path);
			file.mkdirs();
		}
	}
}
