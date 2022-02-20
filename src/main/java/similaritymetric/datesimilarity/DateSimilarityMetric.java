package similaritymetric.datesimilarity;

import java.time.LocalDateTime;

public interface DateSimilarityMetric {

    double compute(LocalDateTime firstDateTime, LocalDateTime secondDateTime);
}