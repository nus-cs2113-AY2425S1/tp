package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SitUpCalculator extends Calculator {
    // Static map to hold the sit-up data for both genders and age groups
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> sitUpTable = new HashMap<>();

    // Flag to determine whether the values should be sorted in descending order
    private static final boolean SHOULD_SORT_DESCENDING = true;

    // Static block to initialize male and female data
    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    /**
     * Calculates the points based on gender, age, and the number of sit-ups (reps).
     *
     * @param gender the gender of the individual
     * @param age the age of the individual
     * @param reps the number of sit-ups performed
     * @return the points corresponding to the number of sit-ups performed
     */
    public static int calculatePoints(Gender gender, int age, int reps) {
        // Retrieves the points from the lookup table based on gender, age, and number of reps
        return getPointsFromTable(sitUpTable, gender, age, reps, IS_HIGHER_NUMBER_BETTER);
    }

    /**
     * Initializes the male data for the sit-up test, adding the data for each age group
     * with the corresponding number of sit-ups and performance points.
     */
    protected static void initialiseMaleData() {
        // 2D array of age-specific sit-up data for males
        int[][][] ageTables = {
                {{42, 5}, {36, 4}, {32, 3}, {27, 2}, {22, 1}},
                {{43, 5}, {38, 4}, {34, 3}, {29, 2}, {25, 1}},
                {{43, 5}, {40, 4}, {37, 3}, {33, 2}, {29, 1}},
                {{43, 5}, {40, 4}, {37, 3}, {34, 2}, {30, 1}},
                {{43, 5}, {40, 4}, {37, 3}, {34, 2}, {31, 1}},
                {{43, 5}, {40, 4}, {37, 3}, {34, 2}, {31, 1}},
                {{43, 5}, {40, 4}, {37, 3}, {34, 2}, {31, 1}},
                {{43, 5}, {40, 4}, {37, 3}, {34, 2}, {31, 1}},
                {{40, 5}, {37, 4}, {34, 3}, {31, 2}, {28, 1}}
        };
        // Adds the male data to the lookup table
        addAllTables(sitUpTable, Gender.MALE, ageTables, SHOULD_SORT_DESCENDING);
    }

    /**
     * Initializes the female data for the sit-up test, adding the data for each age group
     * with the corresponding number of sit-ups and performance points.
     */
    protected static void initialiseFemaleData() {
        // 2D array of age-specific sit-up data for females
        int[][][] ageTables = {
                {{30, 5}, {25, 4}, {21, 3}, {17, 2}, {13, 1}},
                {{31, 5}, {26, 4}, {22, 3}, {18, 2}, {14, 1}},
                {{31, 5}, {28, 4}, {24, 3}, {20, 2}, {16, 1}},
                {{31, 5}, {29, 4}, {25, 3}, {21, 2}, {17, 1}},
                {{31, 5}, {29, 4}, {26, 3}, {22, 2}, {18, 1}},
                {{31, 5}, {29, 4}, {27, 3}, {23, 2}, {19, 1}},
                {{31, 5}, {29, 4}, {27, 3}, {24, 2}, {20, 1}},
                {{31, 5}, {29, 4}, {27, 3}, {24, 2}, {21, 1}},
                {{29, 5}, {27, 4}, {25, 3}, {23, 2}, {21, 1}}
        };
        // Adds the female data to the lookup table
        addAllTables(sitUpTable, Gender.FEMALE, ageTables, SHOULD_SORT_DESCENDING);
    }
}
