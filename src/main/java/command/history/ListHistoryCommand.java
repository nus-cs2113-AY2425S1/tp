// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

/**
 * Represents a command to list the full history of workout records.
 * <p>
 * The {@code ListHistoryCommand} retrieves all entries in the workout history and
 * formats them as a string for display. If the history is empty, a message indicating
 * that no history is available will be returned.
 * </p>
 */
public class ListHistoryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "list";

    /**
     * Executes the command to retrieve and format the entire workout history.
     *
     * @param history the {@link History} object containing workout records
     * @return a {@link CommandResult} with the formatted history or a message if no history is available
     */
    @Override
    public CommandResult execute(History history) {
        return new CommandResult(history.toString());
    }
}

