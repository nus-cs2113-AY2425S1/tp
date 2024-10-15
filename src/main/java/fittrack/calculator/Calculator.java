package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public abstract class Calculator {
    // Utility method to find the points based on performance in the given table.
    protected static int getPointsFromTable(Map<LookUpKey, TreeMap<Integer, Integer>> pointsTable,
            Gender gender, int age, int performance) {
        LookUpKey key = new LookUpKey(gender, age);
        TreeMap<Integer, Integer> subTable = pointsTable.get(key);

        for (Map.Entry<Integer, Integer> entry : subTable.entrySet()) {
            if (performance >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 0;
    }

    // Utility method to add age sub-table for a specific gender
    protected static void addAgeSubTable(Map<LookUpKey, TreeMap<Integer, Integer>> pointsTable,
                                         Gender gender, int age, int[][] points) {
        TreeMap<Integer, Integer> ageSubTable = new TreeMap<>(Comparator.reverseOrder());
        for (int[] point : points) {
            ageSubTable.put(point[0], point[1]);
        }
        pointsTable.put(new LookUpKey(gender, age), ageSubTable);
    }
}
