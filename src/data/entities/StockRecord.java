package data.entities;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import data.entities.enums.HelperIndicator;
import data.entities.enums.Indicator;

public class StockRecord {
	String _id;
	String stockIndex;
	SimpleDate date;
	Long recordId;
	InputVector dailyInput;
	Long yesterdaysRecordId;
	TechnicalIndicatorSet technicalIndicators = null;
	HelperDataSet helperIndicators = null;
	
	public StockRecord(){
		
	}
	public StockRecord(DBObject obj){
		stockIndex = (String) obj.get("stock_index");
		date = new SimpleDate((String) obj.get("date"));
		recordId = (Long) obj.get("record_id");
		
		DBObject di = (DBObject) obj.get("daily_input");
		
		dailyInput = new InputVector();
		
		dailyInput.setOpen((Double) di.get("daily_open"));
		dailyInput.setClose((Double) di.get("daily_close"));
		dailyInput.setHigh((Double) di.get("daily_high"));
		dailyInput.setLow((Double) di.get("daily_low"));
		dailyInput.setVolume((Double) di.get("trading_volume"));
		dailyInput.setAdjClose((Double) di.get("daily_adjclose"));
		
		yesterdaysRecordId = (Long) obj.get("yesterdays_record");
		
		if(obj.containsField("helper_data")){
			helperIndicators = new HelperDataSet((DBObject) obj.get("helper_data"));
		}
			
		if(obj.containsField("technical_indicators")){
			technicalIndicators = new TechnicalIndicatorSet((DBObject) obj.get("technical_indicators"));
		}
	}
	
	public long getId(){return recordId;}
	
	public long getYesterdayId(){return yesterdaysRecordId;}
	
	public double getOpen(){return dailyInput.getOpen();}
	
	public double getClose(){return dailyInput.getClose();}
	
	public double getHigh(){return dailyInput.getHigh();}
	
	public double getLow(){return dailyInput.getLow();}
	
	public double getVolume(){return dailyInput.getVolume();}
	
	public double getAdjClose(){return dailyInput.getAdjClose();}
	
	public SimpleDate getDate(){return date;}
	
	public InputVector getAllDailyInputs(){return dailyInput;}
	
	public TechnicalIndicatorSet getAllTechnicalIndicators(){return technicalIndicators;}
	
	public HelperDataSet getHelperIndicators(){return helperIndicators;}
	
	public boolean hasAnyTechnicalIndicators(){
		if(technicalIndicators == null){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean hasAnyHelperIndicators(){
		if(helperIndicators == null){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean hasTechnicalIndicator(Indicator t){
		if(technicalIndicators == null){
			return false;
		}
		else{
			return technicalIndicators.hasIndicator(t);
		}
	}
	public boolean hasHelperIndicator(HelperIndicator h){
		if(helperIndicators == null){
			return false;
		}
		else{
			return helperIndicators.hasIndicator(h);
		}
	}
	public Double getHelperIndicator(HelperIndicator h){
		return helperIndicators.getIndicator(h);
	}
	public Double getTechicalIndicator(Indicator i){
		return technicalIndicators.getIndicator(i);
	}
	public DBObject getAllChanges(){
		BasicDBObject changes = new BasicDBObject();
		//go through helper indicators
		Map<String,Double> hChanges = helperIndicators.getAllChanges();
		for(Map.Entry<String, Double> h : hChanges.entrySet()){
			changes.append("helper_data."+h.getKey(), h.getValue());
		}
		//go through technical indicators
		Map<String,Double> tChanges = technicalIndicators.getAllChanges();
		for(Map.Entry<String, Double> t : tChanges.entrySet()){
			changes.append("technical_indicators."+t.getKey(), t.getValue());
		}
		
		BasicDBObject newDoc = new BasicDBObject("$set",changes);
		return newDoc;
	}
}
