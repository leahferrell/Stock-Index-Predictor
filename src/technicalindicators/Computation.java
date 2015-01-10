package technicalindicators;

public class Computation {
	private TradingIndex index;
	private TradingDataSet dataSet;
	public Computation(TradingIndex i){
		index = i;
		dataSet = new TradingDataSet(index);
	}
	public double[] computeAll(){
		double[] features = new double[28];
		return features;
	}
	public double simpleMovingAverage(int days){
		double[] values = dataSet.getCloseInPeriod(days);
		return simpleMovingAverage(values);
	}
	public double simpleMovingAverage(double[] values){
		double sma = average(values);
		return sma;
	}
	public double exponentialMovingAverage(int days, double multiplier){
		double ema;
		ema = (dataSet.getCloseToday() - dataSet.getEMAYesterday()) * multiplier + dataSet.getEMAYesterday();
		dataSet.setEMAToday(ema);
		return ema;
	}
	
	public double accumulationDistributionLine(){
		double adl;
		adl = dataSet.getADLYesterday() + dataSet.getADOscillatorToday();
		dataSet.setADLToday(adl);
		return 0.0;
	}
	
	public double trueRangeHigh(){
		double[] values = {dataSet.getCloseYesterday(), dataSet.getHighToday()};
		return max(values);
	}
	public double trueRangeLow(){
		double[] values = {dataSet.getCloseYesterday(), dataSet.getLowToday()};
		return min(values);
	}
	public double todaysAccumulationDistribution(){
		double ad;
		if(dataSet.getCloseToday() > dataSet.getCloseYesterday()){
			ad = dataSet.getCloseToday() - trueRangeLow();
		}
		else if(dataSet.getCloseToday() < dataSet.getCloseYesterday()){
			ad = dataSet.getCloseToday() - trueRangeHigh();
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
		if(dataSet.getVolumeToday() > dataSet.getVolumeYesterday()){
			pvi = dataSet.getPVIYesterday() 
					+ (dataSet.getCloseToday()-dataSet.getCloseYesterday())
					/ dataSet.getCloseYesterday() 
					* dataSet.getPVIYesterday();
		}
		else{
			pvi = dataSet.getPVIYesterday();
		}
		dataSet.setPVIToday(pvi);
		return pvi;
	}
	
	public double negativeVolumeIndex(){
		return 0.0;
	}
	public double onBalanceVolume(){
		double obv = dataSet.getOBVYesterday();
		if(dataSet.getCloseToday() > dataSet.getCloseYesterday()){
			obv += dataSet.getVolumeToday();
		}
		else{
			obv -= dataSet.getVolumeToday();
		}
		dataSet.setOBVToday(obv);
		return obv;
	}
	
	public double typicalVolume(){
		double[] volumeSet = dataSet.getVolumeInPeriod(10);
		double typicalVolume = average(volumeSet);
		return typicalVolume;
	}
	
	public double priceVolumeTrend(){
		double pvt;
		pvt = dataSet.getPVTYesterday()
				+ dataSet.getVolumeToday()
				* (dataSet.getCloseToday() - dataSet.getCloseYesterday())
				/ dataSet.getCloseYesterday();
		dataSet.setPVTToday(pvt);
		
		return pvt;
	}
	
	public double accumulationDistributionOscillator(){
		double ad;
		ad = ((dataSet.getCloseToday() - dataSet.getLowToday())
				- (dataSet.getHighToday() - dataSet.getCloseToday()))
				/ (dataSet.getHighToday() - dataSet.getLowToday())
				* dataSet.getVolumeToday();
		dataSet.setADOscillator(ad);
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
		oscillator = simpleMovingAverage(dataSet.getMedianPriceInPeriod(5))
				- simpleMovingAverage(dataSet.getMedianPriceInPeriod(34));
		dataSet.setOscillatorToday(oscillator);
		acceleration = oscillator - simpleMovingAverage(dataSet.getOscillatorInPeriod(5));
		return acceleration;
	}
	public double highestHigh(){
		double[] highSet = dataSet.getHighInPeriod(10);
		double maxHigh = max(highSet);
		return maxHigh;
	}
	public double lowestLow(){
		double[] lowSet = dataSet.getLowInPeriod(10);
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
		nine = exponentialMovingAverage(9,dataSet.getMACDLine());
		return nine;
	}
	public double macdLine(){
		double line;
		line = exponentialMovingAverage(12,0.0) - exponentialMovingAverage(26, 0.0);
		dataSet.setMACDLine(line);
		return line;
	}
	public double momentum(){
		int day = 10;
		double mo;
		mo = dataSet.getCloseToday() - dataSet.getCloseFromDay(day);
		return mo;
	}
	public double stochasticOscillatorK(){
		double osc;
		double low = min(dataSet.getLowInPeriod(10));
		double high = max(dataSet.getHighInPeriod(10));
		osc = ((dataSet.getCloseToday() - low)
				/ (high - low)) * 100;
		dataSet.setKToday(osc);
		return osc;
	}
	public double stochasticOscillatorD(){
		return simpleMovingAverage(dataSet.getKInPeriod(3));
	}
	public double typicalPrice(){
		double[] values = {dataSet.getHighToday(), dataSet.getLowToday(), dataSet.getCloseToday()};
		double price = average(values);
		return price;
	}
	public double medianPrice(){
		double[] values = {dataSet.getHighToday(), dataSet.getLowToday()};
		double price = average(values);
		dataSet.setMedianPriceToday(price);
		return price;
	}
	public double weightedClose(){
		double[] values = {dataSet.getHighToday(), dataSet.getLowToday(), dataSet.getCloseToday(), dataSet.getCloseToday()};
		double price = average(values);
		return price;
	}
	public double williamsR(){
		double r;
		double high = highestHigh();
		double low = lowestLow();
		r = (high - dataSet.getCloseToday())/(high - low) * 100;
		return r;
	}
	public double priceRateOfChange(){
		double rate = (dataSet.getCloseToday()-dataSet.getCloseYesterday())/dataSet.getCloseYesterday();
		return rate;
	}
	public double williamsAccumulationDistribution(){
		double ad = todaysAccumulationDistribution() + dataSet.getWilliamsADYesterday();
		dataSet.setWilliamsADToday(ad);
		return ad;
	}
	public double bollingerUpper(){
		double upper = simpleMovingAverage(20) + standardDeviation(dataSet.getCloseInPeriod(20)) * 2;
		return upper;
	}
	public double bollingerMiddle(){
		double middle = simpleMovingAverage(20);
		return middle;
	}
	public double bollingerLower(){
		double lower = simpleMovingAverage(20) - standardDeviation(dataSet.getCloseInPeriod(20)) * 2;
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
