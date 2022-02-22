package service.bestpartialmatch;

import java.util.List;

public interface BestPartialMatchStrategy<T> {

    int getBestPartialMatchIndex(List<T> partialMatches);

}