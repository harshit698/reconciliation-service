package service;

import repository.impl.CSVRepository;
import service.strategy.ReconciliationServiceStrategy;

public class ReconciliationServiceFactory {

    public static ReconciliationService get(ReconciliationServiceStrategy type) {

        if (type.equals(ReconciliationServiceStrategy.CSV_FILE)) {
            return new CsvFileReconciliationService(new CSVRepository());
        }

        throw new UnsupportedClassVersionError("ReconciliationService type : " + type + " not supported");
    }

}