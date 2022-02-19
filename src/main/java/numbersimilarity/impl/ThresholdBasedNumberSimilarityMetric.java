package numbersimilarity.impl;

import numbersimilarity.NumberSimilarityMetric;

public class ThresholdBasedNumberSimilarityMetric implements NumberSimilarityMetric {

    @Override
    public double compute(double firstNumber, double secondNumber, double threshold) {

        double firstNumberSecondNumberDiff = Math.abs(firstNumber - secondNumber);

        return firstNumberSecondNumberDiff <= threshold? firstNumberSecondNumberDiff: Double.MAX_VALUE;
    }
}