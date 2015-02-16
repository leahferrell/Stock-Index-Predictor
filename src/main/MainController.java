package main;

import predictionsystem.PredictionController;
import data.entities.StockRecord;
import data.entities.enums.Prediction;
import data.entities.enums.TradingIndex;
import technicalindicators.Features;

public class MainController {
	static Features features = new Features();
	static StockRecord newRecord;
	static PredictionController predictor;
	public MainController(){
		
	}
	
	public static void run(){
		runForIndex(TradingIndex.DOW_JONES_INDUSTRIAL_AVERAGE);
	}
	
	public static void getDailyInputs(TradingIndex index){
		//TODO
	}
	
	public static void computeIndicators(TradingIndex index){
		features.recompute();
		newRecord = features.getCurrentRecord(index);
	}
	
	public static Prediction runPredictor(TradingIndex index){
		predictor = new PredictionController(index);
		return predictor.getPrediction();
	}
	
	public static void updateDatabase(Prediction p, TradingIndex i){
		
	}
	
	public static void runForIndex(TradingIndex index){
		getDailyInputs(index);
		computeIndicators(index);
		Prediction prediction = runPredictor(index);
		updateDatabase(prediction, index);
	}
	
	public static void main(String[] args){
		run();
	}
}
