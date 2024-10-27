package command.history;

import command.Command;
import command.CommandResult;
import programme.ProgrammeList;
import history.History;
import programme.Exercise;

import java.util.Map;

/**
 * Command to list personal bests for all exercises.
 */
public class ListPersonalBestsCommand extends Command {
    public static final String COMMAND_WORD = "pb";

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        // Retrieve personal bests for all exercises
        Map<String, Exercise> personalBests = history.getPersonalBests();

        // Check if there are any personal bests recorded
        if (personalBests.isEmpty()) {
            return new CommandResult("No personal bests found.");
        }

        // Construct the message displaying all personal bests
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