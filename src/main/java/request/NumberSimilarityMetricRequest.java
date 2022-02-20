package request;

import similaritymetric.NumberSimilarityMetricStrategy;

public class NumberSimilarityMetricRequest {

    private NumberSimilarityMetricStrategy strategy;
    private double threshold;

    public NumberSimilarityMetricRequest(NumberSimilarityMetricStrategy strategy, double threshold) {
        this.strategy = strategy;
        this.threshold = threshold;
    }

    public NumberSimilarityMetricStrategy getStrategy() {
        return strategy;
    }

    public double getThreshold() {
        return threshold;
    }
}