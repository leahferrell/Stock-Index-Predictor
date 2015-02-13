package data.db;

import java.util.List;
import java.util.Map;

import data.entities.InputVector;
import data.entities.SimpleDate;
import data.entities.StockRecord;
//import technicalindicators.TradingDataSet;

public interface StockDatabase {
	/*
	 * CREATE
	 * */
	public void createTableFromFile(String index);
	
	public void insertDailyRecord(String index, InputVector inputSet);
	
	public void insertAllDailyRecords(Map<String,InputVector> inputSets);
	
	/*
	 * RETRIEVE
	 * */
	
	public StockRecord getRecordFromIndex(SimpleDate date, String index);
	
	public StockRecord getRecordFromIndex(long number, String index);
	
	public List<StockRecord> getRecordFromPeriod(SimpleDate start, SimpleDate end, String index);
	
	public StockRecord getRecordFromToday(String index);
	
	public StockRecord getRecordFromYesterday(String index);
	
	public void printAllRecords(String index);
	
	/*
	 * UPDATE
	 * */
	//public void addIndicatorData(String index, TradingDataSet tradingData);
	
	public void addTechnicalIndicatorToRecord(String index, long recordId, String key, double value);
	
	public void addTechnicalIndicatorToRecord(String index, SimpleDate date, String key, double value);
	
	public void addHelperDataToRecord(String index, long recordId, String key, double value);
	
	public void addHelperDataToRecord(String index, SimpleDate date, String key, double value);
	
	/*
	 * DELETE
	 * */
	public void deleteEntireIndex(String index);
}
