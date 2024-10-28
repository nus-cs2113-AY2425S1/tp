package history.DailyRecord;
import history.DailyRecord;
import meal.Meal;
import meal.MealList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.Exercise;
import water.Water;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class DailyRecordTest {
    private DailyRecord dailyRecord;
    private Day validDay;
    private MealList validMeal;
    private Meal meal1;
    private Meal meal2;
    private Water validWaterList;

    @BeforeEach
    public void setUp() {
        validDay = new Day("validDay");
        validDay.insertExercise(new Exercise(3, 12, 50, "Bench_Press"));
        validDay.insertExercise(new Exercise(3, 12, 80, "Squat"));

        meal1 = new Meal("potato", 100);
        meal2 = new Meal("pasta", 900);

        validWaterList = new Water();
        validWaterList.addWater(100.0f);
        validWaterList.addWater(400.0f);
    }

    @AfterEach
    public void tearDown() {
        dailyRecord = null;
        validDay = null;
        validMeal = null;
        validWaterList = null;
    }

    @Test
    public void testConstructor_default() {
        dailyRecord = new DailyRecord();
        assertNotNull(dailyRecord.getDayFromRecord());
        assertEquals("Empty Day", dailyRecord.getDayFromRecord().getName());
        assertNotNull(dailyRecord.getWater());
        assertNotNull(dailyRecord.getMealList());
    }

    @Test
    public void logDay_validDay() {
        dailyRecord = new DailyRecord();
        dailyRecord.logDay(validDay);

        assertEquals(validDay, dailyRecord.getDayFromRecord());
    }

    @Test
    public void logDay_emptyDay() {
        dailyRecord = new DailyRecord();
        Day emptyDay = new Day("Empty Day");
        dailyRecord.logDay(emptyDay);
        assertEquals(emptyDay, dailyRecord.getDayFromRecord());
    }

    @Test
    public void logDay_nullDay() {
        dailyRecord = new DailyRecord();
        assertNotNull(dailyRecord.getDayFromRecord());
        assertEquals("Empty Day", dailyRecord.getDayFromRecord().getName());
    }

    @Test
    public void addMealToRecord_validMeals() {
        dailyRecord = new DailyRecord();
        dailyRecord.addMealToRecord(meal1);
        dailyRecord.addMealToRecord(meal2);
        assertFalse(dailyRecord.getMealList().isEmpty());
        assertEquals(2, dailyRecord.getMealList().getSize());
        assertEquals("pasta", dailyRecord.getMealList().getMeals().get(1).getName());
    }

    @Test
    public void addMealToRecord_negativeCaloriesMeal() {
        dailyRecord = new DailyRecord();
        assertThrows(AssertionError.class, () -> dailyRecord.addMealToRecord(new Meal("potato", -100)));
    }

    @Test
    public void addMealToRecord_nullMeal() {
        dailyRecord = new DailyRecord();
        assertThrows(AssertionError.class, () -> dailyRecord.addMealToRecord(null));
    }

    @Test
    public void deleteMealFromRecord_validIndex() {
        dailyRecord = new DailyRecord();
        dailyRecord.addMealToRecord(meal1);
        dailyRecord.addMealToRecord(meal2);
        dailyRecord.deleteMealFromRecord(0);
        assertEquals(1, dailyRecord.getMealList().getSize());
        assertEquals("pasta", dailyRecord.getMealList().getMeals().get(0).getName());
    }

    @Test
    public void deleteMealFromRecord_negativeIndex() {
        dailyRecord = new DailyRecord();
        dailyRecord.addMealToRecord(meal1);
        assertThrows(AssertionError.class, () -> dailyRecord.deleteMealFromRecord(-1));
    }

    @Test
    public void deleteMealFromRecord_outOfBoundsIndex() {
        dailyRecord = new DailyRecord();
        dailyRecord.addMealToRecord(meal1);
        assertThrows(IndexOutOfBoundsException.class, () -> dailyRecord.deleteMealFromRecord(10));
    }

    @Test
    public void addWaterToRecord_validWater() {
        dailyRecord = new DailyRecord();
        dailyRecord.addWaterToRecord(100.0f);
        dailyRecord.addWaterToRecord(400.0f);
        assertFalse(dailyRecord.getWater().isEmpty());
        assertEquals(2, dailyRecord.getWater().getWaterList().size());
        assertEquals(100.0f, dailyRecord.getWater().getWaterList().get(0));
    }

    @Test
    public void addWaterToRecord_negativeWater() {
        dailyRecord = new DailyRecord();
        assertThrows(AssertionError.class, () -> dailyRecord.addWaterToRecord(-500.0f));
    }

    @Test
    public void removeWaterFromRecord_validIndex() {
        dailyRecord = new DailyRecord();
        dailyRecord.addWaterToRecord(100.0f);
        dailyRecord.addWaterToRecord(400.0f);
        dailyRecord.removeWaterFromRecord(0);
        assertEquals(1, dailyRecord.getWater().getWaterList().size());
        assertEquals(400.0f, dailyRecord.getWater().getWaterList().get(0));
    }

    @Test
    public void removeWaterFromRecord_negativeIndex() {
        dailyRecord = new DailyRecord();
        dailyRecord.addWaterToRecord(100.0f);
        assertThrows(IndexOutOfBoundsException.class, () -> dailyRecord.removeWaterFromRecord(-1));
    }

    @Test
    public void removeWaterFromRecord_outOfBoundsIndex() {
        dailyRecord = new DailyRecord();
        dailyRecord.addWaterToRecord(100.0f);
        assertThrows(IndexOutOfBoundsException.class, () -> dailyRecord.removeWaterFromRecord(10));
    }

    @Test
    public void getDayFromRecord_initialDay() {
        dailyRecord = new DailyRecord();
        assertNotNull(dailyRecord.getDayFromRecord());
        assertEquals("Empty Day", dailyRecord.getDayFromRecord().getName());
    }

    @Test
    public void getDayFromRecord_afterLogDay() {
        dailyRecord = new DailyRecord();
        dailyRecord.logDay(validDay);
        assertEquals("validDay", dailyRecord.getDayFromRecord().getName());
    }

    @Test
    public void getDayFromRecord_nullDay() {
        dailyRecord = new DailyRecord();
        Day day = dailyRecord.getDayFromRecord();
        assertNotNull(day);
    }

    @Test
    public void getMealList_initialMealList() {
        dailyRecord = new DailyRecord();
        MealList mealList = dailyRecord.getMealList();
        assertNotNull(mealList);
        assertTrue(mealList.getMeals().isEmpty());
    }

    @Test
    public void getMealList_afterAddMeal() {
        dailyRecord = new DailyRecord();
        dailyRecord.addMealToRecord(meal2);
        dailyRecord.addMealToRecord(meal1);
        MealList mealList = dailyRecord.getMealList();
        assertEquals("potato" , mealList.getMeals().get(0).getName());
        assertEquals("pasta", mealList.getMeals().get(1).getName());
    }

    @Test
    public void getMealList_afterDeleteMeal() {
        dailyRecord = new DailyRecord();
        dailyRecord.addMealToRecord(meal1);
        dailyRecord.addMealToRecord(meal2);
        dailyRecord.deleteMealFromRecord(0);
        assertEquals(1, dailyRecord.getMealList().getSize());
    }

    @Test
    public void getWater_initialWater() {
        dailyRecord = new DailyRecord();
        Water water = dailyRecord.getWater();
        assertNotNull(water);
        assertTrue(water.getWaterList().isEmpty());
    }

    @Test
    public void getWater_afterAddWater() {
        dailyRecord = new DailyRecord();
        dailyRecord.addWaterToRecord(100.0f);
        dailyRecord.addWaterToRecord(400.0f);
        Water water = dailyRecord.getWater();
        assertEquals(100.0f , water.getWaterList().get(0));
        assertEquals(400.0f , water.getWaterList().get(1));
    }

    @Test
    public void getWater_afterRemoveWater() {
        dailyRecord = new DailyRecord();
        dailyRecord.addWaterToRecord(100.0f);
        dailyRecord.addWaterToRecord(400.0f);
        dailyRecord.removeWaterFromRecord(0);
        assertEquals(1, dailyRecord.getWater().getWaterList().size());
    }

    @Test
    public void toString_emptyRecord() {
        MealList mockMealList = mock(MealList.class);
        Water mockWater = mock(Water.class);
        Day mockDay = mock(Day.class);

        dailyRecord = spy(new DailyRecord());

        when(mockMealList.getMeals()).thenReturn(new ArrayList<Meal>());
        when(mockWater.getWaterList()).thenReturn(new ArrayList<Float>());
        when(mockDay.getExercisesCount()).thenReturn(0);

        doReturn(mockMealList).when(dailyRecord).getMealList();
        doReturn(mockDay).when(dailyRecord).getDayFromRecord();
        doReturn(mockWater).when(dailyRecord).getWater();

        String result = dailyRecord.toString();

        assertTrue(result.contains("No Day"));
        assertTrue(result.contains("No Water"));
        assertTrue(result.contains("No Meals"));
    }

    @Test
    public void toString_callsGetCaloriesFromMeal() {
        dailyRecord = new DailyRecord();
        dailyRecord.addMealToRecord(meal1);
        dailyRecord.addMealToRecord(meal2);
        String result = dailyRecord.toString();
        assertTrue(result.contains("Total Calories from Meals:"));
    }

    @Test
    public void toString_callsGetTotalWaterl() {
        dailyRecord = new DailyRecord();
        dailyRecord.addWaterToRecord(100.0f);
        dailyRecord.addWaterToRecord(400.0f);
        String result = dailyRecord.toString();
        assertTrue(result.contains("Total Water Intake:"));
    }

    @Test
    public void toString_testDayToString() {
        dailyRecord = new DailyRecord();
        dailyRecord.logDay(validDay);
        String result = dailyRecord.toString();
        assertFalse(result.contains("No Day"));
    }
}
