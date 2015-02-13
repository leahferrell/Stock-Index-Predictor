package data.entities;

import java.util.Map;
import java.util.TreeMap;

import com.mongodb.DBObject;

import data.entities.enums.HelperIndicator;

public class HelperDataSet {
	static int numOfIndicators = HelperIndicator.values().length;
	Double[] dataSet = new Double[numOfIndicators];
	Map<String, Double> changeSet = new TreeMap<String, Double>();
	
	public HelperDataSet(DBObject h){
		for(HelperIndicator i : HelperIndicator.values()){
			if(h.containsField(i.getKey())){
				dataSet[i.getValue()] = (Double) h.get(i.getKey());
			}
		}
	}
	
	public Map<String, Double> getAllChanges(){
		return changeSet;
	}
	
	public void updateIndicator(HelperIndicator h, Double v){
		dataSet[h.getValue()] = v;
		changeSet.put(h.getKey(), v);
	}
	public Double getIndicator(HelperIndicator h){
		return dataSet[h.getValue()];
	}
	public boolean hasIndicator(HelperIndicator h){
		if(dataSet[h.getValue()] != null){
			return true;
		}
		else{
			return false;
		}
	}
}