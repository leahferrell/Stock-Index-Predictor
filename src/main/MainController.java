package main;

import data.StockIndexDatabase2;

public class MainController {
	public static void main(String[] args){
		StockIndexDatabase2 db = new StockIndexDatabase2();
		db.LoadDatabase();
		System.out.println(db.toString());
		System.out.println(db.getRecordFromToday().toString());
		System.out.println(db.getRecordFromYesterday().toString());
	}
}
