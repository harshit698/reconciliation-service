package similaritymetric.datesimilarity.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import similaritymetric.datesimilarity.DateSimilarityMetric;

public class ThresholdBasedDateTimeSimilarityMetric implements DateSimilarityMetric {

    private double threshold;

    public ThresholdBasedDateTimeSimilarityMetric(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public double compute(LocalDateTime firstDateTime, LocalDateTime secondDateTime) {

        if (firstDateTime.equals(secondDateTime)) {
            return 0.0;
        }

        double firstDateTimeSecondDateTimeDiff = Math.abs(ChronoUnit.SECONDS.between(firstDateTime, secondDateTime));
        return firstDateTimeSecondDateTimeDiff <= threshold? firstDateTimeSecondDateTimeDiff: Double.MAX_VALUE;
    }
}