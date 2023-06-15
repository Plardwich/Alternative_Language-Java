import DataCleaning.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.regex.*;
@Getter
public class CollectionOfCells {
    private Map<String, Cell> cellPhones;

    public CollectionOfCells(List<String[]> listOfLines) {
        DataCleaner cleaner = new DataCleaner();
        this.cellPhones = new HashMap<String, Cell>();
        for(String[] line : listOfLines) {
            String name = line[0] + " " + line[1];
            if (!cellPhones.containsKey(name)) {
                cellPhones.put(line[0] + " " + line[1], new Cell(cleaner.cleanData(line)));
            }
        }
    }

    public CollectionOfCells(List<Cell> listOfCells, boolean isListOfCells) {
        this.cellPhones = new HashMap<String, Cell>();
        for(Cell phone : listOfCells)
        {
            this.cellPhones.put(phone.getOem() + " " + phone.getModel(), phone);
        }
    }

    //Gets a cell phone based on the oem and model. If not found, throws an ArgumentException
    public Cell getCellPhone(String phone) {
        if (!this.cellPhones.containsKey(phone)) {
            throw new IllegalArgumentException();
        }
        return this.cellPhones.get(phone);
    }

    //Adds a cell phone to the collection
    public void addCellPhone(String oem, String model, int launchAnnounced, String launchStatus,
                             String bodyDim, float bodyW, String bodyS, String displayType,
                             float displayS, String displayR, String features, String os) {
        Cell newCell = new Cell(oem, model, launchAnnounced, launchStatus, bodyDim, bodyW,
                bodyS, displayType, displayS, displayR, features, os);
        this.cellPhones.put(oem + " " + model, newCell);
    }

    //Returns a list of all phones from the specified oem
    //Runs in O(n) time where n is the cellPhones dictionary
    public List<Cell> getAllPhonesFromOem(String oem) {
        List<Cell> listOfPhones = new ArrayList<Cell>();
        for (String phone : this.cellPhones.keySet()) {
            Cell phoneObject = this.cellPhones.get(phone);
            if (phoneObject.getOem().equals(oem)) {
                listOfPhones.add(phoneObject);
            }
        }
        return listOfPhones;
    }

    //returns a list of all phone which had different announcment and release dates
    //Runs in O(n) time where n is the cellPhones dictionary
    public List<String> getPhoneAnnouncedAndReleasedDifferently() {
        List<String> phones = new ArrayList<String>();
        for (String phone : this.cellPhones.keySet()) {
            Cell phoneObject = this.cellPhones.get(phone);
            String launchStatus = phoneObject.getLaunchStatus();
            if (!phoneObject.getLaunchStatus().equals("") && phoneObject.getLaunchAnnounced() != -1 &&
                !launchStatus.equals("Discontinued") && !launchStatus.equals("Cancelled") &&
                phoneObject.getLaunchAnnounced() != Integer.parseInt(phoneObject.getLaunchStatus())) {

                phones.add(phone);
            }
        }
        return phones;
    }

    //returns a string representing the most common launch date
    //Runs in O(n + m) time where n is the cellPhones dictionary nad m is the dictionary represeting all launch dates
    public String mostCommonLaunchDate() {
        Map<String, Integer> launchYears = new HashMap<String, Integer>();
        Pattern yearPattern = Pattern.compile("\\d{4}");
        for (String phone : this.cellPhones.keySet()) {
            String launchDate = this.cellPhones.get(phone).getLaunchStatus();
            Matcher matcher = yearPattern.matcher(launchDate);
            if (matcher.find()) {
                String newLaunchDate = matcher.group();
                if (!launchYears.containsKey(newLaunchDate)) {
                    launchYears.put(newLaunchDate, 0);
                }
                launchYears.replace(newLaunchDate, launchYears.get(newLaunchDate) + 1);
            }
        }
        int max = -1;
        String maxLaunchDate = "";
        for (String launchDate : launchYears.keySet()) {
            if (launchYears.get(launchDate) > max) {
                max = launchYears.get(launchDate);
                maxLaunchDate = launchDate;
            }
        }
        return maxLaunchDate;
    }

    //Returns the average weight of the phones in the collection
    //Runs in O(n) time where n is the cellPhones dictionary
    public float averageWeight() {
        float totalWeight = 0;
        int weightCount = 0;
        for(String cellPhone : this.cellPhones.keySet()) {
            float weight = this.cellPhones.get(cellPhone).getBodyWeight();
            if (weight != -1) {
                totalWeight += weight;
                weightCount++;
            }
        }
        return totalWeight / weightCount;
    }

    //Returns the average display size of the phones in the collection
    //Runs in O(n) time where n is the cellPhones dictionary
    public float AverageDisplaySize() {
        float totalSize = 0;
        int sizeCount = 0;
        for (String cellPhone : this.cellPhones.keySet()) {
            float size = this.cellPhones.get(cellPhone).getDisplaySize();
            if (size != -1) {
                totalSize += size;
                sizeCount++;
            }
        }
        return totalSize / sizeCount;
    }

    //returns a list of all oems in collection
    //Runs in O(n) time where n is the cellPhones dictionary
    public List<String> getListOfOems() {
        List<String> oems = new ArrayList<String>();
        for(String phone : this.cellPhones.keySet()) {
            String oem = this.cellPhones.get(phone).getOem();
            if (!oems.contains(oem)) {
                oems.add(oem);
            }
        }
        return oems;
    }

    //returns a string representing all of the names of the phones in the collection
    //Runs in O(n) time where n is the cellPhones dictionary
    public String ToString() {
        StringBuilder outputString = new StringBuilder();
        for (String cellPhone : this.cellPhones.keySet()) {
            outputString.append(cellPhone + "\n");
        }
        return outputString.toString();
    }
}
