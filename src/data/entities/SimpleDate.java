package data.entities;

public class SimpleDate {
	String month;
	int day;
	int year;
	public SimpleDate(String date){
		if(date != null){
			String[] tokens = date.split("-");
			day = Integer.parseInt(tokens[0]);
			year = Integer.parseInt(tokens[2]);
			month = tokens[1];
		}
		else{
			day = 0;
			year = 0;
			month = "-";
		}
	}
	public SimpleDate(int day, String month, int year){
		this.month = month;
		this.day = day;
		this.year = year;
	}
	public String getMonth(){return month;}
	public int getDay(){return day;}
	public int getYear(){return year;}
	
	private int monthToInt(){
		int m = 0;
		if(month.equalsIgnoreCase("jan"))m = 1;
		else if(month.equalsIgnoreCase("feb"))m = 2;
		else if(month.equalsIgnoreCase("mar"))m = 3;
		else if(month.equalsIgnoreCase("apr"))m = 4;
		else if(month.equalsIgnoreCase("may"))m = 5;
		else if(month.equalsIgnoreCase("jun"))m = 6;
		else if(month.equalsIgnoreCase("jul"))m = 7;
		else if(month.equalsIgnoreCase("aug"))m = 8;
		else if(month.equalsIgnoreCase("sep"))m = 9;
		else if(month.equalsIgnoreCase("oct"))m = 10;
		else if(month.equalsIgnoreCase("nov"))m = 11;
		else if(month.equalsIgnoreCase("dec"))m = 12;
		return m;
	}
	
	@Override
	public String toString(){
		return year + "-" + monthToInt() + "-" + day;
	}
	public static SimpleDate getTodaysDate(){
		return null;
	}
	public String getOriginalDateForm(){
		return day+"-"+month+"-"+year;
	}
	public void decreaseDate(){
		
	}
}
