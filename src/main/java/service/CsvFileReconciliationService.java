package service;

import static service.SupportedValueDataTypes.DATETIME;
import static service.SupportedValueDataTypes.NUMBER;
import static service.SupportedValueDataTypes.TEXT;

import java.util.Collections;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import repository.CsvRepository;

public class CsvFileReconciliationService extends ReconciliationService<CSVRecord> {

    private final CsvRepository repository;

    private List<CSVRecord> firstFileRecords;
    private List<CSVRecord> secondFileRecords;

    public CsvFileReconciliationService(CsvRepository repository) {
        this.repository = repository;
    }

//  @Override
//  protected void initializeReconciliationEntities(ReconciliationRequest request) {
//    firstFileRecords = repository.readCsvRecords(request.getEntityReferences().getFirstEntityReference());
//    secondFileRecords = repository.readCsvRecords(request.getEntityReferences().getSecondEntityReference());
//  }

    @Override
    protected ReconciliationAggregate<CSVRecord> initReconciliationAggregateWithExactMatches() {


        return null;
    }

    @Override
    protected void populateDataTypeSequence(CSVRecord singleCsvRecord) {
        singleCsvRecord.stream()
                .map(value -> {
                    if (isValueDate(value)) {
                        return DATETIME;
                    } else if (isValueNumber(value)) {
                        return NUMBER;
                    }
                    return TEXT;
                })
                .forEach(dataTypeSequence::add);
    }


    private List<CSVRecord> getPartialMatches(List<CSVRecord> exactMatches) {
        return Collections.emptyList();
    }

}