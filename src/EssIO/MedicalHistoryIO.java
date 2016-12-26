package EssIO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.TreeSet;
import EssObject.MedicalHistory;

public class MedicalHistoryIO implements ObjectIO<MedicalHistory>{
	private String filePath = ObjectIO.PATH;
	String name;
	public MedicalHistoryIO(String country , String team , String name){
		filePath += "country/" + country + "_" + team + "_MedicalHistory.txt";
		this.name=name;
	}

	@Override
	public TreeSet<MedicalHistory> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(TreeSet<MedicalHistory> inputSet) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath) ,"UTF-8"));
		} 
		catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			for(MedicalHistory i: inputSet){
				out.write("name="+i.getName()+"\r\n");
				out.write("MedicalHistory="+i.getMedicalHistory()+"\r\n\r\n");
			}
			out.flush();
			out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(MedicalHistory t) {
		// TODO Auto-generated method stub
		
	}

}
