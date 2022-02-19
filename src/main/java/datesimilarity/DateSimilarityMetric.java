package datesimilarity;

import java.time.LocalDateTime;

public interface DateSimilarityMetric {

    long compute(LocalDateTime firstDateTime, LocalDateTime secondDateTime, double threshold);
}