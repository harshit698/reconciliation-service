package request;

import similaritymetric.DateSimilarityMetricStrategy;

public class DateSimilarityMetricRequest {

    private DateSimilarityMetricStrategy strategy;
    private double threshold;

    public DateSimilarityMetricRequest(DateSimilarityMetricStrategy strategy, double threshold) {
        this.strategy = strategy;
        this.threshold = threshold;
    }

    public DateSimilarityMetricStrategy getStrategy() {
        return strategy;
    }

    public double getThreshold() {
        return threshold;
    }
}