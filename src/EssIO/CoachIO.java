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

import EssObject.Coach;

public class CoachIO implements ObjectIO<Coach>{
	private String filePath = ObjectIO.PATH;
	
	public CoachIO(String countryName){
		filePath += "country/" + countryName +"_Coach.txt";
	}
	
	@Override
	public TreeSet<Coach> read() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner inputFile = new Scanner(input);
		TreeSet<Coach> returnSet = new TreeSet<Coach>();
		while(inputFile.hasNext()){
			String name = inputFile.next().split("=")[1];
			String gender = inputFile.next().split("=")[1];
			int age = Integer.parseInt(inputFile.next().split("=")[1]);
			String profession = inputFile.next().split("=")[1];
			String country = inputFile.next().split("=")[1];
			returnSet.add(new Coach(name, gender, age, profession, country));
		}
		inputFile.close();
		return returnSet;
	}

	@Override
	public void write(TreeSet<Coach> inputSet) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(Coach i: inputSet){
				out.write("name="+i.getName()+"\r\n");
				out.write("gender="+i.getGender()+"\r\n");
				out.write("age="+i.getAge()+"\r\n");
				out.write("getProfession="+i.getProfession()+"\r\n");
				out.write("country="+i.getCountry()+"\r\n\r\n");
			}
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
