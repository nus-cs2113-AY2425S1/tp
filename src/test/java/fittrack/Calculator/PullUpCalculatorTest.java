package fittrack.Calculator;

import fittrack.calculator.PullUpCalculator;
import fittrack.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PullUpCalculatorTest {
    @Test
    public void testMaleAge12() {
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 12, 30),
                "Expected 5 points for 30 reps for Male, age 12.");
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 12, 25),
                "Expected 5 points for 25 reps for Male, age 12.");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 12, 24),
                "Expected 4 points for 24 reps for Male, age 12");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 12, 21),
                "Expected 4 points for 21 reps for Male, age 12.");
        assertEquals(0, PullUpCalculator.calculatePoints(Gender.MALE, 12, 0),
               "Expected 0 points for 0 reps for Male, age 12.");
    }

    @Test
    public void testMaleAge13() {
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 13, 30),
                "Expected 5 points for 30 reps for Male, age 13.");
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 13, 26),
                "Expected 5 points for 26 reps for Male, age 13.");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 13, 25),
                "Expected 4 points for 25 reps for Male, age 13");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 13, 22),
                "Expected 4 points for 22 reps for Male, age 13.");
        assertEquals(0, PullUpCalculator.calculatePoints(Gender.MALE, 13, 0),
                "Expected 0 points for 0 reps for Male, age 13.");
    }

    @Test
    public void testMaleAge14() {
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 14, 30),
                "Expected 5 points for 30 reps for Male, age 14.");
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 14, 27),
                "Expected 5 points for 27 reps for Male, age 14.");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 14, 26),
                "Expected 4 points for 26 reps for Male, age 14");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 14, 23),
                "Expected 4 points for 23 reps for Male, age 14.");
        assertEquals(0, PullUpCalculator.calculatePoints(Gender.MALE, 14, 0),
                "Expected 0 points for 0 reps for Male, age 14.");
    }

    @Test
    public void testMaleAge15() {
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 15, 15),
                "Expected 5 points for 15 reps for Male, age 15.");
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 15, 8),
                "Expected 5 points for 8 reps for Male, age 15.");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 15, 7),
                "Expected 4 points for 7 reps for Male, age 15");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 15, 6),
                "Expected 4 points for 6 reps for Male, age 15.");
        assertEquals(0, PullUpCalculator.calculatePoints(Gender.MALE, 15, 0),
                "Expected 0 points for 0 reps for Male, age 15.");
    }

    @Test
    public void testMaleAge16() {
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 16, 15),
                "Expected 5 points for 15 reps for Male, age 16.");
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 16, 9),
                "Expected 5 points for 9 reps for Male, age 16.");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 16, 8),
                "Expected 4 points for 8 reps for Male, age 16");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 16, 7),
                "Expected 4 points for 7 reps for Male, age 16.");
        assertEquals(0, PullUpCalculator.calculatePoints(Gender.MALE, 16, 0),
                "Expected 0 points for 0 reps for Male, age 16.");
    }

    @Test
    public void testMaleAge17() {
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 17, 15),
                "Expected 5 points for 15 reps for Male, age 16.");
        assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, 17, 10),
                "Expected 5 points for 10 reps for Male, age 16.");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 17, 9),
                "Expected 4 points for 9 reps for Male, age 16");
        assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, 17, 8),
                "Expected 4 points for 8 reps for Male, age 16.");
        assertEquals(0, PullUpCalculator.calculatePoints(Gender.MALE, 17, 0),
                "Expected 0 points for 0 reps for Male, age 16.");
    }

    @Test
    public void testMaleAge18to24() {
        for (int age = 18; age <= 24; age += 1) {
            assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, age, 15),
                    "Expected 5 points for 15 reps for Male, age" + age + ".");
            assertEquals(5, PullUpCalculator.calculatePoints(Gender.MALE, age, 11),
                    "Expected 5 points for 11 reps for Male, age" + age + ".");
            assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, age, 10),
                    "Expected 4 points for 10 reps for Male, age" + age + ".");
            assertEquals(4, PullUpCalculator.calculatePoints(Gender.MALE, age, 9),
                    "Expected 4 points for 9 reps for Male, age" + age + ".");
            assertEquals(0, PullUpCalculator.calculatePoints(Gender.MALE, age, 0),
                    "Expected 0 points for 0 reps for Male, age" + age + ".");
        }
    }
}