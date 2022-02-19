package request;

import service.ReconciliationEntityReferences;
import similaritymetric.DateSimilarityMetricStrategy;
import similaritymetric.NumberSimilarityMetricStrategy;
import similaritymetric.TextSimilarityMetricStrategy;

public class ReconciliationRequest {
    private final ReconciliationEntityReferences entityReferences;

    public ReconciliationRequest(ReconciliationEntityReferences entityReferences,
                                 DateSimilarityMetricStrategy dateSimilarityMetricStrategy,
                                 NumberSimilarityMetricStrategy numberSimilarityMetricStrategy,
                                 TextSimilarityMetricStrategy textSimilarityMetricStrategy) {
        this.entityReferences = entityReferences;
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

    public ReconciliationEntityReferences getEntityReferences() {
        return entityReferences;
    }
}