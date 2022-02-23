package service.strategy.impl;

import static service.SupportedValueDataTypes.DATETIME;
import static service.SupportedValueDataTypes.NUMBER;
import static service.SupportedValueDataTypes.TEXT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.csv.CSVRecord;
import repository.impl.CSVRepository;
import request.ReconciliationRequest;
import service.SupportedValueDataTypes;
import service.aggregate.ReconciliationAggregate;
import service.aggregate.impl.CsvReconciliationAggregate;
import service.bestpartialmatch.BestPartialMatchStrategy;
import service.bestpartialmatch.impl.SimilarityIndexSumBasedBestPartialMatcher;
import service.strategy.ReconciliationService;

public class CsvFileReconciliationService extends ReconciliationService<CSVRecord> {

    private final CSVRepository repository;
    private final BestPartialMatchStrategy<List<Double>> bestPartialMatcher = new SimilarityIndexSumBasedBestPartialMatcher();

    public CsvFileReconciliationService(CSVRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void populateDataTypeSequence(List<CSVRecord> firstFileCsvRecords, List<CSVRecord> secondFileCsvRecord) {

        List<CSVRecord> allCsvRecords = Stream.concat(firstFileCsvRecords.stream(), secondFileCsvRecord.stream())
                .collect(Collectors.toList());

        int numberOfColumns = firstFileCsvRecords.get(0).size();

        for(int i = 0; i < numberOfColumns; i++) {
            boolean foundNonEmptyValue = false;
            for (CSVRecord csvRecord : allCsvRecords) {
                String value = csvRecord.get(i);
                if (!value.isBlank()) {
                    foundNonEmptyValue = true;
                    if (isValueDate(value)) {
                        dataTypeSequence.add(DATETIME);
                        break;
                    } else if (isValueNumber(value)) {
                        dataTypeSequence.add(NUMBER);
                        break;
                    } else {
                        dataTypeSequence.add(TEXT);
                        break;
                    }
                }
            }

            if (!foundNonEmptyValue) {
                dataTypeSequence.add(TEXT);
            }
        }
    }

    @Override
    protected ReconciliationAggregate<CSVRecord> process(List<CSVRecord> firstFileCsvRecords, List<CSVRecord> secondFileCsvRecords) {

        int firstFileIteratorIndex = 0;
        int secondFileIteratorIndex = 0;

        ReconciliationAggregate<CSVRecord> reconciliationAggregate = new CsvReconciliationAggregate();

        while (firstFileIteratorIndex < firstFileCsvRecords.size()) {
            CSVRecord firstFileCsvRecord = firstFileCsvRecords.get(firstFileIteratorIndex);
            Map<Integer, List<Double>> secondFileIndexToSimilarityVectorMap = new HashMap<>();

            List<Double> similarityVector;
            boolean foundExactMatch = false;
            boolean onlyInFirstFile = false;
            while (secondFileIteratorIndex < secondFileCsvRecords.size()) {
                CSVRecord secondFileCsvRecord = secondFileCsvRecords.get(secondFileIteratorIndex);

                similarityVector = IntStream.range(0, dataTypeSequence.size())
                        .boxed()
                        .map(i -> {
                            SupportedValueDataTypes currentDataType = dataTypeSequence.get(i);
                            String firstFileColumnValue = firstFileCsvRecord.get(i);
                            String secondFileColumnValue = secondFileCsvRecord.get(i);
                            if (currentDataType.equals(DATETIME)) {
                                return getDateSimilarityMetricIndex(firstFileColumnValue, secondFileColumnValue);
                            } else if (currentDataType.equals(NUMBER)) {
                                return getNumberSimilarityMetricIndex(firstFileColumnValue, secondFileColumnValue);
                            } else {
                                return getTextSimilarityMetricIndex(firstFileColumnValue, secondFileColumnValue);
                            }
                        })
                        .collect(Collectors.toList());

                if (areAllSimilarityIndexOneIn(similarityVector)) {
                    foundExactMatch = true;
                    reconciliationAggregate.putSingleExactMatch(firstFileCsvRecord, secondFileCsvRecord);
                    break;
                } else if (isAtleastOneSimilarityIndexZeroIn(similarityVector)) {
                    onlyInFirstFile = true;
                    reconciliationAggregate.putSingleOnlyInFirstFile(firstFileCsvRecord);
                    break;
                } else {
                    secondFileIndexToSimilarityVectorMap.put(secondFileIteratorIndex, similarityVector);
                    secondFileIteratorIndex++;
                }
            }

            if (!foundExactMatch && !onlyInFirstFile) {
                int bestPartialMatchIndex = getBestPartialMatchRecordIndex(secondFileIndexToSimilarityVectorMap);
                reconciliationAggregate.putSinglePartialMatch(firstFileCsvRecord, secondFileCsvRecords.get(bestPartialMatchIndex));
            }

            firstFileIteratorIndex++;
        }

        return reconciliationAggregate;
    }

    private double getTextSimilarityMetricIndex(String firstFileColumnValue, String secondFileColumnValue) {

        Optional<Double> blankBasedSimilarityIndex = computeBlankBasedSimilarityIndex(firstFileColumnValue,
                secondFileColumnValue);

        if (blankBasedSimilarityIndex.isEmpty()) {
            return textSimilarityMetric.compute(firstFileColumnValue, secondFileColumnValue);
        }

        return blankBasedSimilarityIndex.get();
    }

    private double getNumberSimilarityMetricIndex(String firstFileColumnValue, String secondFileColumnValue) {

        Optional<Double> blankBasedSimilarityIndex = computeBlankBasedSimilarityIndex(firstFileColumnValue,
                secondFileColumnValue);

        if (blankBasedSimilarityIndex.isEmpty()) {
            double firstNumber = Double.parseDouble(firstFileColumnValue);
            double secondNumber = Double.parseDouble(secondFileColumnValue);

            return numberSimilarityMetric.compute(firstNumber, secondNumber);
        }

        return blankBasedSimilarityIndex.get();
    }

    private double getDateSimilarityMetricIndex(String firstFileColumnValue, String secondFileColumnValue) {

        Optional<Double> blankBasedSimilarityIndex = computeBlankBasedSimilarityIndex(firstFileColumnValue,
                secondFileColumnValue);

        if (blankBasedSimilarityIndex.isEmpty()) {
            LocalDate firstDate = LocalDate.parse(firstFileColumnValue);
            LocalDate secondDate = LocalDate.parse(secondFileColumnValue);
            return dateSimilarityMetric.compute(firstDate, secondDate);
        }

        return blankBasedSimilarityIndex.get();
    }

    private Optional<Double> computeBlankBasedSimilarityIndex(String firstFileColumnValue, String secondFileColumnValue) {

        boolean isFirstFileColumnValueBlank = firstFileColumnValue.isBlank();
        boolean isSecondFileColumnValueBlank = secondFileColumnValue.isBlank();

        if (isFirstFileColumnValueBlank && isSecondFileColumnValueBlank) {
            return Optional.of(1.0);
        } else if (isFirstFileColumnValueBlank) {
            return Optional.of(0.0);
        } else if (isSecondFileColumnValueBlank) {
            return Optional.of(0.0);
        }

        return Optional.empty();
    }

    @Override
    protected List<CSVRecord> getFirstReconciliationEntityList(ReconciliationRequest request) {
        return repository.read(request.getEntityReferences().getFirstEntityReference());
    }

    @Override
    protected List<CSVRecord> getSecondReconciliationEntityList(ReconciliationRequest request) {
        return repository.read(request.getEntityReferences().getSecondEntityReference());
    }

    private int getBestPartialMatchRecordIndex(Map<Integer, List<Double>> secondFileIndexToSimilarityVectorMap) {

        LinkedHashMap<Integer, List<Double>> orderedIndexToSimilarityVectorMap = new LinkedHashMap<>(secondFileIndexToSimilarityVectorMap);

        List<List<Double>> partialMatchSimilarityVectors = new ArrayList<>(orderedIndexToSimilarityVectorMap.values());
        List<Integer> similarityIndexPositions = new ArrayList<>(secondFileIndexToSimilarityVectorMap.keySet());

        return similarityIndexPositions.get(bestPartialMatcher.getBestPartialMatchIndex(partialMatchSimilarityVectors));

    }

    private boolean areAllSimilarityIndexOneIn(List<Double> similarityIndexVector) {
        return similarityIndexVector.stream().allMatch(similarityIndex -> similarityIndex == 1);
    }

    private boolean isAtleastOneSimilarityIndexZeroIn(List<Double> similarityIndexVector) {
        return similarityIndexVector.stream().anyMatch(similarityIndex -> similarityIndex == 0);
    }
}