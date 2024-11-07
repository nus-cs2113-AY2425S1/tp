// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.History;

public class WeeklySummaryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "wk";

    @Override
    public CommandResult execute(History history) {
        String weeklySummary = history.getWeeklyWorkoutSummary();
        return new CommandResult("Your weekly workout summary: \n" + weeklySummary);
    }
}

