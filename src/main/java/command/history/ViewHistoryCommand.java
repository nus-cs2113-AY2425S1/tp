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
public class ViewHistoryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "view";

    public ViewHistoryCommand(LocalDate date) {
        super(date);
    }

    @Override
    public CommandResult execute(History history) {
        if (!history.hasRecord(date)){
            return new CommandResult("No record found for " + formatDate(date));
        }
        DailyRecord record = history.getRecordByDate(date);
        String result = record.toString();
        return new CommandResult(result);
    }
}



