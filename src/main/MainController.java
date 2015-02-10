package main;

import java.net.UnknownHostException;

//import data.*;
import data.db.*;


public class MainController {
	public static void main(String[] args){
		try {
			StockDatabase db = new MongoStockDatabase();
			//db.createTableFromFile("djia");
			db.deleteEntireIndex("djia");
			db.printAllRecords("djia");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
