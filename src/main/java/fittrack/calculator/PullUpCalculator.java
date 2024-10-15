package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PullUpCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> pullUpTable = new HashMap<>();

    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    public static int calculatePoints(Gender gender, int age, int reps) {
        return getPointsFromTable(pullUpTable, gender, age, reps);
    }

    private static void initialiseMaleData() {
        int[][] tableForAge12 = {{25, 5}, {21, 4}, {16, 3}, {11, 2}, {5, 1}};
        int[][] tableForAge13 = {{26, 5}, {22, 4}, {17, 3}, {12, 2}, {7, 1}};
        int[][] tableForAge14 = {{27, 5}, {23, 4}, {18, 3}, {13, 2}, {8, 1}};
        int[][] tableForAge15 = {{8, 5}, {6, 4}, {5, 3}, {3, 2}, {1, 1}};
        int[][] tableForAge16 = {{9, 5}, {7, 4}, {5, 3}, {3, 2}, {1, 1}};
        int[][] tableForAge17 = {{10, 5}, {8, 4}, {6, 3}, {4, 2}, {2, 1}};
        int[][] tableForAge18to24 = {{11, 5}, {9, 4}, {7, 3}, {5, 2}, {3, 1}};

        addAgeSubTable(pullUpTable, Gender.MALE, 12, tableForAge12);
        addAgeSubTable(pullUpTable, Gender.MALE, 13, tableForAge13);
        addAgeSubTable(pullUpTable, Gender.MALE, 14, tableForAge14);
        addAgeSubTable(pullUpTable, Gender.MALE, 15, tableForAge15);
        addAgeSubTable(pullUpTable, Gender.MALE, 16, tableForAge16);
        addAgeSubTable(pullUpTable, Gender.MALE, 17, tableForAge17);

        for (int age = 18; age <= 24; age++) {
            addAgeSubTable(pullUpTable, Gender.MALE, age, tableForAge18to24);
        }
    }

    private static void initialiseFemaleData() {
        int[][] tableForAge12 = {{16, 5}, {13, 4}, {10, 3}, {7, 2}, {3, 1}};
        int[][] tableForAge13 = {{17, 5}, {13, 4}, {10, 3}, {7, 2}, {3, 1}};
        int[][] tableForAge14to15 = {{17, 5}, {14, 4}, {10, 3}, {7, 2}, {3, 1}};
        int[][] tableForAge16to17 = {{18, 5}, {14, 4}, {11, 3}, {7, 2}, {3, 1}};
        int[][] tableForAge18 = {{18, 5}, {15, 4}, {11, 3}, {8, 2}, {4, 1}};
        int[][] tableForAge19 = {{18, 5}, {15, 4}, {11, 3}, {8, 2}, {5, 1}};
        int[][] tableForAge20to24 = {{18, 5}, {15, 4}, {11, 3}, {8, 2}, {5, 1}};

        addAgeSubTable(pullUpTable, Gender.FEMALE, 12, tableForAge12);
        addAgeSubTable(pullUpTable, Gender.FEMALE, 13, tableForAge13);

        for (int age = 14; age <= 15; age++) {
            addAgeSubTable(pullUpTable, Gender.FEMALE, age, tableForAge14to15);
        }

        for (int age = 16; age <= 17; age++) {
            addAgeSubTable(pullUpTable, Gender.FEMALE, age, tableForAge16to17);
        }

        addAgeSubTable(pullUpTable, Gender.FEMALE, 18, tableForAge18);
        addAgeSubTable(pullUpTable, Gender.FEMALE, 19, tableForAge19);

        for (int age = 20; age <= 24; age++) {
            addAgeSubTable(pullUpTable, Gender.FEMALE, age, tableForAge20to24);
        }
    }
}
