package data;

import java.util.Dictionary;
import java.util.List;

import technicalindicators.TradingDataSet;

public interface StockDatabaseInterface {
	/*
	 * CREATE
	 * */
	public void createTableFromFile(String index);
	
	public void insertDailyRecord(String index, InputVector inputSet);
	
	public void insertAllDailyRecords(Dictionary<String,InputVector> inputSets);
	
	/*
	 * RETRIEVE
	 * */
	
	public StockRecord getRecordFromIndex(SimpleDate date, String index);
	
	public StockRecord getRecordFromIndex(int number, String index);
	
	public List<StockRecord> getRecordFromPeriod(SimpleDate start, SimpleDate end, String index);
	
	public StockRecord getRecordFromToday(String index);
	
	public StockRecord getRecordFromYesterday(String index);
	
	public void printAllRecords(String index);
	
	/*
	 * UPDATE
	 * */
	public void addIndicatorData(String index, TradingDataSet tradingData);
	
	/*
	 * DELETE
	 * */
	public void deleteEntireIndex(String index);
}
