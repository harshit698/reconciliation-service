
import request.DateSimilarityMetricRequest;
import request.NumberSimilarityMetricRequest;
import request.ReconciliationRequest;
import request.TextSimilarityMetricRequest;
import service.ReconciliationAggregate;
import service.ReconciliationEntityReferences;
import service.ReconciliationService;
import service.ReconciliationServiceFactory;
import service.ReconciliationServiceStrategy;
import similaritymetric.DateSimilarityMetricStrategy;
import similaritymetric.NumberSimilarityMetricStrategy;
import similaritymetric.TextSimilarityMetricStrategy;

public class FileReconciliationApplication {

    public static void main(String[] args) {

        String firstFileName = args[0];
        String secondFileName = args[1];

        NumberSimilarityMetricRequest numberSimilarityMetricRequest = new NumberSimilarityMetricRequest(
                NumberSimilarityMetricStrategy.THRESHOLD_BASED, 2);
        DateSimilarityMetricRequest dateSimilarityMetricRequest = new DateSimilarityMetricRequest(
                DateSimilarityMetricStrategy.THRESHOLD_BASED, 2);
        ReconciliationRequest request = new ReconciliationRequest(
                new ReconciliationEntityReferences(firstFileName, secondFileName),
                dateSimilarityMetricRequest, numberSimilarityMetricRequest,
                new TextSimilarityMetricRequest(TextSimilarityMetricStrategy.LEVENSHTEIN_DISTANCE));

        ReconciliationService reconciliationService = ReconciliationServiceFactory.get(ReconciliationServiceStrategy.CSV_FILE);

        ReconciliationAggregate reconciliationAggregate = reconciliationService.reconcile(request);
    }

}