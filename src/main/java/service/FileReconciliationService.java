package service;

import java.util.Collections;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import service.ReconciliationAggregate;

public class FileReconciliationService {

    private final List<CSVRecord> firstFile;
    private final List<CSVRecord> secondFile;

    public FileReconciliationService(List<CSVRecord> firstFile, List<CSVRecord> secondFile) {
        this.firstFile = firstFile;
        this.secondFile = secondFile;
    }

    public ReconciliationAggregate reconcile() {

        ReconciliationAggregate reconciliationAggregate = new ReconciliationAggregate();

        List<CSVRecord> exactMatches = getExactMatches();

        List<CSVRecord> partialMatches = getPartialMatches(exactMatches);

        return new ReconciliationAggregate();
    }

    private List<CSVRecord> getExactMatches() {

//    firstFile.stream().filter(csvRecord -> secondFile)
        return Collections.emptyList();
    }

    private List<CSVRecord> getPartialMatches(List<CSVRecord> exactMatches) {
        return Collections.emptyList();
    }
}