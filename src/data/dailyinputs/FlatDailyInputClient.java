package data.dailyinputs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

import data.entities.InputVector;
import data.entities.SimpleDate;

/**
 * This class is a way to test the daily input functionality without connecting to an external api for the inputs.
 * In the final version of this project, it probably won't be here.
 * */

public class FlatDailyInputClient implements DailyInputClient {
	TreeMap<String,InputVector> dailyInputs = new TreeMap<String,InputVector>();
	
	
	@Override
	public void connectToServer() {
		connectToServer("./data-files/daily-inputs.csv");
		
	}

	@Override
	public void connectToServer(String serverLocation) {
		String line = "";
		boolean firstLine = true;
		try{
			BufferedReader indexFile = new BufferedReader(new FileReader(serverLocation));
			while ((line = indexFile.readLine()) != null) {
				if(firstLine){
					firstLine = false;
					continue;
				}
				Map.Entry<String, InputVector> newInput = parseData(line.split(","));
				dailyInputs.put(newInput.getKey(),newInput.getValue());
			}
			indexFile.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Can't read file");
		}
		
	}

	@Override
	public InputVector getInputForIndex() {
		return getInputForIndex(new SimpleDate(12,"Jan",15));
	}

	@Override
	public InputVector getInputForIndex(String date) {
		return getInputForIndex(date);
	}

	@Override
	public InputVector getInputForIndex(SimpleDate date) {
		return dailyInputs.get(date.getOriginalDateForm());
	}

	@Override
	public void disconnect() {
	}
	
	private Map.Entry<String, InputVector> parseData(String[] inputs){
		String day = inputs[0];
		InputVector newInput = new InputVector();
		newInput.setDay(day);
		newInput.setOpen(inputs[1]);
		newInput.setHigh(inputs[2]);
		newInput.setLow(inputs[3]);
		newInput.setClose(inputs[4]);
		newInput.setVolume(inputs[5]);
		newInput.setAdjClose(inputs[6]);
		
		Map.Entry<String, InputVector> dailyInput = new AbstractMap.SimpleEntry<String, InputVector>(day, newInput);
		
		return dailyInput;
	}

}
