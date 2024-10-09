package seedu.duke.calculator;

import seedu.duke.Gender;
import seedu.duke.LookUpKey;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SitUpCalculator extends Calculator {

    private static final Map<LookUpKey, TreeMap<Integer, Integer>> sitUpPointsTable = new HashMap<>();

    static {
        TreeMap<Integer, Integer> maleAge12Table = new TreeMap<>(Comparator.reverseOrder());
        maleAge12Table.put(42, 5);
        maleAge12Table.put(36, 4);
        maleAge12Table.put(32, 3);
        maleAge12Table.put(27, 2);
        maleAge12Table.put(22, 1);
        sitUpPointsTable.put(new LookUpKey(Gender.MALE, 12), maleAge12Table);

        TreeMap<Integer, Integer> maleAge13Table = new TreeMap<>(Comparator.reverseOrder()); // Sorting in descending order
        maleAge13Table.put(43, 5);
        maleAge13Table.put(38, 4);
        maleAge13Table.put(34, 3);
        maleAge13Table.put(29, 2);
        maleAge13Table.put(25, 1);
        sitUpPointsTable.put(new LookUpKey(Gender.MALE, 13), maleAge13Table);
    }

    public static int calculatePoints(Gender gender, int age, int reps) {
        return getPointsFromTable(sitUpPointsTable, gender, age, reps);
    }

}
