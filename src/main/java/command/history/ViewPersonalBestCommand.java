package command.history;

import command.Command;
import command.CommandResult;
import programme.ProgrammeList;
import history.History;
import java.util.Map;
import programme.Exercise;

public class ViewPersonalBestCommand extends Command {
    // New command format for specific exercise personal best
    public static final String COMMAND_WORD = "pb_exercise";

    private final String exerciseName;  // Store the exercise name (could be null if user wants all personal bests)

    public ViewPersonalBestCommand(String exerciseName) {
        this.exerciseName = exerciseName;  // Initialize with the exercise name
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        // If no exercise is specified, get personal bests for all exercises
        Map<String, Exercise> personalBests = history.getPersonalBests();  // Call method for all exercises

        if (personalBests.isEmpty()) {
            return new CommandResult("No personal bests found.");
        }

        // If an exercise name is specified, get personal best for that exercise
        if (exerciseName != null && !exerciseName.isEmpty()) {
            String personalBest = history.getPersonalBestForExercise(exerciseName);
            return new CommandResult(personalBest);
        }

        StringBuilder bestsMessage = new StringBuilder("Personal bests for all exercises:\n");
        for (Map.Entry<String, Exercise> entry : personalBests.entrySet()) {
            bestsMessage.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue().toString())
                    .append("\n");
        }

        return new CommandResult(bestsMessage.toString());
    }
}
