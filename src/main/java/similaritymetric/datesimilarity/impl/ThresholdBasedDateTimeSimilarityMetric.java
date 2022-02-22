package similaritymetric.datesimilarity.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
            return 1;
        }

        long firstDateTimeEpoch = firstDateTime.toEpochSecond(ZoneOffset.UTC);
        long secondDateTimeEpoch = secondDateTime.toEpochSecond(ZoneOffset.UTC);

        long largerDateTimeEpoch = Math.max(firstDateTimeEpoch, secondDateTimeEpoch);
        long smallerDateTimeEpoch = Math.min(firstDateTimeEpoch, secondDateTimeEpoch);
        double similarityIndex = smallerDateTimeEpoch/(double) largerDateTimeEpoch;

        return similarityIndex <= threshold? similarityIndex: 0;
    }
}