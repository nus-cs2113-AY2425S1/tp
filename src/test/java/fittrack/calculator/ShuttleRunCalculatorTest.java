package fittrack.Calculator;

import fittrack.calculator.ShuttleRunCalculator;
import fittrack.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShuttleRunCalculatorTest {

    @Test
    public void testMaleAge13(){
        assertEquals(5, ShuttleRunCalculator.calculatePoints(Gender.MALE, 13, 102));
        assertEquals(4, ShuttleRunCalculator.calculatePoints(Gender.MALE, 13, 105));
        assertEquals(3, ShuttleRunCalculator.calculatePoints(Gender.MALE, 13, 108));
        assertEquals(2, ShuttleRunCalculator.calculatePoints(Gender.MALE, 13, 112));
        assertEquals(1, ShuttleRunCalculator.calculatePoints(Gender.MALE, 13, 119));
        assertEquals(0, ShuttleRunCalculator.calculatePoints(Gender.MALE, 13, 120));
    }

    @Test
    public void testFemaleAge13(){
        assertEquals(5, ShuttleRunCalculator.calculatePoints(Gender.FEMALE, 13, 112));
        assertEquals(4, ShuttleRunCalculator.calculatePoints(Gender.FEMALE, 13, 113));
        assertEquals(3, ShuttleRunCalculator.calculatePoints(Gender.FEMALE, 13, 122));
        assertEquals(2, ShuttleRunCalculator.calculatePoints(Gender.FEMALE, 13, 125));
        assertEquals(1, ShuttleRunCalculator.calculatePoints(Gender.FEMALE, 13, 130));
        assertEquals(0, ShuttleRunCalculator.calculatePoints(Gender.FEMALE, 13, 140));
    }
}
