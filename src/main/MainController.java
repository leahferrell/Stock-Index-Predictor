package main;

import data.StockIndexDatabase;

public class MainController {
	public static void main(String[] args){
		StockIndexDatabase db = new StockIndexDatabase();
		db.LoadDatabase();
		System.out.println(db.toString());
		System.out.println(db.getRecordFromToday().toString());
		System.out.println(db.getRecordFromYesterday().toString());
	}
}
