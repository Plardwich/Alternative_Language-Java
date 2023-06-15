package DataCleaning;

import java.util.Arrays;

public class CleanData {
    private String[] stringData;
    private float[] floatData;
    private int intData;
    public CleanData(String[] stringData, float[] floatData, int intData) {
        this.stringData = stringData;
        this.floatData = floatData;
        this.intData = intData;
    }

    public String[] getStringData() {
        return this.stringData;
    }

    public float[] getFloatData() {
        return this.floatData;
    }

    public int getIntData() {
        return this.intData;
    }

    @Override
    public boolean equals(Object otherData) {
        if (otherData instanceof CleanData) {
            return Arrays.equals(((CleanData) otherData).floatData, this.floatData) &&
                   Arrays.equals(((CleanData) otherData).stringData, this.stringData) &&
                    ((CleanData) otherData).intData == this.intData;
        } else {
            return false;
        }
    }
}
