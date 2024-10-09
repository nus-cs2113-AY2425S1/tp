package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SitAndReachCalculator extends Calculator {

    private static final Map<LookUpKey, TreeMap<Integer, Integer>> sitAndReachPointsTable = new HashMap<>();

    static {
        TreeMap<Integer, Integer> maleAge12Table = new TreeMap<>(Comparator.reverseOrder());
        maleAge12Table.put(40, 5);
        maleAge12Table.put(36, 4);
        maleAge12Table.put(32, 3);
        maleAge12Table.put(28, 2);
        maleAge12Table.put(23, 1);
        sitAndReachPointsTable.put(new LookUpKey(Gender.MALE, 12), maleAge12Table);

        TreeMap<Integer, Integer> maleAge13Table = new TreeMap<>(Comparator.reverseOrder());
        maleAge13Table.put(40, 5);
        maleAge13Table.put(36, 4);
        maleAge13Table.put(32, 3);
        maleAge13Table.put(28, 2);
        maleAge13Table.put(23, 1);
        sitAndReachPointsTable.put(new LookUpKey(Gender.MALE, 13), maleAge13Table);
    }

    public static int calculatePoints(Gender gender, int age, int length) {
        return getPointsFromTable(sitAndReachPointsTable, gender, age, length);
    }
}