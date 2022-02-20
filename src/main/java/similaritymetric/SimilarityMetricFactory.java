package similaritymetric;

import request.ReconciliationRequest;
import similaritymetric.datesimilarity.DateSimilarityMetric;
import similaritymetric.datesimilarity.impl.ThresholdBasedDateTimeSimilarityMetric;
import similaritymetric.numbersimilarity.NumberSimilarityMetric;
import similaritymetric.numbersimilarity.impl.ThresholdBasedNumberSimilarityMetric;
import similaritymetric.textsimilarity.TextSimilarityMetric;
import similaritymetric.textsimilarity.impl.LevenshteinDistanceTextSimilarity;

public class SimilarityMetricFactory {

    public static DateSimilarityMetric getDateSimilarityMetric(ReconciliationRequest request) {
        switch (request.getDateSimilarityMetricRequest().getStrategy()) {
            default:
                return new ThresholdBasedDateTimeSimilarityMetric(request.getDateSimilarityMetricRequest()
                        .getThreshold());
        }
    }

    public static TextSimilarityMetric getTextSimilarityMetric(ReconciliationRequest request) {
        switch (request.getTextSimilarityMetricRequest().getStrategy()) {
            default:
                return new LevenshteinDistanceTextSimilarity();
        }
    }

    public static NumberSimilarityMetric getNumberSimilarityMetric(ReconciliationRequest request) {
        switch (request.getNumberSimilarityMetricRequest().getStrategy()) {
            default:
                return new ThresholdBasedNumberSimilarityMetric(request.getNumberSimilarityMetricRequest().getThreshold());
        }
    }

}