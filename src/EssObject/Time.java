package EssObject;

public class Time {
	public int year;
	public int month;
	public int day;
	public int hour;
	public int minute;
	public Time(int year ,int month , int day , int hour , int minute){
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public String toString(){
		return String.format("%d/%d/%d %02d%02d", year, month, day, hour, minute);
	}
}
