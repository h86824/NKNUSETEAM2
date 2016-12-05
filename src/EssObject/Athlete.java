package EssObject;

public class Athlete extends Human{
	private int height;
	private int weight;
	private String country;
	private RaceRecord record;
	private MedicalHistory medicalHistory;
	
	public Athlete(String name, String gender, int age, String profession) {
		super(name, gender, age, profession);
		
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
