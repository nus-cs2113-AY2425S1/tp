package fittrack.healthprofile;

import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

public class FoodEntryTest {

    @Test
    void testFoodEntryConstructor() {
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        FoodEntry foodEntry = new FoodEntry("Apple", 95, dateTime);

        assertEquals("Apple", foodEntry.getFoodName());
        assertEquals(95, foodEntry.getCalories());
        assertEquals(dateTime, foodEntry.getTruncatedDateTime().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void testGetFormattedDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 11, 10, 15, 30);
        FoodEntry foodEntry = new FoodEntry("Apple", 95, dateTime);
        assertEquals("10/11/2024 15:30", foodEntry.getFormattedDateTime());
    }

    @Test
    void testToSaveString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 11, 10, 15, 30);
        FoodEntry foodEntry = new FoodEntry("Apple", 95, dateTime);
        assertEquals("Food|Apple|95|10/11/2024 15:30", foodEntry.toSaveString());
    }

    @Test
    void testFromSaveStringValid() {
        String saveString = "Food|Apple|95|10/11/2024 15:30";
        FoodEntry foodEntry = FoodEntry.fromSaveString(saveString);

        assertNotNull(foodEntry);
        assertEquals("Apple", foodEntry.getFoodName());
        assertEquals(95, foodEntry.getCalories());
    }

    @Test
    void testFromSaveStringInvalidFormat() {
      String invalidSaveString = "Invalid|Apple|95|10/11/2024 15:30";
      assertThrows(IllegalArgumentException.class, () -> {
        FoodEntry.fromSaveString(invalidSaveString);
     });
    }
}

class WaterEntryTest {

    @Test
    void testWaterEntryConstructor() {
        LocalDateTime dateTime = LocalDateTime.now();
        WaterEntry waterEntry = new WaterEntry(250, dateTime);

        assertEquals(250, waterEntry.getAmount());
        assertEquals(dateTime, waterEntry.getDateTime());
    }

    @Test
    void testGetFormattedDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 11, 10, 15, 30);
        WaterEntry waterEntry = new WaterEntry(250, dateTime);
        assertEquals("10/11/2024 15:30", waterEntry.getFormattedDateTime());
    }

    @Test
    void testToSaveString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 11, 10, 15, 30);
        WaterEntry waterEntry = new WaterEntry(250, dateTime);
        assertEquals("Water|250|10/11/2024 15:30", waterEntry.toSaveString());
    }

    @Test
    void testFromSaveStringValid() {
        String saveString = "Water|250|10/11/2024 15:30";
        WaterEntry waterEntry = WaterEntry.fromSaveString(saveString);

        assertNotNull(waterEntry);
        assertEquals(250, waterEntry.getAmount());
    }

    @Test
    void testFromSaveStringInvalidFormat() {
        String invalidSaveString = "Invalid|250|10/11/2024 15:30";
        assertThrows(IllegalArgumentException.class, () -> {
          WaterEntry.fromSaveString(invalidSaveString);
        });
    }
}

class FoodWaterIntakeTest {

    @Test
    void testAddFood() {
        FoodWaterIntake intake = new FoodWaterIntake();
        FoodEntry foodEntry = new FoodEntry("Apple", 95, LocalDateTime.now());

        intake.addFood(foodEntry);
        assertEquals(1, intake.getFoodList().size());
        assertEquals(foodEntry, intake.getFoodList().get(0));
    }

    @Test
    void testDeleteFood() {
        FoodWaterIntake intake = new FoodWaterIntake();
        FoodEntry foodEntry = new FoodEntry("Apple", 95, LocalDateTime.now());
        intake.addFood(foodEntry);

        intake.deleteFood(0);
        assertTrue(intake.getFoodList().isEmpty());
    }

    @Test
    void testDeleteFoodInvalidIndex() {
        FoodWaterIntake intake = new FoodWaterIntake();
        intake.deleteFood(0); // Expect no exception, just a message
        assertTrue(intake.getFoodList().isEmpty());
    }

    @Test
    void testAddWater() {
        FoodWaterIntake intake = new FoodWaterIntake();
        WaterEntry waterEntry = new WaterEntry(250, LocalDateTime.now());

        intake.addWater(waterEntry);
        assertEquals(1, intake.getWaterList().size());
        assertEquals(waterEntry, intake.getWaterList().get(0));
    }

    @Test
    void testDeleteWater() {
        FoodWaterIntake intake = new FoodWaterIntake();
        WaterEntry waterEntry = new WaterEntry(250, LocalDateTime.now());
        intake.addWater(waterEntry);

        intake.deleteWater(0);
        assertTrue(intake.getWaterList().isEmpty());
    }

    @Test
    void testDeleteWaterInvalidIndex() {
        FoodWaterIntake intake = new FoodWaterIntake();
        intake.deleteWater(0); // Expect no exception, just a message
        assertTrue(intake.getWaterList().isEmpty());
    }
}
