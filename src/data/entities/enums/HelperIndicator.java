package data.entities.enums;

public enum HelperIndicator {
	EXPONENTIAL_MOVING_AVERAGE(0,"ema"),
	SIMPLE_MOVING_AVERAGE(1,"sma"),
	ACCUMULATION_DISTRIBUTION_LINE(2, "adl"),
	TRUE_RANGE_HIGH(3, "true_range_high"),
	TRUE_RANGE_LOW(4, "true_range_low"),
	TODAYS_ACCUMULATION_DISTRIBUTION(5, "todays_accumulation_distribution"),
	RELATIVE_STRENGTH(6, "relative_strength"),
	UNNAMED_OSCILLATOR(7,"unnamed_oscillator");
	
	private int value;
	private String key;
	private HelperIndicator(int value, String key){
		this.value = value;
		this.key = key;
	}
	public int getValue(){
		return value;
	}
	public String getKey(){
		return key;
	}
}
