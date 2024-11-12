package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StandingBroadJumpCalculator extends Calculator {
    // Static map to hold the broad jump data for both genders and age groups
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> broadJumpTable = new HashMap<>();

    // Flag to determine whether the values should be sorted in descending order
    private static final boolean SHOULD_SORT_DESCENDING = true;

    // Static block to initialize male and female data
    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    /**
     * Calculates the points based on gender, age, and distance jumped (in centimeters).
     *
     * @param gender the gender of the individual
     * @param age the age of the individual
     * @param distance the distance jumped (in centimeters)
     * @return the points corresponding to the distance jumped
     */
    public static int calculatePoints(Gender gender, int age, int distance) {
        // Retrieves the points from the lookup table based on gender, age, and distance jumped
        return getPointsFromTable(broadJumpTable, gender, age, distance, IS_HIGHER_NUMBER_BETTER);
    }

    /**
     * Initializes the male data for the standing broad jump test, adding the data for each age group
     * with the corresponding distance and performance points.
     */
    protected static void initialiseMaleData() {
        int[][][] ageTables = {
                {{203, 5}, {189, 4}, {176, 3}, {163, 2}, {150, 1}},
                {{215, 5}, {202, 4}, {189, 3}, {176, 2}, {164, 1}},
                {{226, 5}, {216, 4}, {206, 3}, {196, 2}, {186, 1}},
                {{238, 5}, {228, 4}, {218, 3}, {208, 2}, {198, 1}},
                {{246, 5}, {236, 4}, {226, 3}, {216, 2}, {206, 1}},
                {{250, 5}, {240, 4}, {230, 3}, {220, 2}, {210, 1}},
                {{252, 5}, {242, 4}, {232, 3}, {222, 2}, {212, 1}},
                {{252, 5}, {242, 4}, {232, 3}, {222, 2}, {212, 1}},
                {{243, 5}, {234, 4}, {225, 3}, {216, 2}, {207, 1}}
        };
        addAllTables(broadJumpTable, Gender.MALE, ageTables, SHOULD_SORT_DESCENDING);
    }

    protected static void initialiseFemaleData() {

        int[][][] ageTables = {
                {{168, 5}, {159, 4}, {150, 3}, {141, 2}, {132, 1}},
                {{171, 5}, {162, 4}, {153, 3}, {144, 2}, {135, 1}},
                {{178, 5}, {169, 4}, {160, 3}, {151, 2}, {142, 1}},
                {{183, 5}, {174, 4}, {165, 3}, {156, 2}, {147, 1}},
                {{187, 5}, {178, 4}, {169, 3}, {160, 2}, {151, 1}},
                {{190, 5}, {181, 4}, {172, 3}, {163, 2}, {154, 1}},
                {{193, 5}, {183, 4}, {174, 3}, {165, 2}, {156, 1}},
                {{196, 5}, {185, 4}, {174, 3}, {165, 2}, {156, 1}},
                {{198, 5}, {186, 4}, {174, 3}, {162, 2}, {150, 1}}
        };
        addAllTables(broadJumpTable, Gender.FEMALE, ageTables, SHOULD_SORT_DESCENDING);
    }
}
