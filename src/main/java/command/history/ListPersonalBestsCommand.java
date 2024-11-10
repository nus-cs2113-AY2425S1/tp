// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

/**
 * Command to list personal bests for all exercises.
 * <p>
 * The {@code ListPersonalBestsCommand} retrieves and displays the personal best
 * records for each exercise in the workout history. If there are no personal bests
 * available, an appropriate message is returned.
 * </p>
 */
public class ListPersonalBestsCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "pb";

    /**
     * Executes the command to retrieve and format the personal bests for all exercises.
     *
     * @param history the {@link History} object containing workout records
     * @return a {@link CommandResult} with the formatted personal bests or a message if no records are found
     */
    @Override
    public CommandResult execute(History history) {
        String result = history.getFormattedPersonalBests();
        return new CommandResult(result);
    }
}

