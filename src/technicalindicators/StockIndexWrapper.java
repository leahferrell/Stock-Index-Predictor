package technicalindicators;
import java.net.UnknownHostException;

import data.db.MongoStockDatabase;
import data.entities.StockRecord;
import data.entities.enums.HelperIndicator;
import data.entities.enums.Indicator;
import data.entities.enums.TradingIndex;


public class StockIndexWrapper {
	private TradingIndex index;
	private MongoStockDatabase db;
	private long dayId = 1;	//TODO
	private StockRecord todayRecord;
	private StockRecord yesterdayRecord;
	public StockIndexWrapper(TradingIndex i){
		index = i;
		setUpDatabase();
	}
	private void setUpDatabase(){
		try {
			db = new MongoStockDatabase();
			todayRecord = db.getRecordFromIndex(dayId, index.getDatabaseName());
			yesterdayRecord = db.getRecordFromIndex(dayId-1, index.getDatabaseName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public Double getVolumeToday(){
		return todayRecord.getVolume();
	}
	public Double getVolumeYesterday(){
		if(yesterdayRecord == null){
			return null;
		}
		else{
			return yesterdayRecord.getVolume();
		}
	}
	public Double getPVIYesterday(){
		if(yesterdayRecord == null){
			return null;
		}
		else if(yesterdayRecord.hasTechnicalIndicator(Indicator.POSITIVE_VOLUME_INDEX)){
			return yesterdayRecord.getTechicalIndicator(Indicator.POSITIVE_VOLUME_INDEX);
		}
		else{
			return null;
		}
	}
	public Double getCloseToday(){
		return todayRecord.getClose();
	}
	public Double getCloseYesterday(){
		if(yesterdayRecord == null){
			return null;
		}
		else{
			return yesterdayRecord.getClose();
		}
	}
	public Double getCloseFromDay(int daysPrevious){
		long day;
		int id = (int) todayRecord.getId();
		if(daysPrevious > id){
			day = 1;
		}
		else{
			day = id - (daysPrevious-1);
		}
		StockRecord s = db.getRecordFromIndex(day, index.getDatabaseName());
		if(s != null)
			return s.getClose();
		else
			return null;
	}
	public Double[] getCloseInPeriod(int numOfDays){
		//TODO
		Double[] closeSet = new Double[numOfDays];
		return closeSet;
	}
	public Double getOBVYesterday(){
		Indicator i = Indicator.ONBALANCE_VOLUME;
		if(yesterdayRecord == null){
			return null;
		}
		else if(yesterdayRecord.hasTechnicalIndicator(i)){
			return yesterdayRecord.getTechicalIndicator(i);
		}
		else{
			return null;
		}
	}
	public Double[] getVolumeInPeriod(int period){
		Double[] volumeSet;
		int setSize;
		long id = todayRecord.getId();
		if(period < todayRecord.getId())
			setSize = period;
		else
			setSize = (int)id;
		
		volumeSet = new Double[setSize];
		int count = 0;
		for(int i = setSize-1; i >= 0; i--){
			volumeSet[count] = db.getRecordFromIndex(id - i, index.getDatabaseName()).getVolume();
			count++;
		}
		
		return volumeSet;
	}
	public Double getPVTYesterday(){
		Indicator i = Indicator.PRICE_VOLUME_TREND;
		if(yesterdayRecord == null){
			return null;
		}
		else if(yesterdayRecord.hasTechnicalIndicator(i)){
			return yesterdayRecord.getTechicalIndicator(i);
		}
		else{
			return null;
		}
	}
	public Double getLowToday(){
		return todayRecord.getLow();
	}
	public Double getHighToday(){
		return todayRecord.getHigh();
	}
	public Double getHighFromDay(int day){
		StockRecord s = db.getRecordFromIndex(day, index.getDatabaseName());
		if(s != null)
			return s.getHigh();
		else
			return null;
	}
	public Double getLowFromDay(int day){
		StockRecord s = db.getRecordFromIndex(day, index.getDatabaseName());
		if(s != null)
			return s.getLow();
		else
			return null;
	}
	public Double[] getHighInPeriod(int period){
		Double[] highSet;
		int setSize;
		long id = todayRecord.getId();
		if(period < todayRecord.getId())
			setSize = period;
		else
			setSize = (int)id;
		
		highSet = new Double[setSize];
		int count = 0;
		for(int i = setSize-1; i >= 0; i--){
			highSet[count] = db.getRecordFromIndex(id - i, index.getDatabaseName()).getHigh();
			count++;
		}
		
		return highSet;
	}
	public Double[] getLowInPeriod(int period){
		Double[] lowSet;
		int setSize;
		long id = todayRecord.getId();
		if(period < todayRecord.getId())
			setSize = period;
		else
			setSize = (int)id;
		
		lowSet = new Double[setSize];
		int count = 0;
		for(int i = setSize-1; i >= 0; i--){
			lowSet[count] = db.getRecordFromIndex(id - i, index.getDatabaseName()).getLow();
			count++;
		}
		
		return lowSet;
	}
	public Double getMedianPriceToday(){
		if(todayRecord.hasTechnicalIndicator(Indicator.MEDIAN_PRICE)){
			return todayRecord.getTechicalIndicator(Indicator.MEDIAN_PRICE);
		}
		else
			return null;
	}
	public Double[] getMedianPriceInPeriod(int days){
		//TODO
		Double[] prices = new Double[days];
		return prices;
	}
	public Double getWilliamsADYesterday(){
		Indicator i = Indicator.WILLIAMS_ACCUMULATION_DISTRIBUTION;
		if(yesterdayRecord == null){
			return null;
		}
		else if(yesterdayRecord.hasTechnicalIndicator(i)){
			return yesterdayRecord.getTechicalIndicator(i);
		}
		else{
			return null;
		}
	}
	public Double getEMAYesterday(){
		HelperIndicator i = HelperIndicator.EXPONENTIAL_MOVING_AVERAGE;
		if(yesterdayRecord == null){
			return null;
		}
		else if(yesterdayRecord.hasHelperIndicator(i)){
			return yesterdayRecord.getHelperIndicator(i);
		}
		else{
			return null;
		}
	}
	public Double getMACDLine(){
		if(todayRecord.hasTechnicalIndicator(Indicator.MACD_LINE)){
			return todayRecord.getTechicalIndicator(Indicator.MACD_LINE);
		}
		else
			return null;
	}
	public Double[] getOscillatorInPeriod(int days){
		//TODO
		Double[] osc = new Double[days];
		return osc;
	}
	public Double[] getKInPeriod(int period){
		//TODO
		Double[] kSet = new Double[period];
		return kSet;
	}
	public Double getADOscillatorToday(){
		if(todayRecord.hasTechnicalIndicator(Indicator.ACCUMULATION_DISTRIBUTION_OSCILLATOR)){
			return todayRecord.getTechicalIndicator(Indicator.ACCUMULATION_DISTRIBUTION_OSCILLATOR);
		}
		else
			return null;
	}
	public Double getADLYesterday(){
		HelperIndicator i = HelperIndicator.ACCUMULATION_DISTRIBUTION_LINE;
		if(yesterdayRecord == null){
			return null;
		}
		else if(yesterdayRecord.hasHelperIndicator(i)){
			return yesterdayRecord.getHelperIndicator(i);
		}
		else{
			return null;
		}
	}
	public void setTechnicalIndicator(Indicator i, Double value){
		todayRecord.setTechnicalIndicator(i, value);
	}
	
	public void setHelperIndicator(HelperIndicator h, Double value){
		todayRecord.setHelperIndicator(h,value);
	}
	public void setMACDLine(Double macd){
		
	}
	public void setEMAToday(Double ema){
		
	}
	public void setWilliamsADToday(Double ad){
		
	}
	public void setMedianPriceToday(Double med){
		
	}
	public void setPVTToday(Double pvt){
		
	}
	public void setOBVToday(Double obv){
		
	}
	public void setPVIToday(Double pvi){
		
	}
	public void setOscillatorToday(Double osc){
		
	}
	public void setKToday(Double k){
		
	}
	public void setADOscillator(Double ad){
		
	}
	public void setADLToday(Double ald){
		
	}
	public void updateRecord(){
		//TODO
		db.updateStockRecord(todayRecord);
	}
}
