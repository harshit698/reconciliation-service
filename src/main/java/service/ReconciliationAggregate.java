package service;

import java.util.List;
import org.apache.commons.csv.CSVRecord;

public interface ReconciliationAggregate<T> {

//    private List<CSVRecord> exactMatches;
//    private List<CSVRecord> partialMatches;
//    private List<CSVRecord> onlyInBuyer;
//    private List<CSVRecord> onlyInSupplier;

    void putExactMatch(T exactMatch);
    void fillPartialMatches(T partialMatch);
    void fillOnlyInBuyer(T onlyInBuyer);
    void fillOnlyInSupplier(T onlyInSupplier);
}