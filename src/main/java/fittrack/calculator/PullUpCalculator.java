package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PullUpCalculator extends Calculator {

    private static final Map<LookUpKey, TreeMap<Integer, Integer>> pullUpPointsTable = new HashMap<>();

    static {
        TreeMap<Integer, Integer> maleAge12Table = new TreeMap<>(Comparator.reverseOrder());
        maleAge12Table.put(25, 5);
        maleAge12Table.put(21, 4);
        maleAge12Table.put(16, 3);
        maleAge12Table.put(11, 2);
        maleAge12Table.put(5, 1);
        pullUpPointsTable.put(new LookUpKey(Gender.MALE, 12), maleAge12Table);

        TreeMap<Integer, Integer> maleAge13Table = new TreeMap<>(Comparator.reverseOrder());
        maleAge13Table.put(26, 5);
        maleAge13Table.put(22, 4);
        maleAge13Table.put(17, 3);
        maleAge13Table.put(12, 2);
        maleAge13Table.put(7, 1);
        pullUpPointsTable.put(new LookUpKey(Gender.MALE, 13), maleAge13Table);

    }

    public static int calculatePoints(Gender gender, int age, int reps) {
        return getPointsFromTable(pullUpPointsTable, gender, age, reps);
    }
}
