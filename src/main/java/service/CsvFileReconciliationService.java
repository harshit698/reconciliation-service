package service;

import static service.SupportedValueDataTypes.DATETIME;
import static service.SupportedValueDataTypes.NUMBER;
import static service.SupportedValueDataTypes.TEXT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.csv.CSVRecord;
import repository.impl.CSVRepository;
import request.ReconciliationRequest;

public class CsvFileReconciliationService extends ReconciliationService<CSVRecord> {

    private final CSVRepository repository;

    public CsvFileReconciliationService(CSVRepository repository) {
        this.repository = repository;
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

    @Override
    protected ReconciliationAggregate<CSVRecord> process(List<CSVRecord> firstFileCsvRecords,
                                                         List<CSVRecord> secondFileCsvRecords) {

        int firstFileIteratorIndex = 0;
        int secondFileIteratorIndex = 0;

        ReconciliationAggregate<CSVRecord> reconciliationAggregate = new CsvReconciliationAggregate();

        while (firstFileIteratorIndex < firstFileCsvRecords.size()) {
            CSVRecord firstFileCsvRecord = firstFileCsvRecords.get(firstFileIteratorIndex);
            Map<Integer, List<Double>> secondFileIndexToSimilarityVectorMap = new HashMap<>();

            List<Double> similarityVector = new ArrayList<>();
            while (secondFileIteratorIndex < secondFileCsvRecords.size()) {
                CSVRecord secondFileCsvRecord = secondFileCsvRecords.get(secondFileIteratorIndex);

                similarityVector = IntStream.range(0, dataTypeSequence.size())
                        .boxed()
                        .map(i -> {
                            SupportedValueDataTypes currentDataType = dataTypeSequence.get(i);
                            if (currentDataType.equals(DATETIME)) {
                                LocalDateTime firstDateTime = LocalDateTime.parse(firstFileCsvRecord.get(i));
                                LocalDateTime secondDateTime = LocalDateTime.parse(secondFileCsvRecord.get(i));
                                return dateSimilarityMetric.compute(firstDateTime, secondDateTime);
                            } else if (currentDataType.equals(NUMBER)) {
                                double firstNumber = Double.parseDouble(firstFileCsvRecord.get(i));
                                double secondNumber = Double.parseDouble(secondFileCsvRecord.get(i));
                                return numberSimilarityMetric.compute(firstNumber, secondNumber);
                            } else {
                                return textSimilarityMetric.compute(firstFileCsvRecord.get(i), secondFileCsvRecord.get(i));
                            }
                        })
                        .collect(Collectors.toList());

                if (isSimilarityVectorAZeroVector(similarityVector)) {
                    reconciliationAggregate.putSingleExactMatch(firstFileCsvRecord);
                    firstFileCsvRecords.remove(firstFileCsvRecord);
                    secondFileCsvRecords.remove(firstFileCsvRecord);
                    secondFileIteratorIndex = 0;
                    break;
                } else if (isSimilarityVectorInfiniteVector(similarityVector)) {
                    reconciliationAggregate.putSingleOnlyInBuyer(firstFileCsvRecord);
                    firstFileCsvRecords.remove(firstFileCsvRecord);
                } else {
                    secondFileIndexToSimilarityVectorMap.put(secondFileIteratorIndex, similarityVector);
                    secondFileIteratorIndex++;
                }
            }

            int closestSecondEntityRecordIndex = getClosestSecondEntityRecordIndex(secondFileIndexToSimilarityVectorMap);
            CSVRecord closestPartialMatch = secondFileCsvRecords.get(closestSecondEntityRecordIndex);
            reconciliationAggregate.putSinglePartialMatch(closestPartialMatch);

            firstFileCsvRecords.remove(closestPartialMatch);
            secondFileCsvRecords.remove(closestPartialMatch);
        }

        return reconciliationAggregate;
    }

    @Override
    protected List<CSVRecord> getFirstReconciliationEntityList(ReconciliationRequest request) {
        return repository.readCsvRecords(request.getEntityReferences().getFirstEntityReference());
    }

    @Override
    protected List<CSVRecord> getSecondReconciliationEntityList(ReconciliationRequest request) {
        return repository.readCsvRecords(request.getEntityReferences().getSecondEntityReference());
    }

    @Override
    protected int getClosestSecondEntityRecordIndex(Map<Integer, List<Double>> secondFileIndexToSimilarityVectorMap) {
        return secondFileIndexToSimilarityVectorMap.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream()
                        .mapToDouble(Double::doubleValue).sum()))
                .entrySet().stream()
                .min(Comparator.comparingDouble(Entry::getValue))
                .map(Entry::getKey)
                .orElseThrow(() -> new IllegalStateException("No closest entity record found"));
    }
}