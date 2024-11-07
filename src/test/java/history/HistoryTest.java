package history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.Exercise;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryTest {

    private History history;
    private Day sampleDay;

    @BeforeEach
    public void setUp() {
        history = new History();
        sampleDay = new Day("Leg Day");
        Exercise sampleExercise = new Exercise(3, 10, 100, 200, "Squat");
        sampleDay.insertExercise(sampleExercise);
    }

    @Test
    public void testLogRecordAndGetRecordByDate() {
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.logDayToRecord(sampleDay);

        history.logRecord(date, dailyRecord);

        DailyRecord retrievedRecord = history.getRecordByDate(date);
        assertNotNull(retrievedRecord, "Retrieved record should not be null.");
        assertEquals(dailyRecord, retrievedRecord, "Retrieved record should match the logged record.");
    }

    @Test
    public void testGetWeeklyWorkoutSummaryWithData() {
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.logDayToRecord(sampleDay);

        history.logRecord(date, dailyRecord);

        String weeklySummary = history.getWeeklyWorkoutSummary();
        assertTrue(weeklySummary.contains("Leg Day"), "Weekly summary should contain the day's name.");
        assertTrue(weeklySummary.contains("Squat"), "Weekly summary should contain the exercise name.");
    }

    @Test
    public void testGetWeeklyWorkoutSummaryWithoutData() {
        String weeklySummary = history.getWeeklyWorkoutSummary();
        assertEquals("No workout history available.", weeklySummary, "Weekly summary should indicate no data available.");
    }

    @Test
    public void testGetPersonalBestForExercise() {
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.logDayToRecord(sampleDay);

        history.logRecord(date, dailyRecord);

        String personalBest = history.getPersonalBestForExercise("Squat");
        assertTrue(personalBest.contains("Personal best for Squat"), "Personal best output should contain exercise name.");
        assertTrue(personalBest.contains("100kg"), "Personal best output should contain correct weight.");
    }

    @Test
    public void testGetFormattedPersonalBests() {
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.logDayToRecord(sampleDay);

        history.logRecord(date, dailyRecord);

        String formattedPersonalBests = history.getFormattedPersonalBests();
        assertTrue(formattedPersonalBests.contains("Personal bests for all exercises:"), "Output should contain header.");
        assertTrue(formattedPersonalBests.contains("Squat: 3 sets of 10 at 100kg"), "Output should contain exercise details.");
    }

    @Test
    public void testToStringNoHistory() {
        String historyString = history.toString();
        assertEquals("No history available.", historyString, "Output should indicate no history available.");
    }

    @Test
    public void testToStringWithHistory() {
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.logDayToRecord(sampleDay);

        history.logRecord(date, dailyRecord);

        String historyString = history.toString();
        assertTrue(historyString.contains("Completed On:"), "Output should contain 'Completed On' date.");
        assertTrue(historyString.contains("Leg Day"), "Output should contain the day's name.");
    }
}

