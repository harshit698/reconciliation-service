package service;

import repository.CsvRepository;

public class ReconciliationServiceFactory {

    public static ReconciliationService get(ReconciliationServiceStrategy type) {

        if (type.equals(ReconciliationServiceStrategy.CSV_FILE)) {
            return new CsvFileReconciliationService(new CsvRepository());
        }

        throw new UnsupportedClassVersionError("ReconciliationService type : " + type + " not supported");
    }

}