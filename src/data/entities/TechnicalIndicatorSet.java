package data.entities;

import com.mongodb.DBObject;

public class TechnicalIndicatorSet {
	Double pvi;
	Double nvi;
	Double obv;
	Double typical_volume;
	Double price_volume_trend;
	Double a_d_oscillator;
	Double chaikins_oscillator;
	Double chaikins_volatility;
	Double acceleration;
	Double highest_high;
	Double lowest_low;
	Double relative_strength_index;
	Double macd_nine_period_moving_average;
	Double macd_line;
	Double momentum;
	Double s_o_k;
	Double s_o_d;
	Double typical_price;
	Double median_price;
	Double weighted_close;
	Double williams_r;
	Double bollinger_upper;
	Double bollinger_lower;
	Double bollinger_middle;
	Double moving_average_25;
	Double moving_average_65;
	
	public TechnicalIndicatorSet(DBObject t){
		
	}
}
