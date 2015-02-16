package technicalindicators;
import java.net.UnknownHostException;
import java.util.ArrayList;

import data.db.MongoStockDatabase;
import data.entities.StockRecord;
import data.entities.TechnicalIndicatorSet;
import data.entities.enums.HelperIndicator;
import data.entities.enums.Indicator;
import data.entities.enums.TradingIndex;


public class StockIndexWrapper {
	private TradingIndex index;
	private MongoStockDatabase db;
	//private long dayId = 65;
	private StockRecord todayRecord;
	private StockRecord yesterdayRecord;
	public StockIndexWrapper(TradingIndex i){
		index = i;
		setUpDatabase();
	}
	private void setUpDatabase(){
		try {
			db = new MongoStockDatabase();
			//todayRecord = db.getRecordFromIndex(dayId, index.getDatabaseName());
			//yesterdayRecord = db.getRecordFromIndex(dayId-1, index.getDatabaseName());
			todayRecord = db.getRecordFromToday(index.getDatabaseName());
			yesterdayRecord = db.getRecordFromYesterday(index.getDatabaseName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public TechnicalIndicatorSet getTechnicalIndicatorSet(){
		return todayRecord.getAllTechnicalIndicators();
	}
	public StockRecord getTodaysRecord(){
		return todayRecord;
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
		Double[] closeSet;
		int setSize;
		long id = todayRecord.getId();
		if(numOfDays < todayRecord.getId())
			setSize = numOfDays;
		else
			setSize = (int)id;
		
		closeSet = new Double[setSize];
		int count = 0;
		for(int i = setSize-1; i >= 0; i--){
			closeSet[count] = db.getRecordFromIndex(id - i, index.getDatabaseName()).getClose();
			count++;
		}
		
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
		Double[] priceSet;
		int setSize;
		long id = todayRecord.getId();
		if(days < todayRecord.getId())
			setSize = days;
		else
			setSize = (int)id;
		
		priceSet = new Double[setSize];
		int count = 0;
		for(int i = setSize-1; i >= 0; i--){
			StockRecord s = db.getRecordFromIndex(id - i, index.getDatabaseName());
			if(s.hasTechnicalIndicator(Indicator.MEDIAN_PRICE))
				priceSet[count] = s.getTechicalIndicator(Indicator.MEDIAN_PRICE);
			else
				priceSet[count] = null;
			count++;
		}
		
		return priceSet;
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
	public Double getMACDNinePeriodMovingAverageYesterday(){
		if(todayRecord.hasTechnicalIndicator(Indicator.MACD_NINE_PERIOD_MOVING_AVERAGE)){
			return todayRecord.getTechicalIndicator(Indicator.MACD_NINE_PERIOD_MOVING_AVERAGE);
		}
		else
			return null;
	}
	
	public Double[] getOscillatorInPeriod(int days){
		ArrayList<Double> oscSet = new ArrayList<Double>();
		long id = todayRecord.getId();
		int size;
		if(days > id){
			size = (int)id;
		}
		else{
			size = days;
		}
		
		for(int i = size-1; i>=0; i--){
			StockRecord r = db.getRecordFromIndex(id-i, index.getDatabaseName());
			if(r.hasHelperIndicator(HelperIndicator.UNNAMED_OSCILLATOR)){
				oscSet.add(r.getHelperIndicator(HelperIndicator.UNNAMED_OSCILLATOR));
			}
		}
		Double[] newArray = new Double[oscSet.size()];
		
		return oscSet.toArray(newArray);
	}
	public Double[] getKInPeriod(int period){
		ArrayList<Double> kSet = new ArrayList<Double>();
		long id = todayRecord.getId();
		int size;
		if(period > id){
			size = (int)id;
		}
		else{
			size = period;
		}
		
		for(int i = size-1; i>=0; i--){
			StockRecord r = db.getRecordFromIndex(id-i, index.getDatabaseName());
			if(r.hasTechnicalIndicator(Indicator.STOCHASTIC_OSCILLATOR_K)){
				kSet.add(r.getTechicalIndicator(Indicator.STOCHASTIC_OSCILLATOR_K));
			}
		}
		Double[] newArray = new Double[kSet.size()];
		
		return kSet.toArray(newArray);
	}
	public Double[] getMACDLineInPeriod(int period){
		ArrayList<Double> mSet = new ArrayList<Double>();
		long id = todayRecord.getId();
		int size;
		if(period > id){
			size = (int)id;
		}
		else{
			size = period;
		}
		
		for(int i = size-1; i>=0; i--){
			StockRecord r = db.getRecordFromIndex(id-i, index.getDatabaseName());
			if(r.hasTechnicalIndicator(Indicator.MACD_LINE)){
				mSet.add(r.getTechicalIndicator(Indicator.MACD_LINE));
			}
		}
		Double[] newArray = new Double[mSet.size()];
		
		return mSet.toArray(newArray);
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
	public void updateRecord(){
		db.updateStockRecord(todayRecord);
	}
}
