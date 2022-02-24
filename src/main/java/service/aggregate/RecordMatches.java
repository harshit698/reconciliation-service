package service.aggregate;

public abstract class RecordMatches<T> {

    protected T firstRecord;
    protected T secondRecord;

    public RecordMatches(T firstRecord, T secondRecord) {
        this.firstRecord = firstRecord;
        this.secondRecord = secondRecord;
    }

    public T getFirstRecord() {
        return firstRecord;
    }

    public T getSecondRecord() {
        return secondRecord;
    }
}