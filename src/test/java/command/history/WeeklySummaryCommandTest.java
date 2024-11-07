package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import programme.ProgrammeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeeklySummaryCommandTest {

    private ProgrammeList programmeList;
    private History history;
    private WeeklySummaryCommand weeklySummaryCommand;

    @BeforeEach
    public void setUp() {
        programmeList = new ProgrammeList();
        history = new History();
        weeklySummaryCommand = new WeeklySummaryCommand();
    }

    @Test
    public void testExecuteHappyPathWithWeeklyData() {
        // Arrange
        LocalDate today = LocalDate.now();
        LocalDate threeDaysAgo = today.minusDays(3);
        DailyRecord todayRecord = new DailyRecord();
        DailyRecord threeDaysAgoRecord = new DailyRecord();

        todayRecord.logDayToRecord(new programme.Day("Day 1"));
        todayRecord.getDayFromRecord().insertExercise(new programme.Exercise(3, 12, 50, 200, "Bench Press"));

        threeDaysAgoRecord.logDayToRecord(new programme.Day("Day 2"));
        threeDaysAgoRecord.getDayFromRecord().insertExercise(new programme.Exercise(3, 10, 45, 150, "Squat"));

        history.logRecord(today, todayRecord);
        history.logRecord(threeDaysAgo, threeDaysAgoRecord);

        // Act
        CommandResult result = weeklySummaryCommand.execute(programmeList, history);

        // Assert
        String expectedMessage = "Your weekly workout summary: \n" + history.getWeeklyWorkoutSummary();
        assertEquals(expectedMessage, result.getMessage(), "Execution should return correct weekly workout summary.");
    }

    @Test
    public void testExecuteEdgeCaseNoWorkoutHistory() {
        // Act
        CommandResult result = weeklySummaryCommand.execute(programmeList, history);

        // Assert
        String expectedMessage = "Your weekly workout summary: \nNo workout history available.";
        assertEquals(expectedMessage, result.getMessage(), "Execution should indicate no workout history available.");
    }

    @Test
    public void testExecuteEdgeCaseOnlyOldRecords() {
        // Arrange
        LocalDate tenDaysAgo = LocalDate.now().minusDays(10);
        DailyRecord oldRecord = new DailyRecord();

        oldRecord.logDayToRecord(new programme.Day("Day 1"));
        oldRecord.getDayFromRecord().insertExercise(new programme.Exercise(3, 10, 40, 120, "Deadlift"));

        history.logRecord(tenDaysAgo, oldRecord);

        // Act
        CommandResult result = weeklySummaryCommand.execute(programmeList, history);

        // Assert
        String expectedMessage = "Your weekly workout summary: \nNo workout history available for the past week.";
        assertEquals(expectedMessage, result.getMessage(),
                "Execution should indicate no recent workout history available.");
    }
}
