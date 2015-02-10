package data.entities;


//import java.text.NumberFormat;
//import java.text.ParseException;

public class InputVector {
	SimpleDate day;
	double open;
	double low;
	double high;
	double close;
	double volume;
	double adjClose;
	
	public InputVector(){
		
	}
	public void setDay(String d){
		day = new SimpleDate(d);
	}
	public void setOpen(String o){
		open = parseDouble(o);
	}
	public void setClose(String c){
		close = parseDouble(c);
	}
	public void setVolume(String v){
		volume = parseDouble(v);
	}
	public void setLow(String l){
		low = parseDouble(l);
	}
	public void setHigh(String h){
		high = parseDouble(h);
	}
	public void setAdjClose(String a){
		adjClose = parseDouble(a);
	}
	public SimpleDate getDay(){return day;}
	public double getOpen(){return open;}
	public double getHigh(){return high;}
	public double getLow(){return low;}
	public double getClose(){return close;}
	public double getVolume(){return volume;}
	public double getAdjClose(){return adjClose;}
	private double parseDouble(String s){
		return Double.parseDouble(s);
		/*NumberFormat format = NumberFormat.getNumberInstance();
		try {
			Number number = format.parse(s);
			return number.doubleValue();
		} catch (ParseException e) {
			System.out.println("Invalid double");
			return 0.0;
		}*/
	}
	public String toString(){
		return day.toString() + ","
				+ open + ","
				+ high + ","
				+ low + ","
				+ close + ","
				+ volume + ","
				+ adjClose;
	}
}
