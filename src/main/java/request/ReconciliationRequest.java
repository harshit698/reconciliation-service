package request;

import similaritymetric.DateSimilarityMetricStrategy;
import similaritymetric.NumberSimilarityMetricStrategy;
import similaritymetric.TextSimilarityMetricStrategy;

public class ReconciliationRequest {

    private DateSimilarityMetricStrategy dateSimilarityMetricStrategy;
    private NumberSimilarityMetricStrategy numberSimilarityMetricStrategy;
    private TextSimilarityMetricStrategy textSimilarityMetricStrategy;

    public ReconciliationRequest(DateSimilarityMetricStrategy dateSimilarityMetricStrategy,
                                 NumberSimilarityMetricStrategy numberSimilarityMetricStrategy,
                                 TextSimilarityMetricStrategy textSimilarityMetricStrategy) {
        this.dateSimilarityMetricStrategy = dateSimilarityMetricStrategy;
        this.numberSimilarityMetricStrategy = numberSimilarityMetricStrategy;
        this.textSimilarityMetricStrategy = textSimilarityMetricStrategy;
    }

    public DateSimilarityMetricStrategy getDateSimilarityMetricStrategy() {
        return dateSimilarityMetricStrategy;
    }

    public NumberSimilarityMetricStrategy getNumberSimilarityMetricStrategy() {
        return numberSimilarityMetricStrategy;
    }

    public TextSimilarityMetricStrategy getTextSimilarityMetricStrategy() {
        return textSimilarityMetricStrategy;
    }
}