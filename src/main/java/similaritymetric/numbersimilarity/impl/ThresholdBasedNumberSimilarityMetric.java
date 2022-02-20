package similaritymetric.numbersimilarity.impl;

import similaritymetric.numbersimilarity.NumberSimilarityMetric;

public class ThresholdBasedNumberSimilarityMetric implements NumberSimilarityMetric {

    private double threshold;

    public ThresholdBasedNumberSimilarityMetric(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public double compute(double firstNumber, double secondNumber) {

        double firstNumberSecondNumberDiff = Math.abs(firstNumber - secondNumber);

        return firstNumberSecondNumberDiff <= threshold? firstNumberSecondNumberDiff: Double.MAX_VALUE;
    }
}