// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

/**
 * Command to view a summary of the weekly workout history.
 * <p>
 * The {@code WeeklySummaryCommand} retrieves a summary of the workouts completed in the past week from the
 * {@link History} object and formats it for display.
 * </p>
 */
public class WeeklySummaryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "wk";

    /**
     * Executes the command to retrieve and display the weekly workout summary.
     *
     * @param history the {@link History} object containing workout records
     * @return a {@link CommandResult} containing the weekly workout summary or a message if no records are available
     */
    @Override
    public CommandResult execute(History history) {
        String weeklySummary = history.getWeeklyWorkoutSummary();
        return new CommandResult("Your weekly workout summary: \n" + weeklySummary);
    }
}

