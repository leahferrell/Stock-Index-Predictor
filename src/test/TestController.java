package test;

import java.net.UnknownHostException;

import technicalindicators.Computation;
import data.dailyinputs.*;
//import data.*;
import data.db.*;
import data.entities.InputVector;
import data.entities.SimpleDate;
import data.entities.StockRecord;
import data.entities.enums.TradingIndex;


public class TestController {
	private static String index = "djia";
	private static Computation c = new Computation(TradingIndex.DOW_JONES_INDUSTRIAL_AVERAGE);
	public static void tryInsertRecord(){
		try {
			StockDatabase db = new MongoStockDatabase();
			//db.createTableFromFile(index);
			//db.deleteEntireIndex(index);
			DailyInputClient inputClient = new FlatDailyInputClient();
			inputClient.connectToServer();
			
			InputVector input1 = inputClient.getInputForIndex(new SimpleDate(12,"Jan",15));
			db.insertDailyRecord(index, input1);
			
			InputVector input2 = inputClient.getInputForIndex(new SimpleDate(13,"Jan",15));
			db.insertDailyRecord(index, input2);
			db.printAllRecords(index);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static void createDatabase(){
		try {
			StockDatabase db = new MongoStockDatabase();
			db.createTableFromFile(index);
			db.printAllRecords(index);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static void tryGetRecord(){
		try {
			StockDatabase db = new MongoStockDatabase();
			db.printAllRecords(index);
			
			StockRecord test = db.getRecordFromToday(index);
			
			System.out.println(test.getId());
			System.out.println(test.getYesterdayId());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static void tryUpdateRecord(){
		try {
			StockDatabase db = new MongoStockDatabase();
			//db.printAllRecords(index);
			
			StockRecord test = db.getRecordFromToday(index);
			
			String key = "nvi";
			double value = 11.0;
			
			db.addTechnicalIndicatorToRecord(index, test.getId(), key, value);
			db.printAllRecords(index);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static void tryDailyInputClient(){
		DailyInputClient inputClient = new FlatDailyInputClient();
		inputClient.connectToServer();
		for(int i = 12; i < 32; i++){
			InputVector inputs = inputClient.getInputForIndex(new SimpleDate(i,"Jan",15));
			if(inputs != null){
				System.out.println(inputs.toString());
			}
		}
		for(int i = 1; i < 10; i++){
			InputVector inputs = inputClient.getInputForIndex(new SimpleDate(i,"Feb",15));
			if(inputs != null){
				System.out.println(inputs.toString());
			}
		}
	}
	
	public static void tryComputationPVI(){
		double pvi = c.positiveVolumeIndex();
		System.out.println("PVI = " + pvi);
	}
	
	public static void tryComputationOBV(){
		double obv = c.onBalanceVolume();
		System.out.println("OBV = " + obv);
	}
	
	public static void tryComputationTV(){
		double tv = c.typicalVolume();
		System.out.println("TV = " + tv);
	}
	
	public static void tryComputationUpdate(){
		c.storeComputations();
		try {
			MongoStockDatabase db = new MongoStockDatabase();
			//db.printAllRecords(index);
			for(int i = 10; i <= 12; i++)
				db.printRecord(index, new Long(i));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		//tryComputationPVI();
		//tryComputationOBV();
		tryComputationTV();
		tryComputationUpdate();
	}
}
