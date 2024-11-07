package command.history;

import command.CommandResult;
import history.History;
import programme.ProgrammeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewPersonalBestCommandTest {

    private ProgrammeList programmeList;
    private History history;

    @BeforeEach
    public void setUp() {
        programmeList = new ProgrammeList();
        history = new History();
    }

    @Test
    public void testExecuteHappyPathSingleExercise() {
        // Arrange
        String exerciseName = "Bench Press";
        history.getRecordByDate(java.time.LocalDate.now()).logDayToRecord(new programme.Day("Day 1"));
        history.getRecordByDate(java.time.LocalDate.now()).getDayFromRecord().insertExercise(
                new programme.Exercise(3, 12, 50, 200, exerciseName)
        );

        ViewPersonalBestCommand command = new ViewPersonalBestCommand(exerciseName);

        // Act
        CommandResult result = command.execute(programmeList, history);

        // Assert
        String expectedMessage = "Personal best for " + exerciseName + ": " +
                "Bench Press: 3 sets of 12 at 50kg | Burnt 200 cals";
        assertEquals(expectedMessage, result.getMessage(), "Execution should return correct personal best message.");
    }

    @Test
    public void testExecuteEdgeCaseExerciseNotFound() {
        // Arrange
        String exerciseName = "Squat";
        ViewPersonalBestCommand command = new ViewPersonalBestCommand(exerciseName);

        // Act
        CommandResult result = command.execute(programmeList, history);

        // Assert
        String expectedMessage = "No personal best found for " + exerciseName;
        assertEquals(expectedMessage, result.getMessage(), "Execution should return message indicating no personal best found.");
    }

    @Test
    public void testExecuteEdgeCaseNoExerciseSpecified() {
        // Arrange
        ViewPersonalBestCommand command = new ViewPersonalBestCommand("");

        // Act
        CommandResult result = command.execute(programmeList, history);

        // Assert
        String expectedMessage = "Please specify an exercise to view its personal best.";
        assertEquals(expectedMessage, result.getMessage(), "Execution with no exercise specified should prompt for exercise name.");
    }
}

