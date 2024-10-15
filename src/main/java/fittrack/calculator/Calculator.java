package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.exception.InvalidAgeException;
import fittrack.lookupkey.LookUpKey;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public abstract class Calculator {

    protected static final int AGE_RANGE_LOWER_START = 12;
    protected static final int AGE_RANGE_LOWER_END = 19;
    protected static final int AGE_RANGE_UPPER_START = 20;
    protected static final int AGE_RANGE_UPPER_END = 24;

    // Utility method to find the points based on performance in the given table.
    protected static int getPointsFromTable(Map<LookUpKey, TreeMap<Integer, Integer>> pointsTable,
            Gender gender, int age, int performance, boolean reverseComparison) {

        try {
            LookUpKey key = new LookUpKey(gender, age);
            TreeMap<Integer, Integer> subTable = pointsTable.get(key);

            for (Map.Entry<Integer, Integer> entry : subTable.entrySet()) {
                if (reverseComparison) {
                    if (performance > entry.getValue()) {
                        return entry.getKey();
                    } else {
                        if (performance >= entry.getKey()) {
                            return entry.getValue();
                        }
                    }
                }
            }
            return 0; // return 0 points as performance is below the minimum standard
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
            return 0; // return 0 points as invalid age is inputted
        }
    }

    // Utility method to add age sub-table for a specific gender
    protected static void addAgeSubTable(Map<LookUpKey, TreeMap<Integer, Integer>> pointsTable,
                                         Gender gender, int age, int[][] points) {
        TreeMap<Integer, Integer> ageSubTable = new TreeMap<>(Comparator.reverseOrder());
        for (int[] point : points) {
            ageSubTable.put(point[0], point[1]);
        }

        try {
            pointsTable.put(new LookUpKey(gender, age), ageSubTable);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());;
        }
    }

    protected static void addAllTables(Map<LookUpKey, TreeMap<Integer, Integer>> pullUpTable,
                                       Gender gender, int[][][] ageTables) {
        // Add data for lower ages
        for (int age = AGE_RANGE_LOWER_START; age <= AGE_RANGE_LOWER_END; age++) {
            addAgeSubTable(pullUpTable, gender, age, ageTables[age - AGE_RANGE_LOWER_START]);
        }

        // Add data for upper ages
        for (int age = AGE_RANGE_UPPER_START; age <= AGE_RANGE_UPPER_END; age++) {
            addAgeSubTable(pullUpTable, gender, age, ageTables[ageTables.length - 1]); // Age 20-24 table
        }
    }
}
