package data.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Dictionary;
import java.util.Iterator;
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
	
	public MongoStockDatabase() throws UnknownHostException{
		this("djia");
	}
	public MongoStockDatabase(String index) throws UnknownHostException{
		mongo = new MongoClient();
		createTableFromFile(index);
	}
	
	@Override
	public StockRecord getRecordFromIndex(SimpleDate date, String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockRecord getRecordFromIndex(int number, String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockRecord> getRecordFromPeriod(SimpleDate start, SimpleDate end, String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockRecord getRecordFromToday(String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockRecord getRecordFromYesterday(String index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertDailyRecord(String index, InputVector inputSet) {
		DBObject document = new BasicDBObject();
		document.put("date", inputSet.getDay().toString());
		document.put("daily_open", inputSet.getOpen());
		document.put("daily_high", inputSet.getHigh());
		document.put("daily_low", inputSet.getLow());
		document.put("daily_close", inputSet.getClose());
		document.put("trading_volume", inputSet.getVolume());
		document.put("daily_adjclose", inputSet.getAdjClose());
		DB stockDB = mongo.getDB("stocks");
		DBCollection collection = stockDB.getCollection(index.toUpperCase());
		collection.insert(document);
		
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
	
	//These methods are used in setup testing
	
	@Override
	public void deleteEntireIndex(String index) {
		DB stockDB = mongo.getDB("stocks");
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
		DB stockDB = mongo.getDB("stocks");
		DBCursor c = stockDB.getCollection(index.toUpperCase()).find();
		while(c.hasNext()){
			System.out.println(c.next());
		}
	}

}
