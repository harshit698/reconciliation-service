package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import request.ReconciliationRequest;
import service.aggregate.ReconciliationAggregate;
import similaritymetric.SimilarityMetricFactory;
import similaritymetric.datesimilarity.DateSimilarityMetric;
import similaritymetric.numbersimilarity.NumberSimilarityMetric;
import similaritymetric.textsimilarity.TextSimilarityMetric;

public abstract class ReconciliationService<T> {

    protected DateSimilarityMetric dateSimilarityMetric;
    protected NumberSimilarityMetric numberSimilarityMetric;
    protected TextSimilarityMetric textSimilarityMetric;

    protected List<SupportedValueDataTypes> dataTypeSequence;

    public final ReconciliationAggregate<T> reconcile(ReconciliationRequest request) {
        initializeSimilarityMetrics(request);
        List<T> firstReconciliationEntityList = getFirstReconciliationEntityList(request);
        List<T> secondReconciliationEntityList = getSecondReconciliationEntityList(request);

        populateDataTypeSequence(firstReconciliationEntityList.get(0));

        return process(firstReconciliationEntityList, secondReconciliationEntityList);
    }

    protected abstract List<T> getFirstReconciliationEntityList(ReconciliationRequest request);

    protected abstract List<T> getSecondReconciliationEntityList(
            ReconciliationRequest request);

    protected abstract ReconciliationAggregate<T> process(List<T> firstReconciliationEntityList,
                                                          List<T> secondReconciliationEntityList);

    private void initializeSimilarityMetrics(ReconciliationRequest request) {
        dateSimilarityMetric = SimilarityMetricFactory.getDateSimilarityMetric(request);
        numberSimilarityMetric = SimilarityMetricFactory.getNumberSimilarityMetric(request);
        textSimilarityMetric = SimilarityMetricFactory.getTextSimilarityMetric(request);
    }

    protected boolean isValueDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
        try {
            LocalDateTime.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    protected boolean isValueNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean isSimilarityVectorAZeroVector(List<Double> similarityVector) {
        return similarityVector.stream().noneMatch(elem -> elem != 0);
    }

    protected boolean isSimilarityVectorInfiniteVector(List<Double> similarityVector) {
        return similarityVector.stream().anyMatch(elem -> elem.equals(Double.MAX_VALUE));
    }

    protected abstract int getClosestSecondEntityRecordIndex(Map<Integer, List<Double>> secondFileIndexToSimilarityVectorMap);

    protected abstract void populateDataTypeSequence(T singleReconciliationEntity);

}