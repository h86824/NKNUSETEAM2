package EssObject;

public abstract class Human implements Comparable<Object>{
	protected String name;
	protected String gender;
	protected int age;
	protected String profession;
	
	public Human(String name){
		this(name, "", 0, "");
	}

	public Human(String name, String gender){
		this(name, gender, 0, "");
	}
	
	public Human(String name, String gender, int age){
		this(name, gender, age, "");
	}
	
	public Human(String name, String gender, int age ,String profession){
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.profession = profession;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getGender(){
		return gender;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setProfession(String profession){
		this.profession = profession;
	}
	
	public String getProfession(){
		return profession;
	}
	
	@Override
	public abstract boolean equals(Object o);
	
	@Override
	public abstract String toString();
}
