package data.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import technicalindicators.TradingDataSet;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import data.entities.InputVector;
import data.entities.SimpleDate;
import data.entities.StockRecord;

public class MongoStockDatabase implements StockDatabase {
	private MongoClient mongo;
	private DB stockDB;
	
	public MongoStockDatabase() throws UnknownHostException{
		//this("djia");
		mongo = new MongoClient();
		stockDB = mongo.getDB("stocks");
	}
	public MongoStockDatabase(String index) throws UnknownHostException{
		mongo = new MongoClient();
		createTableFromFile(index);
	}
	
	@Override
	public StockRecord getRecordFromIndex(SimpleDate date, String index) {
		DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		BasicDBObject query = new BasicDBObject("date",date.getOriginalDateForm());
		DBCursor c = collection.find(query);
		if(c.hasNext()){
			DBObject todaysRecord = c.next();
			StockRecord record = new StockRecord(todaysRecord);
			return record;
		}
		else
			return null;
	}

	@Override
	public StockRecord getRecordFromIndex(long number, String index) {
		DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		BasicDBObject query = new BasicDBObject("record_id",number);
		DBCursor c = collection.find(query);
		if(c.hasNext()){
			DBObject todaysRecord = c.next();
			StockRecord record = new StockRecord(todaysRecord);
			return record;
		}
		else
			return null;
	}

	@Override
	public List<StockRecord> getRecordFromPeriod(SimpleDate start, SimpleDate end, String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockRecord getRecordFromToday(String index) {
		//DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		long recordId = collection.count();
		return getRecordFromIndex(recordId,index);
	}

	@Override
	public StockRecord getRecordFromYesterday(String index) {
		//DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		long recordId = collection.count()-1;
		return getRecordFromIndex(recordId,index);
	}

	@Override
	public void insertDailyRecord(String index, InputVector inputSet) {
		DBObject stock_record = new BasicDBObject();
		DBObject daily_input = new BasicDBObject();
		
		long yesterdayRecordId = getCurrentRecordId(index);
		
		stock_record.put("stock_index", index);
		stock_record.put("record_id", yesterdayRecordId+1);
		stock_record.put("yesterdays_record", yesterdayRecordId);
		stock_record.put("date", inputSet.getDay().getOriginalDateForm());
		
		daily_input.put("daily_open", inputSet.getOpen());
		daily_input.put("daily_high", inputSet.getHigh());
		daily_input.put("daily_low", inputSet.getLow());
		daily_input.put("daily_close", inputSet.getClose());
		daily_input.put("trading_volume", inputSet.getVolume());
		daily_input.put("daily_adjclose", inputSet.getAdjClose());
		
		stock_record.put("daily_input", daily_input);
		
		//DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		collection.insert(stock_record);
		
	}
	
	private long getCurrentRecordId(String index){
		//DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		return collection.count();
	}
	
	@Override
	public void insertAllDailyRecords(Map<String, InputVector> inputSets) {
		for(Map.Entry<String, InputVector> i : inputSets.entrySet()){
			insertDailyRecord(i.getKey(), i.getValue());
		}
		
	}

	@Override
	public void addIndicatorData(String index, TradingDataSet tradingData) {
		// TODO Auto-generated method stub
		
	}
	
	public void addTechnicalIndicatorToRecord(String index, long recordId, String key, double value){
		//DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		BasicDBObject query = new BasicDBObject("record_id",recordId);
		BasicDBObject newDoc = new BasicDBObject("$set",new BasicDBObject("technical_indicator."+key,value));
		collection.update(query, newDoc);
		
	}
	
	public void addTechnicalIndicatorToRecord(String index, SimpleDate date, String key, double value){
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		BasicDBObject query = new BasicDBObject("date",date.getOriginalDateForm());
		BasicDBObject newDoc = new BasicDBObject("$set",new BasicDBObject("technical_indicator."+key,value));
		collection.update(query, newDoc);
	}
	
	public void addHelperDataToRecord(String index, long recordId, String key, double value){
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		BasicDBObject query = new BasicDBObject("record_id",recordId);
		BasicDBObject newDoc = new BasicDBObject("$set",new BasicDBObject("helper_data."+key,value));
		collection.update(query, newDoc);
	}
	
	public void addHelperDataToRecord(String index, SimpleDate date, String key, double value){
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		BasicDBObject query = new BasicDBObject("date",date.getOriginalDateForm());
		BasicDBObject newDoc = new BasicDBObject("$set",new BasicDBObject("helper_data."+key,value));
		collection.update(query, newDoc);
	}
	
	/*
	 * These methods will be deleted after testing is done.
	 * 
	 * */
	
	@Override
	public void deleteEntireIndex(String index) {
		//DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		collection.drop();
	}
	
	private InputVector parseData(String[] inputs){
		InputVector newInput = new InputVector();
		newInput.setDay(inputs[0]);
		newInput.setOpen(inputs[1]);
		newInput.setHigh(inputs[2]);
		newInput.setLow(inputs[3]);
		newInput.setClose(inputs[4]);
		newInput.setVolume(inputs[5]);
		newInput.setAdjClose(inputs[6]);
		
		return newInput;
	}
	
	@Override
	public void createTableFromFile(String indexName) {
		String inputs = "./data-files/" + indexName + "-inputs.csv";
		//String indicators = "./data-files/" + indexName + "-indicators.csv";
		
		String line = "";
		boolean firstLine = true;
		try{
			BufferedReader indexFile = new BufferedReader(new FileReader(inputs));
			while ((line = indexFile.readLine()) != null) {
				if(firstLine){
					firstLine = false;
					continue;
				}
				InputVector newInput = parseData(line.split(","));
				insertDailyRecord(indexName, newInput);
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
	public void printAllRecords(String index){
		//DB stockDB = mongo.getDB("stocks");
		DBCursor c = stockDB.getCollection(index.toUpperCase()).find();
		while(c.hasNext()){
			Map<?,?> record = c.next().toMap();
			System.out.print("{");
			for(Map.Entry<?, ?> entry : record.entrySet()){
				System.out.println("\""+entry.getKey()+"\":"+entry.getValue());
			}
			System.out.println("}");
		}
	}

}
