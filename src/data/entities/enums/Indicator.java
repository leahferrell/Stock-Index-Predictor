package data.entities.enums;

public enum Indicator {
	POSITIVE_VOLUME_INDEX(0, "pvi"), 
	NEGATIVE_VOLUME_INDEX(1, "nvi"),
	ONBALANCE_VOLUME(2, "obv"),
	TYPICAL_VOLUME(3, "typical_volume"),
	PRICE_VOLUME_TREND(4, "price_volume_trend"),
	ACCUMULATION_DISTRIBUTION_OSCILLATOR(5, "a_d_oscillator"),
	CHAIKINS_OSCILLATOR(6, "chaikins_oscillator"),
	CHAIKINS_VOLATILITY(7, "chaikins_volatility"),
	ACCELERATION(8, "acceleration"),
	HIGHEST_HIGH(9, "highest_high"),
	LOWEST_LOW(10, "lowest_low"),
	RELATIVE_STRENGTH_INDEX(11, "relative_strength_index"),
	MACD_NINE_PERIOD_MOVING_AVERAGE(12, "macd_nine_period_moving_average"),
	MACD_LINE(13, "macd_line"),
	MOMENTUM(14, "momentum"),
	STOCHASTIC_OSCILLATOR_K(15, "s_o_k"),
	STOCKASTIC_OSCILLATOR_D(16, "s_o_d"),
	TYPICAL_PRICE(17, "typical_price"),
	MEDIAN_PRICE(18, "median_price"),
	WEIGHTED_CLOSE(19, "weighted_close"),
	WILLIAMS_R(20, "williams_r"),
	PRICE_RATE_OF_CHANGE(21, "price_rate_of_change"),
	WILLIAMS_ACCUMULATION_DISTRIBUTION(22, "williams_a_d"),
	BOLLINGER_UPPER(23, "bollinger_upper"),
	BOLLINGER_LOWER(24, "bollinger_lower"),
	BOLLINGER_MIDDLE(25, "bollinger_middle"),
	MOVING_AVERAGE_25(26, "moving_average_25"),
	MOVING_AVERAGE_65(27, "moving_average_65");
	
	private int value;
	private String key;
	private Indicator(int value, String key){
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