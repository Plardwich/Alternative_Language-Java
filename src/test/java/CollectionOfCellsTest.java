import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import DataCleaning.*;

import static org.junit.Assert.*;

public class CollectionOfCellsTest {
    private CollectionOfCells cells;
    private Cell controlCell;

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
        this.cells = new CollectionOfCells(lines);
        DataCleaner cleaner = new DataCleaner();
        CleanData cleanData = cleaner.cleanData(lines.get(1));
        this.controlCell = new Cell(cleanData);
    }

    @Test
    public void getCellPhoneTest() {
        assertEquals(this.cells.getCellPhone("Benefon Vega"), this.controlCell);
    }

    @Test
    public void getAllPhonesFromOemTest() {
        List<Cell> oemCells = new ArrayList<>();
        oemCells.add(this.controlCell);
        assertEquals(oemCells, this.cells.getAllPhonesFromOem("Benefon"));
    }

    @Test
    public void mostCommonLaunchDateTest() {
        String commonLaunchDateControl = "2019";
        assertEquals(this.cells.mostCommonLaunchDate(), commonLaunchDateControl);
    }

    @Test
    public void getListOfOems() {
        String controlOem = "Benefon";
        List<String> oems = this.cells.getListOfOems();
        assert(oems.contains(controlOem));
    }
}
