package service;

import request.ReconciliationRequest;
import similaritymetric.SimilarityMetricFactory;
import similaritymetric.datesimilarity.DateSimilarityMetric;
import similaritymetric.numbersimilarity.NumberSimilarityMetric;
import similaritymetric.textsimilarity.TextSimilarityMetric;

public abstract class ReconciliationService {

//  protected ToBeReconciledIdentifierPair toBeReconciledIdentifierPair;

    protected DateSimilarityMetric dateSimilarityMetric;
    protected NumberSimilarityMetric numberSimilarityMetric;
    protected TextSimilarityMetric textSimilarityMetric;

//  public ReconciliationService(Repository repository) {
//    this.repository = repository;
//  }

    public ReconciliationAggregate reconcile(ReconciliationRequest request) {
        initializeSimilarityMetrics(request);
        return process();
    }

    private void initializeSimilarityMetrics(ReconciliationRequest request) {
        dateSimilarityMetric = SimilarityMetricFactory.getDateSimilarityMetric(
                request.getDateSimilarityMetricStrategy());
        numberSimilarityMetric = SimilarityMetricFactory.getNumberSimilarityMetric(
                request.getNumberSimilarityMetricStrategy());
        textSimilarityMetric = SimilarityMetricFactory.getTextSimilarityMetric(
                request.getTextSimilarityMetricStrategy());
    }

    protected abstract ReconciliationAggregate process();

}