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
import EssObject.Athlete;

public class AthleteIO implements ObjectIO<Athlete>{
	private String filePath = ObjectIO.PATH;
	
	public AthleteIO(String country , String team){
		filePath += "country/" + country + "_" + team + "_Athlete.txt";
		/*try {
			filePath = new String(filePath.getBytes("UTF-8"),"BIG5");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
	}
	
	@Override
	public TreeSet<Athlete> read() {
		BufferedReader input = null;
		try {
			File f = new File(filePath);
			input = new BufferedReader(new InputStreamReader(new FileInputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner inputFile = new Scanner(input);
		TreeSet<Athlete> returnSet = new TreeSet<Athlete>();
		while(inputFile.hasNext()){
			String name = inputFile.next().split("=")[1];
			String gender = inputFile.next().split("=")[1];
			int age = Integer.parseInt(inputFile.next().split("=")[1]);
			String profession = inputFile.next().split("=")[1];
			int height = Integer.parseInt(inputFile.next().split("=")[1]);
			int weight = Integer.parseInt(inputFile.next().split("=")[1]);
			String country = inputFile.next().split("=")[1];
			returnSet.add(new Athlete(name, gender, age, profession
					, height, weight, country, null, null));
		}
		inputFile.close();
		return returnSet;
	}

	@Override
	public void write(TreeSet<Athlete> inputSet) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(Athlete i: inputSet){
				out.write("name="+i.getName()+"\r\n");
				out.write("gender="+i.getGender()+"\r\n");
				out.write("age="+i.getAge()+"\r\n");
				out.write("getProfession="+i.getProfession()+"\r\n");
				out.write("height="+i.getHeight()+"\r\n");
				out.write("weight="+i.getWeight()+"\r\n");
				out.write("country="+i.getCountry()+"\r\n\r\n");
			}
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Athlete t) {
		new File(filePath).delete();
		
	}
	
}
