package similaritymetric.numbersimilarity.impl;

import similaritymetric.numbersimilarity.NumberSimilarityMetric;

public class ThresholdBasedNumberSimilarityMetric implements NumberSimilarityMetric {

    private double threshold;

    public ThresholdBasedNumberSimilarityMetric(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public double compute(double firstNumber, double secondNumber) {

        if (firstNumber == secondNumber) {
            return 1;
        }

        double largerNumber = Math.max(firstNumber, secondNumber);
        double smallNumber = Math.min(firstNumber, secondNumber);
        double similarityIndex = smallNumber/largerNumber;

        return similarityIndex >= threshold? similarityIndex: 0;
    }
}