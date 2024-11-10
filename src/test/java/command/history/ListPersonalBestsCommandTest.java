package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;
import programme.Exercise;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListPersonalBestsCommandTest {

    private ListPersonalBestsCommand listPersonalBestsCommand;
    private History history;
    private ProgrammeList programmeList;

    @BeforeEach
    public void setUp() {
        listPersonalBestsCommand = new ListPersonalBestsCommand();
        history = new History();
        programmeList = new ProgrammeList(); // Assuming ProgrammeList can be initialized this way
    }

    @Test
    public void testExecuteHappyPathSingleExercise() {
        // Happy Path: Single Exercise Record
        LocalDate date = LocalDate.now();
        DailyRecord dailyRecord = new DailyRecord();
        Exercise exercise = new Exercise(3, 12, 50, 200, "Bench Press");
        dailyRecord.logDayToRecord(new programme.Day("Day 1"));
        dailyRecord.getDayFromRecord().insertExercise(exercise);
        history.logRecord(date, dailyRecord);

        CommandResult expectedResult = new CommandResult(history.getFormattedPersonalBests());

        // Act
        CommandResult result = listPersonalBestsCommand.execute(programmeList, history);

        // Assert
        assertEquals(expectedResult, result,
                "Execution with a single exercise should return the correct personal best.");
    }

    @Test
    public void testExecuteEdgeCaseNoExercises() {
        // Edge Case: No Exercises in History
        CommandResult expectedResult = new CommandResult("No personal bests found.");

        // Act
        CommandResult result = listPersonalBestsCommand.execute(programmeList, history);

        // Assert
        assertEquals(expectedResult, result, "Execution with no exercises should return 'No personal bests found.'");
    }

    @Test
    public void testExecuteEdgeCaseMultipleExercises() {
        // Edge Case: Multiple Exercises with different dates
        LocalDate date1 = LocalDate.now().minusDays(1);
        LocalDate date2 = LocalDate.now();

        DailyRecord record1 = new DailyRecord();
        DailyRecord record2 = new DailyRecord();

        Exercise exercise1 = new Exercise(3, 12, 60, 250, "Squat");
        Exercise exercise2 = new Exercise(4, 10, 70, 300, "Deadlift");

        record1.logDayToRecord(new programme.Day("Day 1"));
        record1.getDayFromRecord().insertExercise(exercise1);
        record2.logDayToRecord(new programme.Day("Day 2"));
        record2.getDayFromRecord().insertExercise(exercise2);

        history.logRecord(date1, record1);
        history.logRecord(date2, record2);

        CommandResult expectedResult = new CommandResult(history.getFormattedPersonalBests());

        // Act
        CommandResult result = listPersonalBestsCommand.execute(programmeList, history);

        // Assert
        assertEquals(expectedResult, result,
                "Execution with multiple exercises should return the correct personal bests.");
    }
}

