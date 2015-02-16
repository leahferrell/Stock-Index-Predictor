package technicalindicators;

import data.entities.StockRecord;
import data.entities.TechnicalIndicatorSet;
import data.entities.enums.Indicator;
import data.entities.enums.TradingIndex;

/**
 * I may be getting rid of this class soon. It's proving to be rather worthless.
 * */

public class Features {
	static TechnicalIndicatorSet features;
	static boolean compute = true;
	
	public double getFeature(Indicator i){
		return features.getIndicator(i);
	}
	public double[] getSelectedFeatures(Indicator[] iList){
		double[] featureSubset = new double[iList.length];
		for(int i = 0; i < iList.length; i++){
			featureSubset[i] = features.getIndicator(iList[i]);
		}
		return featureSubset;
	}
	public TechnicalIndicatorSet getAllFeatures(){
		return features;
	}
	public StockRecord getCurrentRecord(TradingIndex t){
		StockIndexWrapper index = new StockIndexWrapper(t);
		return index.getTodaysRecord();
	}
	public void recompute(){
		Computation c = new Computation(TradingIndex.DOW_JONES_INDUSTRIAL_AVERAGE);
		features = c.computeAll();
	}
}
