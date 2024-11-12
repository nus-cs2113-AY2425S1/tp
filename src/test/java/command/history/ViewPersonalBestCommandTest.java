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
        String expectedMessage = "Personal best for " + exerciseName + ": 3 sets of 12 at 50kg";
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
        assertEquals(expectedMessage, result.getMessage(),
                "Execution should return message indicating no personal best found.");
    }

    @Test
    public void testExecuteEdgeCaseSimilarExerciseNames() {
        // Arrange
        String exerciseName1 = "Bench Press";
        String exerciseName2 = "Bench Press Incline";

        history.getRecordByDate(java.time.LocalDate.now()).logDayToRecord(new programme.Day("Day 1"));
        history.getRecordByDate(java.time.LocalDate.now()).getDayFromRecord().insertExercise(
                new programme.Exercise(3, 12, 50, 200, exerciseName1)
        );
        history.getRecordByDate(java.time.LocalDate.now()).getDayFromRecord().insertExercise(
                new programme.Exercise(3, 10, 45, 180, exerciseName2)
        );

        ViewPersonalBestCommand command = new ViewPersonalBestCommand(exerciseName2);

        // Act
        CommandResult result = command.execute(programmeList, history);

        // Assert
        String expectedMessage = "Personal best for " + exerciseName2 + ": 3 sets of 10 at 45kg";
        assertEquals(expectedMessage, result.getMessage(),
                "Execution should return the correct personal best for a similarly named exercise.");
    }
}

