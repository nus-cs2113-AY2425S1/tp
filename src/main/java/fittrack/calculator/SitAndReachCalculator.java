package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SitAndReachCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> sitAndReachTable = new HashMap<>();

    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    public static int calculatePoints(Gender gender, int age, int length) {
        return getPointsFromTable(sitAndReachTable, gender, age, length, false);
    }

    private static void initialiseMaleData() {
        int[][] tableForAge12 = {{40, 5}, {36, 4}, {32, 3}, {28, 2}, {23, 1}};
        int[][] tableForAge13 = {{42, 5}, {38, 4}, {34, 3}, {30, 2}, {25, 1}};
        int[][] tableForAge14 = {{44, 5}, {40, 4}, {36, 3}, {31, 2}, {27, 1}};
        int[][] tableForAge15 = {{46, 5}, {42, 4}, {38, 3}, {33, 2}, {29, 1}};
        int[][] tableForAge16 = {{48, 5}, {44, 4}, {40, 3}, {36, 2}, {31, 1}};
        int[][] tableForAge17 = {{49, 5}, {45, 4}, {41, 3}, {36, 2}, {32, 1}};
        int[][] tableForAge18 = {{49, 5}, {45, 4}, {41, 3}, {37, 2}, {32, 1}};
        int[][] tableForAge19 = {{49, 5}, {45, 4}, {41, 3}, {37, 2}, {32, 1}};
        int[][] tableForAge20to24 = {{48, 5}, {44, 4}, {40, 3}, {36, 2}, {32, 1}};

        addAgeSubTable(sitAndReachTable, Gender.MALE, 12, tableForAge12);
        addAgeSubTable(sitAndReachTable, Gender.MALE, 13, tableForAge13);
        addAgeSubTable(sitAndReachTable, Gender.MALE, 14, tableForAge14);
        addAgeSubTable(sitAndReachTable, Gender.MALE, 15, tableForAge15);
        addAgeSubTable(sitAndReachTable, Gender.MALE, 16, tableForAge16);
        addAgeSubTable(sitAndReachTable, Gender.MALE, 17, tableForAge17);
        addAgeSubTable(sitAndReachTable, Gender.MALE, 18, tableForAge18);
        addAgeSubTable(sitAndReachTable, Gender.MALE, 19, tableForAge19);

        for (int age = 20; age <= 24; age++) {
            addAgeSubTable(sitAndReachTable, Gender.MALE, age, tableForAge20to24);
        }
    }

    private static void initialiseFemaleData() {
        int[][] tableForAge12 = {{40, 5}, {37, 4}, {34, 3}, {30, 2}, {25, 1}};
        int[][] tableForAge13 = {{42, 5}, {38, 4}, {36, 3}, {32, 2}, {27, 1}};
        int[][] tableForAge14 = {{44, 5}, {41, 4}, {38, 3}, {33, 2}, {29, 1}};
        int[][] tableForAge15 = {{46, 5}, {43, 4}, {39, 3}, {35, 2}, {31, 1}};
        int[][] tableForAge16 = {{47, 5}, {44, 4}, {40, 3}, {36, 2}, {31, 1}};
        int[][] tableForAge17 = {{47, 5}, {44, 4}, {41, 3}, {36, 2}, {32, 1}};
        int[][] tableForAge18 = {{47, 5}, {44, 4}, {41, 3}, {36, 2}, {32, 1}};
        int[][] tableForAge19 = {{47, 5}, {44, 4}, {41, 3}, {36, 2}, {32, 1}};
        int[][] tableForAge20to24 = {{44, 5}, {41, 4}, {38, 3}, {35, 2}, {31, 1}};

        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 12, tableForAge12);
        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 13, tableForAge13);
        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 14, tableForAge14);
        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 15, tableForAge15);
        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 16, tableForAge16);
        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 17, tableForAge17);
        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 18, tableForAge18);
        addAgeSubTable(sitAndReachTable, Gender.FEMALE, 19, tableForAge19);

        for (int age = 20; age <= 24; age++) {
            addAgeSubTable(sitAndReachTable, Gender.FEMALE, age, tableForAge20to24);
        }
    }
}
