package technicalindicators;

public enum TradingIndex {
	DOW_JONES_INDUSTRIAL_AVERAGE(1),
	NEW_YORK_STOCK_EXCHANGE(2);
	private int value;
	private TradingIndex(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public String getDatabaseName(){
		String name = null;
		switch(this){
			case DOW_JONES_INDUSTRIAL_AVERAGE:
				name = "DowJones.data";
			case NEW_YORK_STOCK_EXCHANGE:
				name = "NYSE.data";
		}
		return name;
	}
}
