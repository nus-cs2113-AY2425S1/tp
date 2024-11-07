// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

public class ViewPersonalBestCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "pb";

    private final String exerciseName;

    public ViewPersonalBestCommand(String exerciseName) {
        assert exerciseName != null;
        assert !exerciseName.isEmpty();
        this.exerciseName = exerciseName;
    }

    @Override
    public CommandResult execute(History history) {
        String result = history.getPersonalBestForExercise(exerciseName);
        return new CommandResult(result);
    }
}

