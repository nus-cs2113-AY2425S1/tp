package seedu.duke.calculator;

import seedu.duke.Gender;
import seedu.duke.LookUpKey;

import java.util.Map;
import java.util.TreeMap;

public abstract class Calculator {

    // Helper method to find the points based on performance in the given table
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
}
