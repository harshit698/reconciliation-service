package similaritymetric;

import similaritymetric.datesimilarity.DateSimilarityMetric;
import similaritymetric.datesimilarity.impl.ThresholdBasedDateTimeSimilarityMetric;
import similaritymetric.numbersimilarity.NumberSimilarityMetric;
import similaritymetric.numbersimilarity.impl.ThresholdBasedNumberSimilarityMetric;
import similaritymetric.textsimilarity.TextSimilarityMetric;
import similaritymetric.textsimilarity.impl.LevenshteinDistanceTextSimilarity;

public class SimilarityMetricFactory {

    public static DateSimilarityMetric getDateSimilarityMetric(DateSimilarityMetricStrategy strategy) {
        switch (strategy) {
            default:
                return new ThresholdBasedDateTimeSimilarityMetric();
        }
    }

    public static TextSimilarityMetric getTextSimilarityMetric(TextSimilarityMetricStrategy strategy) {
        switch (strategy) {
            default:
                return new LevenshteinDistanceTextSimilarity();
        }
    }

    public static NumberSimilarityMetric getNumberSimilarityMetric(NumberSimilarityMetricStrategy strategy) {
        switch (strategy) {
            default:
                return new ThresholdBasedNumberSimilarityMetric();
        }
    }

}