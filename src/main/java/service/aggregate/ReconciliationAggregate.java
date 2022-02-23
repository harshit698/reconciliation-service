package service.aggregate;

public interface ReconciliationAggregate<T> {

    void putSingleExactMatch(T firstRecord, T secondRecord);

//  void putExactMatches(List<T> exactMatches);

    void putSinglePartialMatch(T firstRecord, T secondRecord);

//  void fillPartialMatches(List<T> partialMatch);

    void putSingleOnlyInFirstFile(T onlyInFirstFile);

    void putSingleOnlyInSecondFile(T onlyInSecondFile);

//  void fillOnlyInFirstFileList(List<T> onlyInFirstFileList);

//  void fillOnlyInSecondFileList(List<T> onlyInSecondFileList);

//  List<T> getExactMatches();
}