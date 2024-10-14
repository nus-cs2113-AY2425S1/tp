package fittrack.calculator;

import fittrack.enums.Gender;
import fittrack.lookupkey.LookUpKey;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StandingBroadJumpCalculator extends Calculator {
    private static final Map<LookUpKey, TreeMap<Integer, Integer>> broadJumpTable = new HashMap<>();

    static {
        initialiseMaleData();
        initialiseFemaleData();
    }

    public static int calculatePoints(Gender gender, int age, int distance) {
        return getPointsFromTable(broadJumpTable, gender, age, distance);
    }

    protected static void initialiseMaleData() {
        int[][] tableForAge12 = {{203, 5}, {189, 4}, {176, 3}, {163, 2}, {150, 1}};
        int[][] tableForAge13 = {{215, 5}, {202, 4}, {188, 3}, {176, 2}, {164, 1}};
        int[][] tableForAge14 = {{226, 5}, {216, 4}, {206, 3}, {196, 2}, {186, 1}};
        int[][] tableForAge15 = {{238, 5}, {228, 4}, {218, 3}, {208, 2}, {198, 1}};
        int[][] tableForAge16 = {{246, 5}, {236, 4}, {226, 3}, {216, 2}, {206, 1}};
        int[][] tableForAge17 = {{250, 5}, {240, 4}, {230, 3}, {220, 2}, {210, 1}};
        int[][] tableForAge18 = {{252, 5}, {242, 4}, {232, 3}, {222, 2}, {212, 1}};
        int[][] tableForAge19 = {{252, 5}, {242, 4}, {232, 3}, {222, 2}, {212, 1}};
        int[][] tableForAge20to24 = {{243, 5}, {234, 4}, {225, 3}, {216, 2}, {207, 1}};

        addAgeSubTable(broadJumpTable, Gender.MALE, 12, tableForAge12);
        addAgeSubTable(broadJumpTable, Gender.MALE, 13, tableForAge13);
        addAgeSubTable(broadJumpTable, Gender.MALE, 14, tableForAge14);
        addAgeSubTable(broadJumpTable, Gender.MALE, 15, tableForAge15);
        addAgeSubTable(broadJumpTable, Gender.MALE, 16, tableForAge16);
        addAgeSubTable(broadJumpTable, Gender.MALE, 17, tableForAge17);
        addAgeSubTable(broadJumpTable, Gender.MALE, 18, tableForAge18);
        addAgeSubTable(broadJumpTable, Gender.MALE, 19, tableForAge19);

        for (int age = 20; age <= 24; age++) {
            addAgeSubTable(broadJumpTable, Gender.MALE, age, tableForAge20to24);
        }
    }

    protected static void initialiseFemaleData() {
        int[][] tableForAge12 = {{168, 5}, {159, 4}, {150, 3}, {141, 2}, {132, 1}};
        int[][] tableForAge13 = {{171, 5}, {162, 4}, {153, 3}, {144, 2}, {135, 1}};
        int[][] tableForAge14 = {{178, 5}, {169, 4}, {160, 3}, {151, 2}, {142, 1}};
        int[][] tableForAge15 = {{183, 5}, {174, 4}, {165, 3}, {156, 2}, {147, 1}};
        int[][] tableForAge16 = {{187, 5}, {178, 4}, {169, 3}, {160, 2}, {151, 1}};
        int[][] tableForAge17 = {{190, 5}, {181, 4}, {172, 3}, {163, 2}, {154, 1}};
        int[][] tableForAge18 = {{193, 5}, {183, 4}, {174, 3}, {165, 2}, {156, 1}};
        int[][] tableForAge19 =  {{196, 5}, {185, 4}, {174, 3}, {165, 2}, {156, 1}};
        int[][] tableForAge20to24 = {{198, 5}, {186, 4}, {174, 3}, {162, 2}, {150, 1}};

        addAgeSubTable(broadJumpTable, Gender.FEMALE, 12, tableForAge12);
        addAgeSubTable(broadJumpTable, Gender.FEMALE, 13, tableForAge13);
        addAgeSubTable(broadJumpTable, Gender.FEMALE, 14, tableForAge14);
        addAgeSubTable(broadJumpTable, Gender.FEMALE, 15, tableForAge15);
        addAgeSubTable(broadJumpTable, Gender.FEMALE, 16, tableForAge16);
        addAgeSubTable(broadJumpTable, Gender.FEMALE, 17, tableForAge17);
        addAgeSubTable(broadJumpTable, Gender.FEMALE, 18, tableForAge18);
        addAgeSubTable(broadJumpTable, Gender.FEMALE, 19, tableForAge19);

        for (int age = 20; age <= 24; age++) {
            addAgeSubTable(broadJumpTable, Gender.FEMALE, age, tableForAge20to24);
        }
    }
}