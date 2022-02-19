package datesimilarity.impl;

import datesimilarity.DateSimilarityMetric;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ThresholdBasedDateTimeSimilarityMetric implements DateSimilarityMetric {

    @Override
    public long compute(LocalDateTime firstDateTime, LocalDateTime secondDateTime, double threshold) {

        long firstDateTimeSecondDateTimeDiff = Math.abs(ChronoUnit.SECONDS.between(firstDateTime, secondDateTime));

        return firstDateTimeSecondDateTimeDiff <= threshold? firstDateTimeSecondDateTimeDiff: Long.MAX_VALUE;
    }
}