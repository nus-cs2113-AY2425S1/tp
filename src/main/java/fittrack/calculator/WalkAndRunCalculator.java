package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WalkAndRunCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> walkAndRunTable = new HashMap<>();
    private static final boolean reverseOrder = false;

    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    public static int calculatePoints(Gender gender, int age, int reps) {
        return getPointsFromTable(walkAndRunTable, gender, age, reps, true);
    }

    // Shuttle Run time (in hundredths of seconds)
    private static void initialiseMaleData() {
        // Age data arrays
        int[][][] ageTables = {
                {{720, 5}, {790, 4}, {860, 3}, {930, 2}, {1010, 1}}, // Age 12
                {{690, 5}, {750, 4}, {820, 3}, {890, 2}, {960, 1}}, // Age 13
                {{660, 5}, {720, 4}, {780, 3}, {850, 2}, {920, 1}}, // Age 14
                {{640, 5}, {700, 4}, {760, 3}, {820, 2}, {880, 1}}, // Age 15
                {{630, 5}, {690, 4}, {740, 3}, {800, 2}, {850, 1}}, // Age 16
                {{620, 5}, {670, 4}, {720, 3}, {770, 2}, {820, 1}}, // Age 17
                {{620, 5}, {670, 4}, {710, 3}, {760, 2}, {810, 1}}, // Age 18
                {{620, 5}, {660, 4}, {700, 3}, {750, 2}, {800, 1}}, // Age 19
                {{620, 5}, {660, 4}, {700, 3}, {740, 2}, {780, 1}} // Age 20-24
        };
        addAllTables(walkAndRunTable, Gender.MALE, ageTables, reverseOrder);
    }

    private static void initialiseFemaleData() {
        // Age data arrays
        int[][][] ageTables = {
                {{880, 5}, {940, 4}, {1000, 3}, {1060, 2}, {1120, 1}}, // Age 12
                {{870, 5}, {930, 4}, {990, 3}, {1050, 2}, {1110, 1}}, // Age 13
                {{860, 5}, {920, 4}, {980, 3}, {1040, 2}, {1100, 1}}, // Age 14
                {{850, 5}, {910, 4}, {970, 3}, {1030, 2}, {1090, 1}}, // Age 15
                {{840, 5}, {900, 4}, {960, 3}, {1020, 2}, {1070, 1}}, // Age 16
                {{840, 5}, {890, 4}, {950, 3}, {1000, 2}, {1050, 1}}, // Age 17
                {{840, 5}, {890, 4}, {940, 3}, {990, 2}, {1040, 1}}, // Age 18
                {{860, 5}, {890, 4}, {930, 3}, {980, 2}, {1030, 1}}, // Age 19
                {{900, 5}, {930, 4}, {960, 3}, {990, 2}, {1020, 1}} // Age 20-24
        };
        addAllTables(walkAndRunTable, Gender.FEMALE, ageTables, reverseOrder);
    }
}

