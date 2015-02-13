package technicalindicators;

import data.entities.enums.TradingIndex;

public class Computation {
	private TradingIndex index;
	private StockIndexFactory indexData;
	public Computation(TradingIndex i){
		index = i;
		indexData = new StockIndexFactory(index);
	}
	public double[] computeAll(){
		double[] features = new double[28];
		return features;
	}
	public double simpleMovingAverage(int days){
		double[] values = indexData.getCloseInPeriod(days);
		return simpleMovingAverage(values);
	}
	public double simpleMovingAverage(double[] values){
		double sma = average(values);
		return sma;
	}
	public double exponentialMovingAverage(int days, double multiplier){
		double ema;
		ema = (indexData.getCloseToday() - indexData.getEMAYesterday()) * multiplier + indexData.getEMAYesterday();
		indexData.setEMAToday(ema);
		return ema;
	}
	
	public double accumulationDistributionLine(){
		double adl;
		adl = indexData.getADLYesterday() + indexData.getADOscillatorToday();
		indexData.setADLToday(adl);
		return 0.0;
	}
	
	public double trueRangeHigh(){
		double[] values = {indexData.getCloseYesterday(), indexData.getHighToday()};
		return max(values);
	}
	public double trueRangeLow(){
		double[] values = {indexData.getCloseYesterday(), indexData.getLowToday()};
		return min(values);
	}
	public double todaysAccumulationDistribution(){
		double ad;
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
	
	public double relativeStrength(){
		return 0.0;
	}
	public double positiveVolumeIndex(){
		double pvi;
		if(indexData.getVolumeToday() > indexData.getVolumeYesterday()){
			pvi = indexData.getPVIYesterday() 
					+ (indexData.getCloseToday()-indexData.getCloseYesterday())
					/ indexData.getCloseYesterday() 
					* indexData.getPVIYesterday();
		}
		else{
			pvi = indexData.getPVIYesterday();
		}
		indexData.setPVIToday(pvi);
		return pvi;
	}
	
	public double negativeVolumeIndex(){
		return 0.0;
	}
	public double onBalanceVolume(){
		double obv = indexData.getOBVYesterday();
		if(indexData.getCloseToday() > indexData.getCloseYesterday()){
			obv += indexData.getVolumeToday();
		}
		else{
			obv -= indexData.getVolumeToday();
		}
		indexData.setOBVToday(obv);
		return obv;
	}
	
	public double typicalVolume(){
		double[] volumeSet = indexData.getVolumeInPeriod(10);
		double typicalVolume = average(volumeSet);
		return typicalVolume;
	}
	
	public double priceVolumeTrend(){
		double pvt;
		pvt = indexData.getPVTYesterday()
				+ indexData.getVolumeToday()
				* (indexData.getCloseToday() - indexData.getCloseYesterday())
				/ indexData.getCloseYesterday();
		indexData.setPVTToday(pvt);
		
		return pvt;
	}
	
	public double accumulationDistributionOscillator(){
		double ad;
		ad = ((indexData.getCloseToday() - indexData.getLowToday())
				- (indexData.getHighToday() - indexData.getCloseToday()))
				/ (indexData.getHighToday() - indexData.getLowToday())
				* indexData.getVolumeToday();
		indexData.setADOscillator(ad);
		return ad;
	}
	public double chaikinsOscillator(){
		return 0.0;
	}
	public double chaikinsVolatility(){
		return 0.0;
	}
	public double acceleration(){
		double oscillator, acceleration;
		oscillator = simpleMovingAverage(indexData.getMedianPriceInPeriod(5))
				- simpleMovingAverage(indexData.getMedianPriceInPeriod(34));
		indexData.setOscillatorToday(oscillator);
		acceleration = oscillator - simpleMovingAverage(indexData.getOscillatorInPeriod(5));
		return acceleration;
	}
	public double highestHigh(){
		double[] highSet = indexData.getHighInPeriod(10);
		double maxHigh = max(highSet);
		return maxHigh;
	}
	public double lowestLow(){
		double[] lowSet = indexData.getLowInPeriod(10);
		double minLow = min(lowSet);
		return minLow;
	}
	public double relativeStrengthIndex(){
		double rsi;
		rsi = 100 - 100 / (1 + relativeStrength());
		return rsi;
	}
	public double macdNinePeriodMovingAverage(){
		double nine;
		nine = exponentialMovingAverage(9,indexData.getMACDLine());
		return nine;
	}
	public double macdLine(){
		double line;
		line = exponentialMovingAverage(12,0.0) - exponentialMovingAverage(26, 0.0);
		indexData.setMACDLine(line);
		return line;
	}
	public double momentum(){
		int day = 10;
		double mo;
		mo = indexData.getCloseToday() - indexData.getCloseFromDay(day);
		return mo;
	}
	public double stochasticOscillatorK(){
		double osc;
		double low = min(indexData.getLowInPeriod(10));
		double high = max(indexData.getHighInPeriod(10));
		osc = ((indexData.getCloseToday() - low)
				/ (high - low)) * 100;
		indexData.setKToday(osc);
		return osc;
	}
	public double stochasticOscillatorD(){
		return simpleMovingAverage(indexData.getKInPeriod(3));
	}
	public double typicalPrice(){
		double[] values = {indexData.getHighToday(), indexData.getLowToday(), indexData.getCloseToday()};
		double price = average(values);
		return price;
	}
	public double medianPrice(){
		double[] values = {indexData.getHighToday(), indexData.getLowToday()};
		double price = average(values);
		indexData.setMedianPriceToday(price);
		return price;
	}
	public double weightedClose(){
		double[] values = {indexData.getHighToday(), indexData.getLowToday(), indexData.getCloseToday(), indexData.getCloseToday()};
		double price = average(values);
		return price;
	}
	public double williamsR(){
		double r;
		double high = highestHigh();
		double low = lowestLow();
		r = (high - indexData.getCloseToday())/(high - low) * 100;
		return r;
	}
	public double priceRateOfChange(){
		double rate = (indexData.getCloseToday()-indexData.getCloseYesterday())/indexData.getCloseYesterday();
		return rate;
	}
	public double williamsAccumulationDistribution(){
		double ad = todaysAccumulationDistribution() + indexData.getWilliamsADYesterday();
		indexData.setWilliamsADToday(ad);
		return ad;
	}
	public double bollingerUpper(){
		double upper = simpleMovingAverage(20) + standardDeviation(indexData.getCloseInPeriod(20)) * 2;
		return upper;
	}
	public double bollingerMiddle(){
		double middle = simpleMovingAverage(20);
		return middle;
	}
	public double bollingerLower(){
		double lower = simpleMovingAverage(20) - standardDeviation(indexData.getCloseInPeriod(20)) * 2;
		return lower;
	}
	public double movingAverage25(){
		return simpleMovingAverage(25);
	}
	public double movingAverage65(){
		return simpleMovingAverage(65);
	}
	private double average(double[] numbers){
		double total = 0.0;
		double average;
		for(double n: numbers){
			total += n;
		}
		average = total / numbers.length;
		return average;
	}
	private double max(double[] numbers){
		double max = numbers[0];
		for(double n: numbers){
			if(n > max)
				max = n;
		}
		return max;
	}
	private double min(double[] numbers){
		double min = numbers[0];
		for(double n: numbers){
			if(n < min){
				min = n;
			}
		}
		return min;
	}
	
	private double standardDeviation(double[] numbers){
		return 0.0;
	}
}
