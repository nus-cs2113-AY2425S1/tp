package command.programme.edit;

import command.CommandResult;
import programme.Day;
import programme.Exercise;
import programme.ProgrammeList;

import java.util.logging.Level;

/**
 * Command to edit a specific exercise's fields within a day of a programme.
 * <p>
 * This class encapsulates the functionality to update the details of an
 * existing exercise identified by its ID within a specified day of a programme.
 * </p>
 */
public class EditExerciseCommand extends EditCommand {

    public static final String SUCCESS_MESSAGE_FORMAT = "Update exercise %d to: %s%n";

    private final Exercise updateExercise;

    /**
     * Constructs an EditExerciseCommand with the specified programme index, day ID,
     * exercise ID, and updated exercise details.
     *
     * @param programmeIndex the index of the programme containing the exercise to be updated
     * @param dayId the ID of the day containing the exercise to be updated
     * @param exerciseId the ID of the exercise to be updated
     * @param updateExercise the exercise object containing the fields meant to be updated
     */
    public EditExerciseCommand(int programmeIndex, int dayId, int exerciseId, Exercise updateExercise) {
        super(programmeIndex, dayId, exerciseId);
        assert updateExercise != null : "updateExercise must not be null";
        this.updateExercise = updateExercise;
    }

    /**
     * Executes the command to update the specified exercise in the given day of the programme.
     *
     * @param programmes the ProgrammeList containing the programmes where the exercise will be updated
     * @return a CommandResult containing a success message indicating the updated exercise
     */
    @Override
    public CommandResult execute(ProgrammeList programmes) {
        assert programmes != null : "programmes cannot be null";
        Day selectedDay = programmes.getDay(programmeIndex, dayIndex);
        Exercise updated = selectedDay.updateExercise(exerciseId, updateExercise);
        String result = String.format(SUCCESS_MESSAGE_FORMAT, exerciseId, updated);
        logger.log(Level.INFO, "EditExerciseCommand executed successfully.");
        return new CommandResult(result);
    }
}
