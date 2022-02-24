package service.aggregate.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import service.aggregate.ReconciliationAggregate;

public class CsvReconciliationAggregate implements ReconciliationAggregate<CSVRecord> {

    private List<CsvRecordMatches> exactMatches = new ArrayList<>();
    private List<CsvRecordMatches> partialMatches = new ArrayList<>();
    private List<CSVRecord> onlyInFirstFileList = new ArrayList<>();
    private List<CSVRecord> onlyInSecondFileList = new ArrayList<>();

    @Override
    public void putSingleExactMatch(CSVRecord firstRecord, CSVRecord secondRecord) {
        this.exactMatches.add(new CsvRecordMatches(firstRecord, secondRecord));
    }

    @Override
    public void putSinglePartialMatch(CSVRecord firstRecord, CSVRecord secondRecord) {
        this.partialMatches.add(new CsvRecordMatches(firstRecord, secondRecord));
    }

    @Override
    public void putSingleOnlyInFirstFile(CSVRecord onlyInFirstFile) {
        this.onlyInFirstFileList.add(onlyInFirstFile);
    }

    @Override
    public void putSingleOnlyInSecondFile(CSVRecord onlyInSecondFile) {
        this.onlyInFirstFileList.add(onlyInSecondFile);
    }

    @Override
    public List<CsvRecordMatches> getExactMatches() {
        return exactMatches;
    }

    @Override
    public List<CsvRecordMatches> getPartialMatches() {
        return partialMatches;
    }

    @Override
    public List<CSVRecord> getOnlyInFirstFileList() {
        return onlyInFirstFileList;
    }

    @Override
    public List<CSVRecord> getOnlyInSecondFileList() {
        return onlyInSecondFileList;
    }
}