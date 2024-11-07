// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

/**
 * Command to view the personal best for a specific exercise.
 * <p>
 * The {@code ViewPersonalBestCommand} retrieves and displays the personal best record for a specified exercise.
 * If no record exists for the exercise, a message indicating this is returned.
 * </p>
 */
public class ViewPersonalBestCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "pb";

    private final String exerciseName;

    /**
     * Constructs a {@code ViewPersonalBestCommand} with a specified exercise name.
     *
     * @param exerciseName the name of the exercise to view the personal best for
     * @throws AssertionError if {@code exerciseName} is null or empty
     */
    public ViewPersonalBestCommand(String exerciseName) {
        assert exerciseName != null : "Exercise name must not be null";
        assert !exerciseName.isEmpty() : "Exercise name must not be empty";
        this.exerciseName = exerciseName;
    }

    /**
     * Executes the command to retrieve and display the personal best for the specified exercise.
     *
     * @param history the {@link History} object containing workout records
     * @return a {@link CommandResult} containing the personal best for the specified exercise or a message
     * indicating that no record was found
     */
    @Override
    public CommandResult execute(History history) {
        String result = history.getPersonalBestForExercise(exerciseName);
        return new CommandResult(result);
    }
}

