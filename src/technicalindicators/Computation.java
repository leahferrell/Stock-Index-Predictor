package technicalindicators;

import data.entities.StockRecord;
import data.entities.TechnicalIndicatorSet;
import data.entities.enums.HelperIndicator;
import data.entities.enums.Indicator;
import data.entities.enums.TradingIndex;

public class Computation {
	private TradingIndex index;
	private StockIndexWrapper indexData;
	public Computation(TradingIndex i){
		index = i;
		indexData = new StockIndexWrapper(index);
	}
	/*public Double[] computeAll(){
		//TODO
		Double[] features = new Double[28];
		features[Indicator.POSITIVE_VOLUME_INDEX.getValue()] = positiveVolumeIndex();
		//features[Indicator.NEGATIVE_VOLUME_INDEX.getValue()] = negativeVolumeIndex();
		features[Indicator.NEGATIVE_VOLUME_INDEX.getValue()] = 0.0;
		features[Indicator.ONBALANCE_VOLUME.getValue()] = onBalanceVolume();
		features[Indicator.TYPICAL_VOLUME.getValue()] = typicalVolume();
		features[Indicator.PRICE_VOLUME_TREND.getValue()] = priceVolumeTrend();
		features[Indicator.ACCUMULATION_DISTRIBUTION_OSCILLATOR.getValue()] = accumulationDistributionOscillator();
		//features[Indicator.CHAIKINS_OSCILLATOR.getValue()] = chaikinsOscillator();
		features[Indicator.CHAIKINS_OSCILLATOR.getValue()] = 0.0;
		//features[Indicator.CHAIKINS_VOLATILITY.getValue()] = chaikinsVolatility();
		features[Indicator.CHAIKINS_VOLATILITY.getValue()] = 0.0;
		features[Indicator.ACCELERATION.getValue()] = acceleration();
		features[Indicator.HIGHEST_HIGH.getValue()] = highestHigh();
		features[Indicator.LOWEST_LOW.getValue()] = lowestLow();
		//features[Indicator.RELATIVE_STRENGTH_INDEX.getValue()] = relativeStrengthIndex();
		features[Indicator.RELATIVE_STRENGTH_INDEX.getValue()] = 0.0;
		//features[Indicator.MACD_LINE.getValue()] = macdLine();
		features[Indicator.RELATIVE_STRENGTH_INDEX.getValue()] = 0.0;
		//features[Indicator.MACD_NINE_PERIOD_MOVING_AVERAGE.getValue()] = macdNinePeriodMovingAverage();
		features[Indicator.RELATIVE_STRENGTH_INDEX.getValue()] = 0.0;
		features[Indicator.MOMENTUM.getValue()] = momentum();
		features[Indicator.STOCHASTIC_OSCILLATOR_K.getValue()] = stochasticOscillatorK();
		features[Indicator.STOCKASTIC_OSCILLATOR_D.getValue()] = stochasticOscillatorD();
		features[Indicator.TYPICAL_PRICE.getValue()] = typicalPrice();
		features[Indicator.MEDIAN_PRICE.getValue()] = medianPrice();
		features[Indicator.WEIGHTED_CLOSE.getValue()] = weightedClose();
		features[Indicator.WILLIAMS_R.getValue()] = williamsR();
		features[Indicator.PRICE_RATE_OF_CHANGE.getValue()] = priceRateOfChange();
		features[Indicator.WILLIAMS_ACCUMULATION_DISTRIBUTION.getValue()] = williamsAccumulationDistribution();
		features[Indicator.BOLLINGER_UPPER.getValue()] = bollingerUpper();
		features[Indicator.BOLLINGER_LOWER.getValue()] = bollingerLower();
		features[Indicator.BOLLINGER_MIDDLE.getValue()] = bollingerMiddle();
		features[Indicator.MOVING_AVERAGE_25.getValue()] = movingAverage25();
		features[Indicator.MOVING_AVERAGE_65.getValue()] = movingAverage65();
		return features;
	}*/
	
	public TechnicalIndicatorSet computeAll(){
		positiveVolumeIndex();
		//negativeVolumeIndex();
		onBalanceVolume();
		typicalVolume();
		priceVolumeTrend();
		accumulationDistributionOscillator();
		//chaikinsOscillator();
		//chaikinsVolatility();
		acceleration();
		highestHigh();
		lowestLow();
		//relativeStrengthIndex();
		//macdLine();
		//macdNinePeriodMovingAverage();
		momentum();
		stochasticOscillatorK();
		stochasticOscillatorD();
		typicalPrice();
		medianPrice();
		weightedClose();
		williamsR();
		priceRateOfChange();
		williamsAccumulationDistribution();
		bollingerUpper();
		bollingerLower();
		bollingerMiddle();
		movingAverage25();
		movingAverage65();
		return indexData.getTechnicalIndicatorSet();
	}
	
	public StockRecord getUpdatedRecord(){
		return indexData.getTodaysRecord();
	}
	
	public void storeComputations(){
		indexData.updateRecord();
	}
	public Double simpleMovingAverage(int days){
		Double[] values = indexData.getCloseInPeriod(days);
		return simpleMovingAverage(values);
	}
	public Double simpleMovingAverage(Double[] values){
		Double sma = average(values);
		return sma;
	}
	public Double exponentialMovingAverage(int days){
		Double ema;
		Double multiplier = 2 / ((double)days+1);
		if(indexData.getEMAYesterday() != null){
			ema = (indexData.getCloseToday() - indexData.getEMAYesterday()) * multiplier + indexData.getEMAYesterday();
		}
		else{
			ema = simpleMovingAverage(days);
		}
		indexData.setHelperIndicator(HelperIndicator.EXPONENTIAL_MOVING_AVERAGE, ema);
		return ema;
	}
	
	public Double accumulationDistributionLine(){
		Double adl;
		if(indexData.getADLYesterday() != null)
			adl = indexData.getADLYesterday() + indexData.getADOscillatorToday();
		else
			adl = indexData.getADOscillatorToday();
		indexData.setHelperIndicator(HelperIndicator.ACCUMULATION_DISTRIBUTION_LINE, adl);
		return 0.0;
	}
	
	public Double trueRangeHigh(){
		if(indexData.getCloseYesterday() == null)
			return indexData.getHighToday();
		else{
			Double[] values = {indexData.getCloseYesterday(), indexData.getHighToday()};
			return max(values);
		}
	}
	public Double trueRangeLow(){
		if(indexData.getCloseYesterday() == null){
			return indexData.getLowToday();
		}
		else{
			Double[] values = {indexData.getCloseYesterday(), indexData.getLowToday()};
			return min(values);
		}
	}
	public Double todaysAccumulationDistribution(){
		Double ad;
		if(indexData.getCloseYesterday() == null){
			ad = 0.0;
		}
		else{
			if(indexData.getCloseToday() > indexData.getCloseYesterday()){
				ad = indexData.getCloseToday() - trueRangeLow();
			}
			else if(indexData.getCloseToday() < indexData.getCloseYesterday()){
				ad = indexData.getCloseToday() - trueRangeHigh();
			}
			else{
				ad = 0.0;
			}
		}
		return ad;
	}
	
	public Double relativeStrength(){
		//TODO
		double upwardChange = 1.0;
		double downwardChange = 1.0;
		double rs = upwardChange / downwardChange;
		return rs;
	}
	public Double positiveVolumeIndex(){
		Double pvi;
		if(indexData.getPVIYesterday() == null){
			pvi = indexData.getCloseToday();
		}
		else{
			if(indexData.getVolumeToday() > indexData.getVolumeYesterday()){
				pvi = indexData.getPVIYesterday() 
						+ (indexData.getCloseToday()-indexData.getCloseYesterday())
						/ indexData.getCloseYesterday() 
						* indexData.getPVIYesterday();
			}
			else{
				pvi = indexData.getPVIYesterday();
			}
		}
		indexData.setTechnicalIndicator(Indicator.POSITIVE_VOLUME_INDEX, pvi);
		return pvi;
	}
	
	public Double negativeVolumeIndex(){
		//TODO
		return 0.0;
	}
	public Double onBalanceVolume(){
		Double obv;
		if(indexData.getOBVYesterday() == null){
			obv = indexData.getVolumeToday();
		}
		else{
			obv = indexData.getOBVYesterday();
			if(indexData.getCloseToday() > indexData.getCloseYesterday()){
				obv += indexData.getVolumeToday();
			}
			else{
				obv -= indexData.getVolumeToday();
			}
		}
		
		indexData.setTechnicalIndicator(Indicator.ONBALANCE_VOLUME, obv);
		return obv;
	}
	
	public Double typicalVolume(){
		Double[] volumeSet = indexData.getVolumeInPeriod(10);
		Double typicalVolume = average(volumeSet);
		indexData.setTechnicalIndicator(Indicator.TYPICAL_VOLUME, typicalVolume);
		return typicalVolume;
	}
	
	public Double priceVolumeTrend(){
		Double pvt;
		if(indexData.getPVTYesterday() == null){
			pvt = indexData.getVolumeToday();
		}
		else{
			pvt = indexData.getPVTYesterday()
					+ indexData.getVolumeToday()
					* (indexData.getCloseToday() - indexData.getCloseYesterday())
					/ indexData.getCloseYesterday();
		}
		
		indexData.setTechnicalIndicator(Indicator.PRICE_VOLUME_TREND, pvt);
		return pvt;
	}
	
	public Double accumulationDistributionOscillator(){
		Double ad;
		ad = ((indexData.getCloseToday() - indexData.getLowToday())
				- (indexData.getHighToday() - indexData.getCloseToday()))
				/ (indexData.getHighToday() - indexData.getLowToday())
				* indexData.getVolumeToday();
		indexData.setTechnicalIndicator(Indicator.ACCUMULATION_DISTRIBUTION_OSCILLATOR, ad);
		return ad;
	}
	public Double chaikinsOscillator(){
		//TODO
		return 0.0;
	}
	public Double chaikinsVolatility(){
		//TODO
		return 0.0;
	}
	public Double acceleration(){
		Double oscillator, acceleration;
		oscillator = simpleMovingAverage(indexData.getMedianPriceInPeriod(5))
				- simpleMovingAverage(indexData.getMedianPriceInPeriod(34));
		indexData.setHelperIndicator(HelperIndicator.UNNAMED_OSCILLATOR, oscillator);
		
		acceleration = oscillator - simpleMovingAverage(indexData.getOscillatorInPeriod(5));
		indexData.setTechnicalIndicator(Indicator.ACCELERATION, acceleration);
		return acceleration;
	}
	public Double highestHigh(){
		Double[] highSet = indexData.getHighInPeriod(10);
		Double maxHigh = max(highSet);
		indexData.setTechnicalIndicator(Indicator.HIGHEST_HIGH, maxHigh);
		return maxHigh;
	}
	public Double lowestLow(){
		Double[] lowSet = indexData.getLowInPeriod(10);
		Double minLow = min(lowSet);
		indexData.setTechnicalIndicator(Indicator.LOWEST_LOW, minLow);
		return minLow;
	}
	public Double relativeStrengthIndex(){
		//TODO
		Double rsi;
		rsi = 100 - 100 / (1 + relativeStrength());
		indexData.setTechnicalIndicator(Indicator.RELATIVE_STRENGTH_INDEX, rsi);
		return rsi;
	}
	public Double macdNinePeriodMovingAverage(){
		Double nine;
		Double[] values = indexData.getMACDLineInPeriod(9);
		Double multiplier = 2 / ((double)values.length+1);
		Double signalLine = indexData.getMACDNinePeriodMovingAverageYesterday();
		if(signalLine != null){
			nine = (indexData.getCloseToday() - signalLine) * multiplier + signalLine;
		}
		else{
			nine = simpleMovingAverage(values);
		}
		indexData.setTechnicalIndicator(Indicator.MACD_NINE_PERIOD_MOVING_AVERAGE, nine);
		return nine;
	}
	public Double macdLine(){
		//TODO
		Double line;
		line = exponentialMovingAverage(12) - exponentialMovingAverage(26);
		indexData.setTechnicalIndicator(Indicator.MACD_LINE, line);
		return line;
	}
	public Double momentum(){
		int day = 10;
		Double mo;
		mo = indexData.getCloseToday() - indexData.getCloseFromDay(day);
		indexData.setTechnicalIndicator(Indicator.MOMENTUM, mo);
		return mo;
	}
	public Double stochasticOscillatorK(){
		Double osc;
		Double low = min(indexData.getLowInPeriod(10));
		Double high = max(indexData.getHighInPeriod(10));
		osc = ((indexData.getCloseToday() - low)
				/ (high - low)) * 100;
		indexData.setTechnicalIndicator(Indicator.STOCHASTIC_OSCILLATOR_K, osc);
		return osc;
	}
	public Double stochasticOscillatorD(){
		Double d = simpleMovingAverage(indexData.getKInPeriod(3));
		indexData.setTechnicalIndicator(Indicator.STOCKASTIC_OSCILLATOR_D, d);
		return d;
	}
	public Double typicalPrice(){
		Double[] values = {indexData.getHighToday(), indexData.getLowToday(), indexData.getCloseToday()};
		Double price = average(values);
		indexData.setTechnicalIndicator(Indicator.TYPICAL_PRICE, price);
		return price;
	}
	public Double medianPrice(){
		Double[] values = {indexData.getHighToday(), indexData.getLowToday()};
		Double price = average(values);
		indexData.setTechnicalIndicator(Indicator.MEDIAN_PRICE, price);
		return price;
	}
	public Double weightedClose(){
		Double[] values = {indexData.getHighToday(), indexData.getLowToday(), indexData.getCloseToday(), indexData.getCloseToday()};
		Double price = average(values);
		indexData.setTechnicalIndicator(Indicator.WEIGHTED_CLOSE, price);
		return price;
	}
	public Double williamsR(){
		Double r;
		Double high = highestHigh();
		Double low = lowestLow();
		r = (high - indexData.getCloseToday())/(high - low) * 100;
		indexData.setTechnicalIndicator(Indicator.WILLIAMS_R, r);
		return r;
	}
	public Double priceRateOfChange(){
		Double rate;
		if(indexData.getCloseYesterday() == null){
			rate = 0.0;
		}
		else{
			rate = (indexData.getCloseToday()-indexData.getCloseYesterday())/indexData.getCloseYesterday();
		}
		indexData.setTechnicalIndicator(Indicator.PRICE_RATE_OF_CHANGE, rate);
		return rate;
	}
	public Double williamsAccumulationDistribution(){
		Double ad;
		if(indexData.getWilliamsADYesterday() != null){
			ad = todaysAccumulationDistribution() + indexData.getWilliamsADYesterday();
		}
		else{
			ad = todaysAccumulationDistribution();
		}
		indexData.setTechnicalIndicator(Indicator.WILLIAMS_ACCUMULATION_DISTRIBUTION, ad);
		return ad;
	}
	public Double bollingerUpper(){
		Double upper = simpleMovingAverage(20) + standardDeviation(indexData.getCloseInPeriod(20)) * 2;
		indexData.setTechnicalIndicator(Indicator.BOLLINGER_UPPER, upper);
		return upper;
	}
	public Double bollingerMiddle(){
		Double middle = simpleMovingAverage(20);
		indexData.setTechnicalIndicator(Indicator.BOLLINGER_MIDDLE, middle);
		return middle;
	}
	public Double bollingerLower(){
		Double lower = simpleMovingAverage(20) - standardDeviation(indexData.getCloseInPeriod(20)) * 2;
		indexData.setTechnicalIndicator(Indicator.BOLLINGER_LOWER, lower);
		return lower;
	}
	public Double movingAverage25(){
		Double avg = simpleMovingAverage(25);
		indexData.setTechnicalIndicator(Indicator.MOVING_AVERAGE_25, avg);
		return avg;
	}
	public Double movingAverage65(){
		Double avg = simpleMovingAverage(65);
		indexData.setTechnicalIndicator(Indicator.MOVING_AVERAGE_65, avg);
		return avg;
	}
	private Double average(Double[] numbers){
		Double total = 0.0;
		Double average;
		for(Double n: numbers){
			total += n;
		}
		average = total / numbers.length;
		return average;
	}
	private Double max(Double[] numbers){
		Double max = numbers[0];
		for(Double n: numbers){
			if(n > max)
				max = n;
		}
		return max;
	}
	private Double min(Double[] numbers){
		Double min = numbers[0];
		for(Double n: numbers){
			if(n < min){
				min = n;
			}
		}
		return min;
	}
	
	private Double standardDeviation(Double[] numbers){
		double mean = average(numbers);
		double sum = 0;
		for(Double n : numbers){
			sum += Math.pow(n-mean, 2);
		}
		double stdev = Math.sqrt((sum/numbers.length));
		return stdev;
	}
}
