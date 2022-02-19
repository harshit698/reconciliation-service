package repository.impl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat.Builder;
import org.apache.commons.csv.CSVRecord;
import repository.Repository;

public class CSVRepository implements Repository {

    public static List<CSVRecord> readCsvRecords(String fileName) {

        try {
            Reader fileReader = new FileReader(fileName);
            return Builder.create().setSkipHeaderRecord(true).build()
                    .parse(fileReader)
                    .stream()
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Cannot parse: " + fileName);
        }
    }

}