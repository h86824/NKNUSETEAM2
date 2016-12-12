package EssObject;

public class Coach extends Human{
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
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if(this.getClass() != o.getClass()){
			return false;
		}
		Athlete athlete = (Athlete) o;
		if(athlete.getName() != this.name){
			return false;
		}
		if(athlete.getGender() != this.gender){
			return false;
		}
		if(athlete.getAge() != this.age){
			return false;
		}
		if(athlete.getProfession() != this.getProfession()){
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
