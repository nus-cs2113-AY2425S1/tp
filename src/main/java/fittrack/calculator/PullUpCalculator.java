package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PullUpCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> pullUpTable = new HashMap<>();
    private static final boolean SHOULD_SORT_DESCENDING = true;
    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    /**
     * Calculates the points based on gender, age, and number of pull-ups (reps).
     *
     * @param gender The gender of the individual (Male or Female).
     * @param age The age of the individual.
     * @param reps The number of pull-ups the individual performed.
     * @return The points for the given performance.
     */
    public static int calculatePoints(Gender gender, int age, int reps) {
        return getPointsFromTable(pullUpTable, gender, age, reps, IS_HIGHER_NUMBER_BETTER);
    }

    private static void initialiseMaleData() {
        int[][][] ageTables = {
                {{25, 5}, {21, 4}, {16, 3}, {11, 2}, {5, 1}},
                {{26, 5}, {22, 4}, {17, 3}, {12, 2}, {7, 1}},
                {{27, 5}, {23, 4}, {18, 3}, {13, 2}, {8, 1}},
                {{8, 5}, {6, 4}, {5, 3}, {3, 2}, {1, 1}},
                {{9, 5}, {7, 4}, {5, 3}, {3, 2}, {1, 1}},
                {{10, 5}, {8, 4}, {6, 3}, {4, 2}, {2, 1}},
                {{11, 5}, {9, 4}, {7, 3}, {5, 2}, {3, 1}},
                {{11, 5}, {9, 4}, {7, 3}, {5, 2}, {3, 1}},
                {{11, 5}, {9, 4}, {7, 3}, {5, 2}, {3, 1}}
        };
        addAllTables(pullUpTable, Gender.MALE, ageTables, SHOULD_SORT_DESCENDING);
    }

    private static void initialiseFemaleData() {
        int[][][] ageTables = {
                {{16, 5}, {13, 4}, {10, 3}, {7, 2}, {3, 1}},
                {{17, 5}, {13, 4}, {10, 3}, {7, 2}, {3, 1}},
                {{17, 5}, {14, 4}, {10, 3}, {7, 2}, {3, 1}},
                {{17, 5}, {14, 4}, {10, 3}, {7, 2}, {3, 1}},
                {{18, 5}, {14, 4}, {11, 3}, {7, 2}, {3, 1}},
                {{18, 5}, {14, 4}, {11, 3}, {7, 2}, {3, 1}},
                {{18, 5}, {15, 4}, {11, 3}, {8, 2}, {4, 1}},
                {{18, 5}, {15, 4}, {11, 3}, {8, 2}, {5, 1}},
                {{18, 5}, {15, 4}, {11, 3}, {8, 2}, {5, 1}}
        };
        addAllTables(pullUpTable, Gender.FEMALE, ageTables, SHOULD_SORT_DESCENDING);
    }
}
