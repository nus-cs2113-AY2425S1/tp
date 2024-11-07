// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

/**
 * Command to list personal bests for all exercises.
 */
public class ListPersonalBestsCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "pb";

    @Override
    public CommandResult execute(History history) {
        String result = history.getFormattedPersonalBests();
        return new CommandResult(result);
    }
}

