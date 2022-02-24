
import request.DateSimilarityMetricRequest;
import request.NumberSimilarityMetricRequest;
import request.ReconciliationRequest;
import request.TextSimilarityMetricRequest;
import service.aggregate.ReconciliationAggregate;
import service.ReconciliationEntityReferences;
import service.strategy.ReconciliationService;
import service.ReconciliationServiceFactory;
import service.ReconciliationServiceStrategy;
import similaritymetric.DateSimilarityMetricStrategy;
import similaritymetric.NumberSimilarityMetricStrategy;
import similaritymetric.TextSimilarityMetricStrategy;

public class FileReconciliationApplication {

    public static void main(String[] args) {

        String firstFileName = "src/main/resources/Buyer2.csv";
        String secondFileName = "src/main/resources/Supplier.csv";

        NumberSimilarityMetricRequest numberSimilarityMetricRequest = new NumberSimilarityMetricRequest(
                NumberSimilarityMetricStrategy.THRESHOLD_BASED, 0.5);
        DateSimilarityMetricRequest dateSimilarityMetricRequest = new DateSimilarityMetricRequest(
                DateSimilarityMetricStrategy.THRESHOLD_BASED, 0.5);
        TextSimilarityMetricRequest textSimilarityMetricRequest = new TextSimilarityMetricRequest(
                TextSimilarityMetricStrategy.LEVENSHTEIN_DISTANCE);
        ReconciliationEntityReferences reconciliationEntityReferences = new ReconciliationEntityReferences(
                firstFileName, secondFileName);
        ReconciliationRequest request = new ReconciliationRequest(
                reconciliationEntityReferences,
                dateSimilarityMetricRequest,
                numberSimilarityMetricRequest,
                textSimilarityMetricRequest
        );

        ReconciliationService reconciliationService = ReconciliationServiceFactory.get(ReconciliationServiceStrategy.CSV_FILE);

        ReconciliationAggregate reconciliationAggregate = reconciliationService.reconcile(request);
    }

}