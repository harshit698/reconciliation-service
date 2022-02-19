package service;

import java.util.List;
import org.apache.commons.csv.CSVRecord;

public class CsvReconciliationAggregate implements ReconciliationAggregate<CSVRecord> {

    private List<CSVRecord> exactMatches;
    private List<CSVRecord> partialMatches ;
    private List<CSVRecord> onlyInBuyerList;
    private List<CSVRecord> onlyInSupplierList;


    @Override
    public void putExactMatch(CSVRecord exactMatch) {
        this.exactMatches.add(exactMatch);
    }

    @Override
    public void fillPartialMatches(CSVRecord partialMatch) {
        this.partialMatches.add(partialMatch);
    }

    @Override
    public void fillOnlyInBuyer(CSVRecord onlyInBuyer) {
        this.onlyInBuyerList.add(onlyInBuyer);
    }

    @Override
    public void fillOnlyInSupplier(CSVRecord onlyInSupplier) {
        this.onlyInSupplierList.add(onlyInSupplier);
    }

    public List<CSVRecord> getExactMatches() {
        return exactMatches;
    }

    public List<CSVRecord> getPartialMatches() {
        return partialMatches;
    }

    public List<CSVRecord> getOnlyInBuyerList() {
        return onlyInBuyerList;
    }

    public List<CSVRecord> getOnlyInSupplierList() {
        return onlyInSupplierList;
    }
}