package request;

import service.ReconciliationEntityReferences;

public class ReconciliationRequest {
    private final ReconciliationEntityReferences entityReferences;
    private final DateSimilarityMetricRequest dateSimilarityMetricRequest;
    private final NumberSimilarityMetricRequest numberSimilarityMetricRequest;
    private final TextSimilarityMetricRequest textSimilarityMetricRequest;

    public ReconciliationRequest(ReconciliationEntityReferences entityReferences,
                                 DateSimilarityMetricRequest dateSimilarityMetricRequest,
                                 NumberSimilarityMetricRequest numberSimilarityMetricRequest,
                                 TextSimilarityMetricRequest textSimilarityMetricRequest) {
        this.entityReferences = entityReferences;
        this.dateSimilarityMetricRequest = dateSimilarityMetricRequest;
        this.numberSimilarityMetricRequest = numberSimilarityMetricRequest;
        this.textSimilarityMetricRequest = textSimilarityMetricRequest;
    }

    public DateSimilarityMetricRequest getDateSimilarityMetricRequest() {
        return dateSimilarityMetricRequest;
    }

    public NumberSimilarityMetricRequest getNumberSimilarityMetricRequest() {
        return numberSimilarityMetricRequest;
    }

    public TextSimilarityMetricRequest getTextSimilarityMetricRequest() {
        return textSimilarityMetricRequest;
    }

    public ReconciliationEntityReferences getEntityReferences() {
        return entityReferences;
    }
}