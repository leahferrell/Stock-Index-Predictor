package technicalindicators;

import data.entities.enums.Indicator;
import data.entities.enums.TradingIndex;

public class Features {
	static int TOTAL_FEATURES = 28;
	static Double[] features = new Double[TOTAL_FEATURES];
	static boolean compute = true;
	public double getFeature(Indicator i){
		return features[i.getValue()];
	}
	public double[] getSelectedFeatures(Indicator[] iList){
		double[] featureSubset = new double[iList.length];
		for(int i = 0; i < iList.length; i++){
			featureSubset[i] = features[iList[i].getValue()];
		}
		return featureSubset;
	}
	public Double[] getAllFeatures(){
		return features;
	}
	public void recompute(){
		Computation c = new Computation(TradingIndex.DOW_JONES_INDUSTRIAL_AVERAGE);
		features = c.computeAll();
	}
}
