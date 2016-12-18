package EssObject;

import java.util.ArrayList;

import java.util.Collections;

public class MedicalHistory {
	
	String name;
	ArrayList<String> medicalHistory=new ArrayList<String>();
	public MedicalHistory(String name){
		this.name = name;
	}
	public  String getName(){
		return name;
	}
	public void addMedicalHistory(String medical){
		medicalHistory.add(medical);
	}
	public ArrayList<String> getMedicalHistory(){
		Collections.sort(medicalHistory);
		return medicalHistory;
	}
	public boolean equals(Object o){
		if(this.getClass() != o.getClass())return false;
		MedicalHistory medicalHistory=(MedicalHistory) o;
		if(!this.name.equals(medicalHistory.name)){
			return false;
		}
		else{
			return true;
		}
	}
}
