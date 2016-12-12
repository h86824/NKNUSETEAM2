package EssObject;

public class Athlete extends Human<Athlete>{
	private int height;
	private int weight;
	private String country;
	private RaceRecord record;
	private MedicalHistory medicalHistory;
	
	public Athlete(String name, String gender, int age, String profession, int height, int weight
			, String country, RaceRecord record, MedicalHistory medicalHistory) {
		super(name, gender, age, profession);
		this.height = height;
		this.weight = weight;
		this.country = country;
		this.record = record;
		this.medicalHistory = medicalHistory;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public String getCountry(){
		return country;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public RaceRecord getRecord(){
		return record;
	}
	
	public void setRecord(RaceRecord record){
		this.record = record;
	}
	
	public MedicalHistory getMedicalHistory(){
		return medicalHistory;
	}
	
	public void setMedicalHistory(MedicalHistory medicalHistory){
		this.medicalHistory = medicalHistory;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass())
			return false;
		Athlete athlete = (Athlete) o;
		if(athlete.getName() != this.name)
			return false;
		if(athlete.getGender() != this.gender)
			return false;
		if(athlete.getAge() != this.age)
			return false;
		if(athlete.getProfession() != this.getProfession())
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return country + " " + profession +" " + name;
	}



	@Override
	public int compareTo(Athlete o) {
		if(!this.name.equals(o.name))
			return name.compareTo(o.name);
		if(this.age != o.age)
			return this.age - o.age;
		else 
			return this.profession.compareTo(o.profession);
	}
	
}
