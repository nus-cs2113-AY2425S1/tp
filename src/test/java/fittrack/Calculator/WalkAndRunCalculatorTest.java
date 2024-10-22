package fittrack.Calculator;

import fittrack.calculator.WalkAndRunCalculator;
import fittrack.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalkAndRunCalculatorTest {

    @Test
    public void testMaleAge13(){
        assertEquals(5, WalkAndRunCalculator.calculatePoints(Gender.MALE, 13, 690));
        assertEquals(4, WalkAndRunCalculator.calculatePoints(Gender.MALE, 13, 691));
        assertEquals(3, WalkAndRunCalculator.calculatePoints(Gender.MALE, 13, 780));
        assertEquals(2, WalkAndRunCalculator.calculatePoints(Gender.MALE, 13, 890));
        assertEquals(1, WalkAndRunCalculator.calculatePoints(Gender.MALE, 13, 891));
        assertEquals(0, WalkAndRunCalculator.calculatePoints(Gender.MALE, 13, 1000));
    }

    @Test
    public void testFemaleAge13(){
        assertEquals(5, WalkAndRunCalculator.calculatePoints(Gender.FEMALE, 13, 870));
        assertEquals(4, WalkAndRunCalculator.calculatePoints(Gender.FEMALE, 13, 871));
        assertEquals(3, WalkAndRunCalculator.calculatePoints(Gender.FEMALE, 13, 931));
        assertEquals(2, WalkAndRunCalculator.calculatePoints(Gender.FEMALE, 13, 1020));
        assertEquals(1, WalkAndRunCalculator.calculatePoints(Gender.FEMALE, 13, 1109));
        assertEquals(0, WalkAndRunCalculator.calculatePoints(Gender.FEMALE, 13, 1200));
    }
}
