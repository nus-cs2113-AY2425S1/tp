package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WalkAndRunCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> pullUpTable = new HashMap<>();

    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    public static int calculatePoints(Gender gender, int age, int reps) {
        reps *= 100;
        return getPointsFromTable(pullUpTable, gender, age, reps, true);
    }

    // Shuttle Run time (in hundredths of seconds)
    private static void initialiseMaleData() {
        // Age data arrays
        int[][][] ageTables = {
                {{601, 5}, {721, 4}, {790, 3}, {860, 2}, {930, 1}}, // Age 12
                {{691, 5}, {750, 4}, {820, 3}, {890, 2}, {1010, 1}}, // Age 13
                {{641, 5}, {753, 4}, {830, 3}, {890, 2}, {1005, 1}}, // Age 14
                {{621, 5}, {649, 4}, {719, 3}, {771, 2}, {840, 1}}, // Age 15
                {{621, 5}, {649, 4}, {719, 3}, {771, 2}, {840, 1}}, // Age 16
                {{621, 5}, {649, 4}, {719, 3}, {771, 2}, {840, 1}}, // Age 17
                {{621, 5}, {649, 4}, {719, 3}, {771, 2}, {840, 1}}, // Age 18
                {{621, 5}, {649, 4}, {719, 3}, {771, 2}, {840, 1}}, // Age 19
                {{661, 5}, {681, 4}, {700, 3}, {730, 2}, {800, 1}}  // Age 20-24
        };
        addAllTables(pullUpTable, Gender.FEMALE, ageTables);
    }

    private static void initialiseFemaleData() {
        // Age data arrays
        int[][][] ageTables = {
                {{841, 5}, {861, 4}, {941, 3}, {1001, 2}, {1051, 1}}, // 12
                {{861, 5}, {901, 4}, {961, 3}, {1021, 2}, {1081, 1}}, // 13
                {{841, 5}, {881, 4}, {941, 3}, {1001, 2}, {1061, 1}}, // 14
                {{801, 5}, {841, 4}, {901, 3}, {961, 2}, {1021, 1}}, // 15
                {{801, 5}, {841, 4}, {901, 3}, {961, 2}, {1021, 1}}, // 16
                {{801, 5}, {841, 4}, {901, 3}, {961, 2}, {1021, 1}}, // 17
                {{801, 5}, {841, 4}, {901, 3}, {961, 2}, {1021, 1}}, // 18
                {{801, 5}, {841, 4}, {901, 3}, {961, 2}, {1021, 1}}, // 19
                {{801, 5}, {841, 4}, {901, 3}, {961, 2}, {1021, 1}}  // 20-24
        };
        addAllTables(pullUpTable, Gender.FEMALE, ageTables);
    }
}

