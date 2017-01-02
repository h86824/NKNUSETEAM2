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

import EssObject.Country;

public class CountryIO implements ObjectIO<Country>{
	private String filePath = ObjectIO.PATH;
	public CountryIO(){
		filePath += "country/countryList.txt";
	}
	
	@Override
	public void write(TreeSet<Country> inputSet) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(Country i: inputSet){
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
	public TreeSet<Country> read() {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner inputFile = new Scanner(input);
		TreeSet<Country> returnSet = new TreeSet<Country>();
		while(inputFile.hasNext()){
			String name = inputFile.next().split("=")[1];
			returnSet.add(new Country(name));
		}
		inputFile.close();
		return returnSet;
	}

	@Override
	public void delete(Country t) {
		new File(ObjectIO.PATH + "/country/" + t.getName() + "_team.txt").delete();
		
	}

}
