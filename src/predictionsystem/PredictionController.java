package predictionsystem;

import data.entities.enums.Prediction;
import data.entities.enums.TradingIndex;

public class PredictionController {
	TradingIndex index;
	public PredictionController(TradingIndex index){
		this.index = index;
	}
	
	public Prediction getPrediction(){
		return Prediction.NO_TREND;
	}
}
