package service.aggregate;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public interface ReconciliationAggregate<T, V extends RecordMatches<T>> {

    void putSingleExactMatch(T firstRecord, T secondRecord);

    void putSinglePartialMatch(T firstRecord, T secondRecord);

    void putSingleOnlyInFirstFile(T onlyInFirstFile);

    void putSingleOnlyInSecondFile(T onlyInSecondFile);

    List<V> getExactMatches();

    List<V> getPartialMatches();

    List<CSVRecord> getOnlyInFirstFileList();

    List<CSVRecord> getOnlyInSecondFileList();
}