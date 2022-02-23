package service.bestpartialmatch.impl;

import java.util.List;
import java.util.stream.IntStream;
import service.bestpartialmatch.BestPartialMatchStrategy;

public class SimilarityIndexSumBasedBestPartialMatcher implements BestPartialMatchStrategy<List<Double>> {

    @Override
    public int getBestPartialMatchIndex(List<List<Double>> partialMatchSimilarityVectors) {
        return IntStream.range(0, partialMatchSimilarityVectors.size())
                .reduce((i, j) -> {
                    List<Double> firstSimilarityVector = partialMatchSimilarityVectors.get(i);
                    List<Double> secondSimilarityVector = partialMatchSimilarityVectors.get(j);

                    double firstSimilarityVectorSum = firstSimilarityVector.stream().mapToDouble(Double::valueOf).sum();
                    double secondSimilarityVectorSum = secondSimilarityVector.stream().mapToDouble(Double::valueOf).sum();

                    return firstSimilarityVectorSum > secondSimilarityVectorSum ? i : j;
                })
                .orElseThrow(
                        () -> new RuntimeException("Internal error: Unable to find best partial match"));
    }
}