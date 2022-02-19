import java.util.List;
import org.apache.commons.csv.CSVRecord;
import util.CSVHelper;

public class FileReconciliationApplication {

    public static void main(String[] args) {

        List<CSVRecord> firstFile = CSVHelper.readCsvStream(args[0]);
        List<CSVRecord> secondFile = CSVHelper.readCsvStream(args[1]);

        FileReconciliationService reconciliationService = new FileReconciliationService(firstFile, secondFile);

        ReconciliationAggregate reconciliationAggregate = reconciliationService.reconcile();
    }
}