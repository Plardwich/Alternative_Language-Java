package DataCleaning;

import java.util.Arrays;
import java.util.regex.*;

public class DataCleaner {
    public DataCleaner(){}
    public CleanData cleanData(String[] data) {
        if (data.length < 12) {
            String[] newData = Arrays.copyOf(data, 12);
            data = newData;
        }
        String[] stringData = new String[9];
        float[] floatData = new float[2];
        int launchInteger = -1;
        int stringIndex = 0;
        for (int i = 0; i < data.length; i++) {
            if (i == 2) {
                launchInteger = convertToYear(data[i]);
            } else if (i == 3) {
                stringData[stringIndex] = convertLaunchStatus(data[i]);
                stringIndex++;
            }
            else if (i == 5) {
                floatData[0] = convertToWeight(data[i]);
            }
            else if (i == 6) {
                stringData[stringIndex] = convertSim(data[i]);
                stringIndex++;
            }
            else if (i == 8) {
                floatData[1] = convertToSize(data[i]);
            }
            else {
                stringData[stringIndex] = !data[i].equals("-") ? data[i] : "";
                stringIndex++;
            }
        }
        return new CleanData(stringData, floatData, launchInteger);
    }

    private int convertToYear(String text) {
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            return -1;
        }
    }

    private float convertToWeight(String text) {
        Pattern patternOne = Pattern.compile("\\d+\\s*[g]{1}");
        Matcher matchesFullText = patternOne.matcher(text);
        if (matchesFullText.find()) {
            String foundText = matchesFullText.group();
            Pattern patternTwo = Pattern.compile("\\d+");
            Matcher matchesFoundText = patternTwo.matcher(foundText);
            return matchesFoundText.find() ? Float.parseFloat(matchesFoundText.group()) : -1;
        } else {
            return -1;
        }
    }
    private float convertToSize(String text) {
        Pattern patternOne = Pattern.compile("\\d+\\.?\\d*\\s*(inches)");
        Matcher matchesFullText = patternOne.matcher(text);
        if (matchesFullText.find()) {
            Pattern patternTwo = Pattern.compile("\\d+\\.?\\d*");
            Matcher matchesFoundText = patternTwo.matcher(matchesFullText.group());
            matchesFoundText.find();
            return Float.parseFloat(matchesFoundText.group());
        } else {
            return -1;
        }
    }

    private String convertLaunchStatus(String text) {
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        } else if (text.contains("Cancelled") || text.contains("Discontinued")) {
            return text;
        } else {
            return "";
        }
    }

    private String convertSim(String text) {
        if (!isNoOrYes(text)) {
            return text;
        }
        return "";
    }

    private Boolean isNoOrYes(String text) {
        String newText = text.toLowerCase();
        return newText.equals("no") || newText.equals("yes");
    }
}
