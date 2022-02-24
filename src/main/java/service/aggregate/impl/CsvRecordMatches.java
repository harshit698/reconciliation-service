package service.aggregate.impl;

import org.apache.commons.csv.CSVRecord;
import service.aggregate.RecordMatches;

public class CsvRecordMatches extends RecordMatches<CSVRecord> {

    public CsvRecordMatches(CSVRecord firstRecord, CSVRecord secondRecord) {
        super(firstRecord, secondRecord);
    }

    public CSVRecord getFirstRecord() {
        return firstRecord;
    }

    public CSVRecord getSecondRecord() {
        return secondRecord;
    }
}