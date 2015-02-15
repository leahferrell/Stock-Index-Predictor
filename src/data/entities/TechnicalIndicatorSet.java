package data.entities;

import java.util.Map;
import java.util.TreeMap;

import com.mongodb.DBObject;

import data.entities.enums.Indicator;

public class TechnicalIndicatorSet {
	static int numOfIndicators = Indicator.values().length;
	Double[] dataSet = new Double[numOfIndicators];
	Map<String, Double> changeSet = new TreeMap<String, Double>();
	
	public TechnicalIndicatorSet(){
		
	}
	
	public TechnicalIndicatorSet(DBObject t){
		for(Indicator i : Indicator.values()){
			if(t.containsField(i.getKey())){
				dataSet[i.getValue()] = (Double) t.get(i.getKey());
			}
		}
	}
	
	public Map<String, Double> getAllChanges(){
		return changeSet;
	}
	
	public void updateIndicator(Indicator h, Double v){
		dataSet[h.getValue()] = v;
		changeSet.put(h.getKey(), v);
	}
	public Double getIndicator(Indicator h){
		return dataSet[h.getValue()];
	}
	public boolean hasIndicator(Indicator h){
		if(dataSet[h.getValue()] != null){
			return true;
		}
		else{
			return false;
		}
	}
}
