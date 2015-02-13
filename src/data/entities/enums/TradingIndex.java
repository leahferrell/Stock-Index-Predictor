package data.entities.enums;

public enum TradingIndex {
	DOW_JONES_INDUSTRIAL_AVERAGE(1, "DJIA"),
	NEW_YORK_STOCK_EXCHANGE(2, "NYSE");
	private int value;
	private String name;
	private TradingIndex(int value, String name){
		this.value = value;
		this.name = name;
	}
	public int getValue(){
		return value;
	}
	public String getDatabaseName(){
		return name;
	}
}
