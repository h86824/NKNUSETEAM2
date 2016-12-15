package EssObject;

public class Time implements Comparable<Time>{
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	public Time(int year ,int month , int day , int hour , int minute){
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public int getYear(){
		return year;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public int getMonth(){
		return month;
	}
	
	public void setMonth(int month){
		this.month = month;
	}
	
	public int getDay(){
		return day;
	}
	
	public void setDay(int day){
		this.day = day;
	}
	
	public int getHour(){
		return hour;
	}
	
	public void setHour(int hour){
		this.hour = hour;
	}
	
	public int getMinute(){
		return minute;
	}
	
	public void setMinute(int minute){
		this.minute = minute;
	}
	
	@Override
	public String toString(){
		return String.format("%d/%d/%d %02d%02d", year, month, day, hour, minute);
	}

	@Override
	public int compareTo(Time o) {
		if(this.year != o.year)return this.year - o.year;
		if(this.month != o.month)return this.month - o.month;
		if(this.day != o.day)return this.day - o.day;
		if(this.hour != o.hour)return this.hour - o.hour;
		return this.minute - o.minute;
	}
}
