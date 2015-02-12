package data.entities;
import com.mongodb.DBObject;

public class StockRecord {
	String _id;
	String stockIndex;
	SimpleDate date;
	Long recordId;
	InputVector dailyInput;
	Long yesterdaysRecordId;
	TechnicalIndicatorSet technicalIndicators;
	HelperDataSet helperIndicators;
	
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
	
	public InputVector getDailyInputs(){return dailyInput;}
	
	public TechnicalIndicatorSet getAllTechnicalIndicators(){return technicalIndicators;}
	
	public HelperDataSet getHelperIndicators(){return helperIndicators;}
	
	public boolean hasIndicators(){
		return false;
	}
}
