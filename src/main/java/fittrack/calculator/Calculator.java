package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.exception.InvalidAgeException;
import fittrack.lookupkey.LookUpKey;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public abstract class Calculator {

    // Constants for age ranges
    protected static final int AGE_RANGE_LOWER_START = 12;
    protected static final int AGE_RANGE_LOWER_END = 19;
    protected static final int AGE_RANGE_UPPER_START = 20;
    protected static final int AGE_RANGE_UPPER_END = 24;
    
    protected static final boolean IS_HIGHER_NUMBER_BETTER = true;

    /**
     * Utility method to find points based on performance from a points table.
     *
     * @param pointsTable The table containing points information for different age and gender categories.
     * @param gender The gender of the person.
     * @param age The age of the person.
     * @param performance The performance metric to compare against the table.
     * @param isHigherNumBetter A boolean flag to check if higher values are better.
     * @return The points based on the performance in the table.
     */
    protected static int getPointsFromTable(Map<LookUpKey, TreeMap<Integer, Integer>> pointsTable,
            Gender gender, int age, int performance, boolean isHigherNumBetter) {

        try {
            LookUpKey key = new LookUpKey(gender, age);
            TreeMap<Integer, Integer> subTable = pointsTable.get(key);

            // Loop through the sub-table and match performance to the corresponding points
            for (Map.Entry<Integer, Integer> entry : subTable.entrySet()) {
                if ((!isHigherNumBetter && performance <= entry.getKey()) ||
                        (isHigherNumBetter && performance >= entry.getKey())) {
                    return entry.getValue();
                }
            }
            return 0; // return 0 point as performance is below the minimum standard
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
            return 0; // return 0 point as invalid age is inputted
        }
    }

    /**
     * Utility method to add an age-specific sub-table for a given gender to the points table.
     *
     * @param pointsTable The main points table to which the sub-table will be added.
     * @param gender The gender for which the sub-table is being added.
     * @param age The age for which the sub-table is being added.
     * @param points A 2D array containing performance-to-points mapping for the specific age.
     * @param reverseOrder Boolean flag to reverse the order of the table (for descending order).
     */
    protected static void addAgeSubTable(Map<LookUpKey, TreeMap<Integer, Integer>> pointsTable,
                                         Gender gender, int age, int[][] points, boolean reverseOrder) {
        assert age >= AGE_RANGE_LOWER_START && age <= AGE_RANGE_UPPER_END :
                "Age should be within 12 and 24 during table initialisation";

        // Initialize the sub-table with appropriate order based on reverseOrder flag
        TreeMap<Integer, Integer> ageSubTable;
        if (reverseOrder){
            ageSubTable = new TreeMap<>(Comparator.reverseOrder());
        } else{
            ageSubTable = new TreeMap<>();
        }

        for (int[] point : points) {
            assert point[0] >= 0 : "Performance metric cannot be negative";
            assert point[1] >= 0 : "Points should never be negative";
            ageSubTable.put(point[0], point[1]);
        }

        try {
            pointsTable.put(new LookUpKey(gender, age), ageSubTable);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to add all sub-tables for different ages and genders.
     *
     * @param exerciseTable The main points table for exercise performance.
     * @param gender The gender for which the tables are being added.
     * @param ageTables A 3D array containing the performance-to-points data for different age groups.
     * @param reverseOrder Flag to reverse the order of the tables (for descending order).
     */
    protected static void addAllTables(Map<LookUpKey, TreeMap<Integer, Integer>> exerciseTable,
                                       Gender gender, int[][][] ageTables, boolean reverseOrder) {
        // Add data for lower ages (12-19)
        for (int age = AGE_RANGE_LOWER_START; age <= AGE_RANGE_LOWER_END; age++) {
            addAgeSubTable(exerciseTable, gender, age, ageTables[age - AGE_RANGE_LOWER_START], reverseOrder);
        }

        // Add data for upper ages (20-24)
        for (int age = AGE_RANGE_UPPER_START; age <= AGE_RANGE_UPPER_END; age++) {
            addAgeSubTable(exerciseTable, gender, age, ageTables[ageTables.length - 1], reverseOrder);
        }
    }
}
