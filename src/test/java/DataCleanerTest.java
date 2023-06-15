import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import org.junit.Before;
import org.junit.Test;
import DataCleaning.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DataCleanerTest {
    private DataCleaner cleaner;
    private String[] line;

    @Before
    public void setup() throws FileNotFoundException {
        List<String[]> lines = new ArrayList<>();
        File csvFile = new File("src/main/resources/cells.csv");
        Scanner scan = new Scanner(csvFile);
        CSVParser parser = new CSVParserBuilder().withQuoteChar('"').withSeparator(',').build();
        try {
            while (scan.hasNextLine()) {
                String currLine = scan.nextLine();
                lines.add(parser.parseLine(currLine));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.cleaner = new DataCleaner();
        this.line = lines.get(1);
    }

    @Test
    public void cleanDataTest() {
        String[] stringData = {"Benefon", "Vega", "Discontinued", "145 x 56 x 23 mm (5.71 x 2.20 x 0.91 in)",
                               "Mini-SIM", "Monochrome graphic", "6 lines", "V1", ""};
        float[] floatData = {190, -1};
        int launchInteger = 1999;
        CleanData controlData = new CleanData(stringData, floatData, launchInteger);
        CleanData testData = this.cleaner.cleanData(this.line);
        assertEquals(controlData, testData);
    }
}
