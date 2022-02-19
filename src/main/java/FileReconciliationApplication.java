import request.ReconciliationRequest;
import service.ReconciliationAggregate;
import service.ReconciliationService;
import service.ReconciliationServiceStrategy;
import similaritymetric.DateSimilarityMetricStrategy;
import similaritymetric.NumberSimilarityMetricStrategy;
import similaritymetric.TextSimilarityMetricStrategy;

public class FileReconciliationApplication {

    public static void main(String[] args) {

        String firstFileName = args[0];
        String secondFileName = args[1];

        ReconciliationRequest request = new ReconciliationRequest(
                DateSimilarityMetricStrategy.THRESHOLD_BASED,
                NumberSimilarityMetricStrategy.THRESHOLD_BASED,
                TextSimilarityMetricStrategy.LEVENSHTEIN_DISTANCE);

        ReconciliationService reconciliationService = new FileReconciliationService(ReconciliationServiceStrategy.CSV_FILE);

        ReconciliationAggregate reconciliationAggregate = reconciliationService.reconcile(request);
    }
}