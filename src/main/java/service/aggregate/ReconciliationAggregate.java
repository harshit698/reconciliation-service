package service.aggregate;

import org.apache.commons.csv.CSVRecord;
import service.aggregate.impl.CsvRecordMatches;

import java.util.List;

public interface ReconciliationAggregate<T, V extends RecordMatches<T>> {

    void putSingleExactMatch(T firstRecord, T secondRecord);

//  void putExactMatches(List<T> exactMatches);

    void putSinglePartialMatch(T firstRecord, T secondRecord);

//  void fillPartialMatches(List<T> partialMatch);

    void putSingleOnlyInFirstFile(T onlyInFirstFile);

    void putSingleOnlyInSecondFile(T onlyInSecondFile);

    List<V> getExactMatches();

    List<V> getPartialMatches();

    List<CSVRecord> getOnlyInFirstFileList();

    List<CSVRecord> getOnlyInSecondFileList();

//  void fillOnlyInFirstFileList(List<T> onlyInFirstFileList);

//  void fillOnlyInSecondFileList(List<T> onlyInSecondFileList);

//  List<T> getExactMatches();
}