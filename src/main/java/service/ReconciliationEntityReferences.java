package service;

public class ReconciliationEntityReferences {

    private final String firstEntityReference;
    private final String secondEntityReference;

    public ReconciliationEntityReferences(String firstEntityReference, String secondEntityReference) {
        this.firstEntityReference = firstEntityReference;
        this.secondEntityReference = secondEntityReference;
    }

    public String getFirstEntityReference() {
        return firstEntityReference;
    }

    public String getSecondEntityReference() {
        return secondEntityReference;
    }
}