// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

/**
 * Represents a command to show the full history or perform history-related operations.
 */
public class ListHistoryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(History history) {
        return new CommandResult(history.toString());
    }
}



