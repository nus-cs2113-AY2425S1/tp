// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;

import java.time.LocalDate;

import static common.Utils.formatDate;

/**
 * Represents a command to show the full history or perform history-related operations.
 */
public class DeleteHistoryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "delete";

    public DeleteHistoryCommand(LocalDate date) {
        super(date);
    }

    @Override
    public CommandResult execute(History history) {
        DailyRecord record = history.deleteRecord(date);
        if (record == null) {
            return new CommandResult("No record found at " + formatDate(date));
        }
        String result = record.toString();
        return new CommandResult(result);
    }
}



