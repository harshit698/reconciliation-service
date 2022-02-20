package service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVRecord;

public class CsvReconciliationAggregate implements ReconciliationAggregate<CSVRecord> {

    private List<CSVRecord> exactMatches;
    private List<CSVRecord> partialMatches ;
    private List<CSVRecord> onlyInBuyerList;
    private List<CSVRecord> onlyInSupplierList;


    @Override
    public void putSingleExactMatch(CSVRecord exactMatch) {
        this.exactMatches.add(exactMatch);
    }

    @Override
    public void putExactMatches(List<CSVRecord> exactMatch) {
        this.exactMatches = new ArrayList<>(exactMatch);
    }

    @Override
    public void putSinglePartialMatch(CSVRecord partialMatch) {
        this.partialMatches.add(partialMatch);
    }

    @Override
    public void fillPartialMatches(List<CSVRecord> partialMatches) {
        this.partialMatches = new ArrayList<>(partialMatches);
    }

    @Override
    public void putSingleOnlyInBuyer(CSVRecord onlyInBuyer) {
        this.onlyInBuyerList.add(onlyInBuyer);
    }

    @Override
    public void fillOnlyInBuyerList(List<CSVRecord> onlyInBuyerList) {
        this.onlyInBuyerList = new ArrayList<>(onlyInBuyerList);
    }

    @Override
    public void fillOnlyInSupplierList(List<CSVRecord> onlyInSupplierList) {
        this.onlyInSupplierList = new ArrayList<>(onlyInSupplierList);
    }

    @Override
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