package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SitUpCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> sitUpTable = new HashMap<>();

    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    public static int calculatePoints(Gender gender, int age, int reps) {
        return getPointsFromTable(sitUpTable, gender, age, reps);
    }

    protected static void initialiseMaleData() {
        int[][] tableForAge12 = {{42, 5}, {36, 4}, {32, 3}, {27, 2}, {22, 1}};
        int[][] tableForAge13 = {{43, 5}, {38, 4}, {34, 3}, {29, 2}, {25, 1}};
        int[][] tableForAge14 = {{43, 5}, {40, 4}, {37, 3}, {33, 2}, {29, 1}};
        int[][] tableForAge15 = {{43, 5}, {40, 4}, {37, 3}, {34, 2}, {30, 1}};
        int[][] tableForAge16to19 = {{43, 5}, {40, 4}, {37, 3}, {34, 2}, {31, 1}};
        int[][] tableForAge20to24 = {{40, 5}, {37, 4}, {34, 3}, {31, 2}, {28, 1}};

        addAgeSubTable(sitUpTable, Gender.MALE, 12, tableForAge12);
        addAgeSubTable(sitUpTable, Gender.MALE, 13, tableForAge13);
        addAgeSubTable(sitUpTable, Gender.MALE, 14, tableForAge14);
        addAgeSubTable(sitUpTable, Gender.MALE, 15, tableForAge15);

        for (int age = 16; age <= 19; age++) {
            addAgeSubTable(sitUpTable, Gender.MALE, age, tableForAge16to19);
        }

        for (int age = 20; age <= 24; age++) {
            addAgeSubTable(sitUpTable, Gender.MALE, age,tableForAge20to24);
        }
    }

    protected static void initialiseFemaleData() {
        int[][] tableForAge12 = {{30, 5}, {25, 4}, {21, 3}, {17, 2}, {13, 1}};
        int[][] tableForAge13 = {{31, 5}, {26, 4}, {22, 3}, {18, 2}, {14, 1}};
        int[][] tableForAge14 = {{31, 5}, {28, 4}, {24, 3}, {20, 2}, {16, 1}};
        int[][] tableForAge15 = {{31, 5}, {29, 4}, {25, 3}, {21, 2}, {17, 1}};
        int[][] tableForAge16 = {{31, 5}, {29, 4}, {26, 3}, {22, 2}, {18, 1}};
        int[][] tableForAge17 = {{31, 5}, {29, 4}, {27, 3}, {23, 2}, {19, 1}};
        int[][] tableForAge18 = {{31, 5}, {29, 4}, {27, 3}, {24, 2}, {20, 1}};
        int[][] tableForAge19 = {{31, 5}, {29, 4}, {27, 3}, {24, 2}, {21, 1}};
        int[][] tableForAge20to24 = {{29, 5}, {27, 4}, {25, 3}, {23, 2}, {21, 1}};

        addAgeSubTable(sitUpTable, Gender.FEMALE, 12, tableForAge12);
        addAgeSubTable(sitUpTable, Gender.FEMALE, 13, tableForAge13);
        addAgeSubTable(sitUpTable, Gender.FEMALE, 14, tableForAge14);
        addAgeSubTable(sitUpTable, Gender.FEMALE, 15, tableForAge15);
        addAgeSubTable(sitUpTable, Gender.FEMALE, 16, tableForAge16);
        addAgeSubTable(sitUpTable, Gender.FEMALE, 17, tableForAge17);
        addAgeSubTable(sitUpTable, Gender.FEMALE, 18, tableForAge18);
        addAgeSubTable(sitUpTable, Gender.FEMALE, 19, tableForAge19);

        for (int age = 20; age <= 24; age++) {
            addAgeSubTable(sitUpTable, Gender.FEMALE, age, tableForAge20to24);
        }
    }
}