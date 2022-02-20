package request;

import similaritymetric.TextSimilarityMetricStrategy;

public class TextSimilarityMetricRequest {

    private TextSimilarityMetricStrategy strategy;

    public TextSimilarityMetricRequest(TextSimilarityMetricStrategy strategy) {
        this.strategy = strategy;
    }

    public TextSimilarityMetricStrategy getStrategy() {
        return strategy;
    }
}