package service;

import repository.impl.CSVRepository;

public class ReconciliationServiceFactory {

    public static ReconciliationService get(ReconciliationServiceStrategy type) {

        if (type.equals(ReconciliationServiceStrategy.CSV_FILE)) {
            return new CsvFileReconciliationService(new CSVRepository());
        }

        throw new UnsupportedClassVersionError("ReconciliationService type : " + type + " not supported");
    }

}