import lombok.Getter;
import lombok.Setter;
import DataCleaning.*;

@Getter @Setter
public class Cell {
    private String oem;
    private String model;
    private int launchAnnounced;
    private String launchStatus;
    private String bodyDimensions;
    private float bodyWeight;
    private String bodySim;
    private String displayType;
    private float displaySize;
    private String displayResolution;
    private String featuresSensors;
    private String platformOS;

    public Cell(CleanData cleanData) {
        String[] stringData = cleanData.getStringData();
        float[] floatData = cleanData.getFloatData();
        this.oem = stringData[0];
        this.model = stringData[1];
        this.launchAnnounced = cleanData.getIntData();
        this.launchStatus = stringData[2];
        this.bodyDimensions = stringData[3];
        this.bodyWeight = floatData[0];
        this.bodySim = stringData[4];
        this.displayType = stringData[5];
        this.displaySize = floatData[1];
        this.displayResolution = stringData[6];
        this.featuresSensors = stringData[7];
        this.platformOS = stringData[8];
    }

    public Cell(String oem, String model, int launchAnnounced, String launchStatus, String bodyDim,
                float bodyW, String bodyS, String displayType, float displayS, String displayR,
                String features, String os) {
        this.oem = oem;
        this.model = model;
        this.launchAnnounced = launchAnnounced;
        this.launchStatus = launchStatus;
        this.bodyDimensions = bodyDim;
        this.bodyWeight = bodyW;
        this.bodySim = bodyS;
        this.displayType = displayType;
        this.displaySize = displayS;
        this.displayResolution = displayR;
        this.featuresSensors = features;
        this.platformOS = os;
    }

    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Name: " + this.oem + " " + this.model + "\n");
        outputString.append("Announcment Date: " + this.launchAnnounced + "\n");
        outputString.append("Launch Status: " + this.launchStatus + "\n");
        outputString.append("Dimensions: " + this.bodyDimensions + "\n");
        outputString.append("Weight: " + this.bodyWeight + "\n");
        outputString.append("Sim Card: " + this.bodySim + "\n");
        outputString.append("Display Type: " + this.displayType + "\n");
        outputString.append("Display Size: " + this.displaySize + "\n");
        outputString.append("Display Resolution: " + this.displayResolution + "\n");
        outputString.append("Sensor Features: " + this.featuresSensors + "\n");
        outputString.append("Operating System: " + this.platformOS);
        return outputString.toString();
    }

    public boolean equals(Object otherCell) {
        if (otherCell instanceof Cell) {
            return equalsCell((Cell) otherCell);
        }
        return false;
    }

    private boolean equalsCell(Cell other) {
        return this.oem.equals(other.oem) &&
            this.model.equals(other.model) &&
            this.launchAnnounced == other.launchAnnounced &&
            this.launchStatus.equals(other.launchStatus) &&
            this.bodyDimensions.equals(other.bodyDimensions) &&
            this.bodyWeight == other.bodyWeight &&
            this.bodySim.equals(other.bodySim) &&
            this.displayType.equals(other.displayType) &&
            this.displaySize == other.displaySize &&
            this.displayResolution.equals(other.displayResolution) &&
            this.featuresSensors.equals(other.featuresSensors) &&
            this.platformOS.equals(other.platformOS);
    }
}
