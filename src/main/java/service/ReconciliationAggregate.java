package service;

import java.util.List;
import org.apache.commons.csv.CSVRecord;

public class ReconciliationAggregate {

    private List<CSVRecord> exactMatches;
    private List<CSVRecord> partialMatches;
    private List<CSVRecord> onlyInBuyer;
    private List<CSVRecord> onlyInSupplier;
}