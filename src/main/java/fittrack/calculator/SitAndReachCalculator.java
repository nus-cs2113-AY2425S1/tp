package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SitAndReachCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> sitAndReachTable = new HashMap<>();
    private static final boolean SHOULD_SORT_DESCENDING = true;
    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    /**
     * Calculates the points based on the gender, age, and the sit and reach length.
     *
     * @param gender the gender of the individual
     * @param age the age of the individual
     * @param length the length achieved in the sit and reach test
     * @return the points corresponding to the length achieved
     */
    public static int calculatePoints(Gender gender, int age, int length) {
        return getPointsFromTable(sitAndReachTable, gender, age, length, IS_HIGHER_NUMBER_BETTER);
    }

    private static void initialiseMaleData() {
        int[][][] ageTables = {
                {{40, 5}, {36, 4}, {32, 3}, {28, 2}, {23, 1}},
                {{42, 5}, {38, 4}, {34, 3}, {30, 2}, {25, 1}},
                {{44, 5}, {40, 4}, {36, 3}, {31, 2}, {27, 1}},
                {{46, 5}, {42, 4}, {38, 3}, {33, 2}, {29, 1}},
                {{48, 5}, {44, 4}, {40, 3}, {36, 2}, {31, 1}},
                {{49, 5}, {45, 4}, {41, 3}, {36, 2}, {32, 1}},
                {{49, 5}, {45, 4}, {41, 3}, {37, 2}, {32, 1}},
                {{49, 5}, {45, 4}, {41, 3}, {37, 2}, {32, 1}},
                {{48, 5}, {44, 4}, {40, 3}, {36, 2}, {32, 1}}
        };
        addAllTables(sitAndReachTable, Gender.MALE, ageTables, SHOULD_SORT_DESCENDING);
    }

    private static void initialiseFemaleData() {
        int[][][] ageTables = {
                {{40, 5}, {37, 4}, {34, 3}, {30, 2}, {25, 1}},
                {{42, 5}, {38, 4}, {36, 3}, {32, 2}, {27, 1}},
                {{44, 5}, {41, 4}, {38, 3}, {33, 2}, {29, 1}},
                {{46, 5}, {43, 4}, {39, 3}, {35, 2}, {31, 1}},
                {{47, 5}, {44, 4}, {40, 3}, {36, 2}, {31, 1}},
                {{47, 5}, {44, 4}, {41, 3}, {36, 2}, {32, 1}},
                {{47, 5}, {44, 4}, {41, 3}, {36, 2}, {32, 1}},
                {{47, 5}, {44, 4}, {41, 3}, {36, 2}, {32, 1}},
                {{44, 5}, {41, 4}, {38, 3}, {35, 2}, {31, 1}}
        };
        addAllTables(sitAndReachTable, Gender.FEMALE, ageTables, SHOULD_SORT_DESCENDING);
    }
}
