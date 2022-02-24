package service.aggregate.impl;

import org.apache.commons.csv.CSVRecord;

public class CsvRecordMatches {

    private CSVRecord firstRecord;
    private CSVRecord secondRecord;

    public CsvRecordMatches(CSVRecord firstRecord, CSVRecord secondRecord) {
        this.firstRecord = firstRecord;
        this.secondRecord = secondRecord;
    }
}