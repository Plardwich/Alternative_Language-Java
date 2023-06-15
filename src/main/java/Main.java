import com.opencsv.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String[]> listOfLines = readInData("src/main/resources/cells.csv");
        CollectionOfCells cells = new CollectionOfCells(listOfLines);
        List<String> oems = cells.getListOfOems();
        System.out.println(findLargestWeight(cells));
        System.out.println("......................");
        System.out.println(cells.getPhoneAnnouncedAndReleasedDifferently().toString());
        System.out.println("......................");
        printOneSensor(cells);
        System.out.println("......................");
        System.out.println(cells.mostCommonLaunchDate());
    }

    public static List<String[]> readInData(String fileName) throws FileNotFoundException {
        List<String[]> lines = new ArrayList<>();
        File csvFile = new File(fileName);
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
        return lines;
    }

    public static String findLargestWeight(CollectionOfCells cells) {
        List<String> oems = cells.getListOfOems();
        float maxWeight = -1;
        String maxOem = "";
        for (String oem : oems) {
            CollectionOfCells phones = new CollectionOfCells(cells.getAllPhonesFromOem(oem), true);
            float currWeight = phones.averageWeight();
            if (currWeight > maxWeight) {
                maxWeight = currWeight;
                maxOem = oem;
            }
        }
        return maxOem;
    }

    public static void printOneSensor(CollectionOfCells cells) {
        int count = 0;
        for(String phone : cells.getCellPhones().keySet()) {
            Cell phoneObject = cells.getCellPhones().get(phone);
            if (!phoneObject.getFeaturesSensors().contains(",")) {
                System.out.println(phone);
                count++;
            }
        }
    }
}
