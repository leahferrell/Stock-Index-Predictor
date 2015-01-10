package technicalindicators;
import java.io.File;

public class TradingDataSet {
	private TradingIndex index;
	public TradingDataSet(TradingIndex i){
		index = i;
		setUpDatabase();
	}
	private void setUpDatabase(){
		File database = new File(index.getDatabaseName());
		database.exists();
	}
	public double getVolumeToday(){
		return 0.0;
	}
	public double getVolumeYesterday(){
		return 0.0;
	}
	public double getPVIYesterday(){
		return 0.0;
	}
	public double getCloseToday(){
		return 0.0;
	}
	public double getCloseYesterday(){
		return 0.0;
	}
	public double getCloseFromDay(int day){
		return 0.0;
	}
	public double[] getCloseInPeriod(int numOfDays){
		double[] closeSet = new double[numOfDays];
		return closeSet;
	}
	public double getOBVYesterday(){
		return 0.0;
	}
	public double[] getVolumeInPeriod(int period){
		double[] volumeSet = new double[period];
		return volumeSet;
	}
	public double getPVTYesterday(){
		return 0.0;
	}
	public double getLowToday(){
		return 0.0;
	}
	public double getHighToday(){
		return 0.0;
	}
	public double getHighFromDay(int day){
		return 0.0;
	}
	public double getLowFromDay(int day){
		return 0.0;
	}
	public double[] getHighInPeriod(int period){
		double[] highSet = new double[period];
		return highSet;
	}
	public double[] getLowInPeriod(int period){
		double[] lowSet = new double[period];
		return lowSet;
	}
	public double getMedianPriceToday(){
		return 0.0;
	}
	public double[] getMedianPriceInPeriod(int days){
		double[] prices = new double[days];
		return prices;
	}
	public double getWilliamsADYesterday(){
		return 0.0;
	}
	public double getEMAYesterday(){
		return 0.0;
	}
	public double getMACDLine(){
		return 0.0;
	}
	public double[] getOscillatorInPeriod(int days){
		double[] osc = new double[days];
		return osc;
	}
	public double[] getKInPeriod(int period){
		double[] kSet = new double[period];
		return kSet;
	}
	public double getADOscillatorToday(){
		return 0.0;
	}
	public double getADLYesterday(){
		return 0.0;
	}
	public void setMACDLine(double macd){
		
	}
	public void setEMAToday(double ema){
		
	}
	public void setWilliamsADToday(double ad){
		
	}
	public void setMedianPriceToday(double med){
		
	}
	public void setPVTToday(double pvt){
		
	}
	public void setOBVToday(double obv){
		
	}
	public void setPVIToday(double pvi){
		
	}
	public void setOscillatorToday(double osc){
		
	}
	public void setKToday(double k){
		
	}
	public void setADOscillator(double ad){
		
	}
	public void setADLToday(double ald){
		
	}
}
