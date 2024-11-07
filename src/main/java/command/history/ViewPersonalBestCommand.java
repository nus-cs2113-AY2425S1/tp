// @@author andreusxcarvalho
package command.history;

import command.Command;
import command.CommandResult;
import programme.ProgrammeList;
import history.History;

public class ViewPersonalBestCommand extends Command {
    public static final String COMMAND_WORD = "pb";

    private final String exerciseName;

    public ViewPersonalBestCommand(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        // If an exercise name is specified, get personal best for that exercise
        if (exerciseName != null && !exerciseName.isEmpty()) {
            String personalBest = history.getPersonalBestForExercise(exerciseName);
            return new CommandResult(personalBest);
        }

        // If no specific exercise is specified, prompt for exercise name
        return new CommandResult("Please specify an exercise to view its personal best.");
    }
}

