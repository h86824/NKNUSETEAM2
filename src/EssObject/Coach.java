package EssObject;

public class Coach extends Human<Coach>{
	private String country;
    public Coach(String name, String gender, int age, String profession,String country) {
        super(name, gender, age, profession);
        this.country = country;
      }
    public String getCountry(){
		return country;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
   
    
	@Override
	public int compareTo(Coach o) {
		// TODO Auto-generated method stub
		if(!this.name.equals(o.name)){
			return name.compareTo(o.name);
		}
		if(this.age != o.age){
			return this.age - o.age;
		}
		if(!this.country.equals(o.country)){
			return country.compareTo(o.country);
		}
		else {
			return this.profession.compareTo(o.profession);
		}
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if(this.getClass() != o.getClass()){
			return false;
		}
		Coach coach = (Coach) o;
		if(coach.getName() != this.name){
			return false;
		}
		if(coach.getGender() != this.gender){
			return false;
		}
		if(coach.getAge() != this.age){
			return false;
		}
		if(coach.getProfession() != this.getProfession()){
			return false;
		}
		if(coach.getCountry()!= this.getCountry()){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name = "+this.getName()+"\ngender = "+this.getGender()+"\nage = "+this.getAge()+"\nprofession = "+this.getProfession() ;
	}
}
