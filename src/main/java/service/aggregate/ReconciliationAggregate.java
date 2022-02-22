package service.aggregate;

import java.util.List;

public interface ReconciliationAggregate<T> {

    void putSingleExactMatch(T exactMatch);

    void putExactMatches(List<T> exactMatches);

    void putSinglePartialMatch(T partialMatch);

    void fillPartialMatches(List<T> partialMatch);

    void putSingleOnlyInBuyer(T onlyInBuyer);

    void fillOnlyInBuyerList(List<T> onlyInBuyerList);

    void fillOnlyInSupplierList(List<T> onlyInSupplier);

    List<T> getExactMatches();
}