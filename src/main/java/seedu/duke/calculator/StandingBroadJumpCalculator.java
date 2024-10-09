package seedu.duke.calculator;

import seedu.duke.Gender;
import seedu.duke.LookUpKey;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StandingBroadJumpCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> broadJumpPointsTable = new HashMap<>();

    static {
        TreeMap<Integer, Integer> maleAge12Table = new TreeMap<>(Comparator.reverseOrder());
        maleAge12Table.put(203, 5);
        maleAge12Table.put(189, 4);
        maleAge12Table.put(176, 3);
        maleAge12Table.put(163, 2);
        maleAge12Table.put(150, 1);
        broadJumpPointsTable.put(new LookUpKey(Gender.MALE, 12), maleAge12Table);

        TreeMap<Integer, Integer> maleAge13Table = new TreeMap<>(Comparator.reverseOrder());
        maleAge13Table.put(215, 5);
        maleAge13Table.put(202, 4);
        maleAge13Table.put(189, 3);
        maleAge13Table.put(176, 2);
        maleAge13Table.put(164, 1);
        broadJumpPointsTable.put(new LookUpKey(Gender.MALE, 13), maleAge13Table);

    }

    public static int calculatePoints(Gender gender, int age, int distance) {
        return getPointsFromTable(broadJumpPointsTable, gender, age, distance);
    }
}