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

import EssObject.Team;

public class TeamIO implements ObjectIO<Team>{
	private String filePath = ObjectIO.PATH;
	
	public TeamIO(String countryName){
		filePath += "country/" + countryName + "_team.txt";
		/*try {
			filePath = new String(filePath.getBytes("UTF-8"),"BIG5");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
	}
	
	@Override
	public TreeSet<Team> read() {
		BufferedReader input = null;
		try {
			File f = new File(filePath);
			input = new BufferedReader(new InputStreamReader(new FileInputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner inputFile = new Scanner(input);
		TreeSet<Team> returnSet = new TreeSet<Team>();
		while(inputFile.hasNext()){
			String name = inputFile.next().split("=")[1];
			returnSet.add(new Team(name , null, null));
		}
		inputFile.close();
		return returnSet;
	}

	@Override
	public void write(TreeSet<Team> inputSet) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(Team i: inputSet){
				out.write("name="+i.getName()+"\r\n");
			}
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Team t) {
		new File(filePath.substring(0, filePath.length() - 1 - 8) + "_" + t.getName() +"_Athlete.txt").delete();
	}

}
