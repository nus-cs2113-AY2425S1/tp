package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ShuttleRunCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> shuttleRunTable = new HashMap<>();
    private static final boolean reverseOrder = false;

    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    public static int calculatePoints(Gender gender, int age, int reps) {
        assert age >= AGE_RANGE_LOWER_END && age <= AGE_RANGE_UPPER_END;
        return getPointsFromTable(shuttleRunTable, gender, age, reps, true);
    }

    // Shuttle Run time (in hundredths of seconds)
    private static void initialiseMaleData() {
        // Age data arrays
        int[][][] ageTables = {
                {{103, 5}, {109, 4}, {113, 3}, {117, 2}, {122, 1}}, // Age 12
                {{102, 5}, {107, 4}, {111, 3}, {115, 2}, {119, 1}}, // Age 13
                {{101, 5}, {104, 4}, {108, 3}, {112, 2}, {116, 1}}, // Age 14
                {{101, 5}, {103, 4}, {105, 3}, {109, 2}, {113, 1}}, // Age 15
                {{101, 5}, {103, 4}, {105, 3}, {107, 2}, {111, 1}}, // Age 16
                {{101, 5}, {103, 4}, {105, 3}, {107, 2}, {109, 1}}, // Age 17
                {{101, 5}, {103, 4}, {105, 3}, {107, 2}, {109, 1}}, // Age 18
                {{101, 5}, {103, 4}, {105, 3}, {107, 2}, {109, 1}}, // Age 19
                {{103, 5}, {105, 4}, {107, 3}, {109, 2}, {111, 1}}  // Age 20-24
        };
        addAllTables(shuttleRunTable, Gender.MALE, ageTables, reverseOrder);
    }

    private static void initialiseFemaleData() {

        // Age data arrays
        int[][][] ageTables = {
                {{114, 5}, {119, 4}, {123, 3}, {127, 2}, {132, 1}}, // Age 12
                {{112, 5}, {117, 4}, {122, 3}, {127, 2}, {132, 1}}, // Age 13
                {{114, 5}, {118, 4}, {122, 3}, {126, 2}, {130, 1}}, // Age 14
                {{112, 5}, {116, 4}, {120, 3}, {124, 2}, {128, 1}}, // Age 15
                {{112, 5}, {115, 4}, {118, 3}, {122, 2}, {126, 1}}, // Age 16
                {{112, 5}, {115, 4}, {118, 3}, {121, 2}, {125, 1}}, // Age 17
                {{112, 5}, {115, 4}, {118, 3}, {121, 2}, {124, 1}}, // Age 18
                {{112, 5}, {115, 4}, {118, 3}, {121, 2}, {124, 1}}, // Age 19
                {{115, 5}, {118, 4}, {121, 3}, {124, 2}, {127, 1}} // Age 20-24
        };
        addAllTables(shuttleRunTable, Gender.FEMALE, ageTables, reverseOrder);
    }
}
