package history;

//
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryTest {
    private History history;
    private Day day1;
    private Day day2;
    private LocalDate date1;
    private LocalDate date2;

    @BeforeEach
    public void setUp() {
        history = new History();

        // Create exercises for Day 1
        ArrayList<Exercise> exercisesDay1 = new ArrayList<>();
        exercisesDay1.add(new Exercise(3, 12, 50, "Bench_Press"));
        exercisesDay1.add(new Exercise(3, 12, 80, "Squat"));

        // Create exercises for Day 2
        ArrayList<Exercise> exercisesDay2 = new ArrayList<>();
        exercisesDay2.add(new Exercise(3, 10, 100, "Deadlift"));
        exercisesDay2.add(new Exercise(4, 8, 0, "Pull_Up"));  // No weight for Pull_Up

        // Create Day objects
        day1 = new Day("Day 1", exercisesDay1);
        day2 = new Day("Day 2", exercisesDay2);

        // Dates for logging the days (now using LocalDate)
        date1 = LocalDate.of(2024, 10, 12);
        date2 = LocalDate.of(2024, 10, 13);
    }

    @Test
    public void testLogDayAndToString() {
        // Log the days into history
        //DailyRecord dailyrecord1 = history.getRecordByDate(date1);
        //DailyRecord dailyrecord2 = history.getRecordByDate(date1);
        //dailyrecord1.logDay(day1);
        //dailyrecord2.logDay(day2);

        // Object-based comparison
        //assertEquals(day1, history.getRecordByDate(date1).getDayFromRecord());
        //assertEquals(day2, history.getRecordByDate(date2).getDayFromRecord());
    }

    @Test
    public void testEmptyHistory() {
        // Ensure that history returns "No workout history available" when empty
        //assertEquals("No workout history available.", history.toString());
    }

    @Test
    public void testOverwriteDay() {
        // Log day1 with date1, then log another day with the same date to overwrite
        //history.getRecordByDate(date1).logDay(day1);

        // Modify day1 with a different exercise
        //Day modifiedDay = new Day("Day 1");
        //modifiedDay.insertExercise(new Exercise(3, 12, 15, "Bicep_Curl"));

        // Log the modified day with the same date
        //history.getRecordByDate(date1).logDay(modifiedDay);

        // Object-based comparison
        //assertEquals(modifiedDay, history.getRecordByDate(date1).getDayFromRecord());
    }
}

