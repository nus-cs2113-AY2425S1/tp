package water;

import exceptions.WaterExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WaterTest {

    private Water water;

    @BeforeEach
    public void setUp() {
        water = new Water();
    }

    @Test
    public void testAddWaterHappyPath() {
        water.addWater(2.5f);

        ArrayList<Float> expectedList = new ArrayList<>();
        expectedList.add(2.5f);

        assertEquals(expectedList, water.getWaterList(), "Water list should contain the added amount.");
    }

    @Test
    public void testAddWaterEdgeCaseNegativeWaterAmount() {
        assertThrows(AssertionError.class, () -> water.addWater(-1.0f),
                "Adding negative water should throw AssertionError.");
    }

    @Test
    public void testIsEmptyInitiallyEmpty() {
        assertTrue(water.isEmpty(), "New Water instance should be empty.");
    }

    @Test
    public void testIsEmptyAfterAddingWater() {
        water.addWater(1.0f);
        assertFalse(water.isEmpty(), "Water instance should not be empty after adding water.");
    }

    @Test
    public void testDeleteWaterHappyPath() {
        water.addWater(3.0f);
        float deletedWater = water.deleteWater(0);

        assertEquals(3.0f, deletedWater, "Deleted water amount should match the amount added.");
        assertTrue(water.isEmpty(), "Water list should be empty after deleting the only entry.");
    }

    @Test
    public void testDeleteWaterEdgeCaseInvalidIndex() {
        // Update to expect IndexOutOfBoundsBuffBuddyException instead of IndexOutOfBoundsException
        assertThrows(WaterExceptions.class, () -> water.deleteWater(0),
                "Deleting from an empty list should throw WaterExceptions.");
    }

    @Test
    public void testGetWaterList() {
        water.addWater(1.5f);
        water.addWater(2.5f);

        ArrayList<Float> expectedList = new ArrayList<>();
        expectedList.add(1.5f);
        expectedList.add(2.5f);

        assertEquals(expectedList, water.getWaterList(), "Water list should contain all added amounts.");
    }

    @Test
    public void testToStringEmptyList() {
        // Test string output for an empty water list
        assertEquals("No record.", water.toString(),
                "Empty water list should return 'No record.'");
    }

    @Test
    public void testToStringWithEntries() {
        // Add entries and check the string representation
        water.addWater(1.0f);
        water.addWater(2.0f);

        String expectedOutput = "1: 1.0\n2: 2.0";
        assertEquals(expectedOutput, water.toString(),
                "String representation should match the format of indexed entries.");
    }
}

