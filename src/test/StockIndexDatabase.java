package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import data.entities.InputVector;
import data.entities.SimpleDate;

public class StockIndexDatabase {
	String filepath = "./data-files/djia-inputs.csv";
	int databaseID;
	TreeMap<String,InputVector> inputSet = new TreeMap<String,InputVector>();
	
	public StockIndexDatabase(){
		
	}
	public void LoadDatabase(){
		String line = "";
		boolean firstLine = true;
		try{
			BufferedReader database = new BufferedReader(new FileReader(filepath));
			while ((line = database.readLine()) != null) {
				if(firstLine){
					firstLine = false;
					continue;
				}
				parseData(line.split(","));
			}
			database.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Can't read file");
		}
	}
	
	public InputVector getRecordFromToday(){
		return getRecordFromDate(getTodaysDate());
		
	}
	public InputVector getRecordFromYesterday(){
		return getRecordFromDate(getYesterdaysDate());
	}
	public InputVector getRecordFromDate(SimpleDate date){
		return getRecordFromDate(date.toString());
	}
	private InputVector getRecordFromDate(String date){
		return inputSet.get(date);
	}
	public InputVector[] getRecordsFromPeriod(SimpleDate start, SimpleDate end){
		//String[] dates = getDateRange(start,end);
		return null;
	}

	private void parseData(String[] inputs){
		InputVector newInput = new InputVector();
		newInput.setDay(inputs[0]);
		newInput.setOpen(inputs[1]);
		newInput.setHigh(inputs[2]);
		newInput.setLow(inputs[3]);
		newInput.setClose(inputs[4]);
		newInput.setVolume(inputs[5]);
		newInput.setAdjClose(inputs[6]);
		
		inputSet.put(newInput.getDay().toString(), newInput);
	}
	private void updateDatabase(){
		
		//todaysData = inputSet.get();
	}
	private String getTodaysDate(){
		Set<String> set = inputSet.keySet();
		String[] dates = new String[set.size()];
		set.toArray(dates);
		int today = dates.length - 1;
		return dates[today];
	}
	private String getYesterdaysDate(){
		Set<String> set = inputSet.keySet();
		String[] dates = new String[set.size()];
		set.toArray(dates);
		int yesterday = dates.length - 2;
		return dates[yesterday];
	}
	private String[] getDateRange(SimpleDate start, SimpleDate end){
		
		return null;
	}
	public String toString(){
		String database = "";
		
		Collection<InputVector> values = inputSet.values();
		
		for(Iterator<InputVector> i = values.iterator();i.hasNext();){
			InputVector v = (InputVector) i.next();
			database += v.toString() + "\n";
		}
		
		return database;
	}
}
