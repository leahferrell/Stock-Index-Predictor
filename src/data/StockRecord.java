package data;
import technicalindicators.TradingDataSet;

public class StockRecord {
	String _id;
	String stockIndex;
	SimpleDate date;
	String recordId;
	InputVector dailyInput;
	String yesterdaysRecordId;
	TradingDataSet tradingData;
}
