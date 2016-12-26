package EssIO;

import EssObject.Device;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
/**
 * 
 * @author 普皓群
 *
 */
public class DeviceIO implements ObjectIO<Device>{
	private String filePath = ObjectIO.PATH+"DeviceList.txt";
	
	@Override
	public TreeSet<Device> read(){
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner inputFile = new Scanner(input);
		TreeSet<Device> returnSet = new TreeSet<Device>();
		while(inputFile.hasNext()){
			String name = inputFile.next().split("=")[1];
			int amount = Integer.parseInt(inputFile.next().split("=")[1]);
			returnSet.add(new Device(name , amount));
		}
		inputFile.close();
		return returnSet;
	}

	@Override
	public void write(TreeSet<Device> inputList){
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(Device i: inputList){
				out.write("name="+i.getName()+"\r\n");
				out.write("amount="+i.getAmount()+"\r\n\r\n");
			}
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Device t) {
		// TODO Auto-generated method stub
		
	}
}
