package technicalindicators;

import data.entities.enums.Indicator;
import data.entities.enums.TradingIndex;

public class Computation {
	private TradingIndex index;
	private StockIndexWrapper indexData;
	public Computation(TradingIndex i){
		index = i;
		indexData = new StockIndexWrapper(index);
	}
	public Double[] computeAll(){
		//TODO
		Double[] features = new Double[28];
		return features;
	}
	public void storeComputations(){
		indexData.updateRecord();
	}
	public Double simpleMovingAverage(int days){
		//TODO
		Double[] values = indexData.getCloseInPeriod(days);
		return simpleMovingAverage(values);
	}
	public Double simpleMovingAverage(Double[] values){
		//TODO
		Double sma = average(values);
		return sma;
	}
	public Double exponentialMovingAverage(int days, Double multiplier){
		//TODO
		Double ema;
		ema = (indexData.getCloseToday() - indexData.getEMAYesterday()) * multiplier + indexData.getEMAYesterday();
		indexData.setEMAToday(ema);
		return ema;
	}
	
	public Double accumulationDistributionLine(){
		//TODO
		Double adl;
		adl = indexData.getADLYesterday() + indexData.getADOscillatorToday();
		indexData.setADLToday(adl);
		return 0.0;
	}
	
	public Double trueRangeHigh(){
		//TODO
		Double[] values = {indexData.getCloseYesterday(), indexData.getHighToday()};
		return max(values);
	}
	public Double trueRangeLow(){
		//TODO
		Double[] values = {indexData.getCloseYesterday(), indexData.getLowToday()};
		return min(values);
	}
	public Double todaysAccumulationDistribution(){
		//TODO
		Double ad;
		if(indexData.getCloseToday() > indexData.getCloseYesterday()){
			ad = indexData.getCloseToday() - trueRangeLow();
		}
		else if(indexData.getCloseToday() < indexData.getCloseYesterday()){
			ad = indexData.getCloseToday() - trueRangeHigh();
		}
		else{
			ad = 0.0;
		}
		return ad;
	}
	
	public Double relativeStrength(){
		//TODO
		return 0.0;
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
		//TODO
		Double oscillator, acceleration;
		oscillator = simpleMovingAverage(indexData.getMedianPriceInPeriod(5))
				- simpleMovingAverage(indexData.getMedianPriceInPeriod(34));
		indexData.setOscillatorToday(oscillator);
		acceleration = oscillator - simpleMovingAverage(indexData.getOscillatorInPeriod(5));
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
		return rsi;
	}
	public Double macdNinePeriodMovingAverage(){
		//TODO
		Double nine;
		nine = exponentialMovingAverage(9,indexData.getMACDLine());
		return nine;
	}
	public Double macdLine(){
		//TODO
		Double line;
		line = exponentialMovingAverage(12,0.0) - exponentialMovingAverage(26, 0.0);
		indexData.setMACDLine(line);
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
		//TODO
		return simpleMovingAverage(indexData.getKInPeriod(3));
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
		indexData.setMedianPriceToday(price);
		return price;
	}
	public Double weightedClose(){
		Double[] values = {indexData.getHighToday(), indexData.getLowToday(), indexData.getCloseToday(), indexData.getCloseToday()};
		Double price = average(values);
		return price;
	}
	public Double williamsR(){
		Double r;
		Double high = highestHigh();
		Double low = lowestLow();
		r = (high - indexData.getCloseToday())/(high - low) * 100;
		return r;
	}
	public Double priceRateOfChange(){
		Double rate = (indexData.getCloseToday()-indexData.getCloseYesterday())/indexData.getCloseYesterday();
		return rate;
	}
	public Double williamsAccumulationDistribution(){
		Double ad = todaysAccumulationDistribution() + indexData.getWilliamsADYesterday();
		indexData.setWilliamsADToday(ad);
		return ad;
	}
	public Double bollingerUpper(){
		Double upper = simpleMovingAverage(20) + standardDeviation(indexData.getCloseInPeriod(20)) * 2;
		return upper;
	}
	public Double bollingerMiddle(){
		Double middle = simpleMovingAverage(20);
		return middle;
	}
	public Double bollingerLower(){
		Double lower = simpleMovingAverage(20) - standardDeviation(indexData.getCloseInPeriod(20)) * 2;
		return lower;
	}
	public Double movingAverage25(){
		return simpleMovingAverage(25);
	}
	public Double movingAverage65(){
		return simpleMovingAverage(65);
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
		//TODO
		return 0.0;
	}
}
