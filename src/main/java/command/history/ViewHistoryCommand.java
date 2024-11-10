// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;

import java.time.LocalDate;

import static common.Utils.formatDate;

/**
 * Command to view the history for a specific date.
 * <p>
 * The {@code ViewHistoryCommand} retrieves and displays the workout record for a specified date.
 * If no record exists for the given date, it returns a message indicating that no record was found.
 * </p>
 */
public class ViewHistoryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "view";

    /**
     * Constructs a {@code ViewHistoryCommand} for a specific date.
     *
     * @param date the {@link LocalDate} for which the history record is to be viewed
     */
    public ViewHistoryCommand(LocalDate date) {
        super(date);
    }

    /**
     * Executes the command to retrieve and display the history record for the specified date.
     *
     * @param history the {@link History} object containing workout records
     * @return a {@link CommandResult} containing the formatted daily record or a message if no record is found
     */
    @Override
    public CommandResult execute(History history) {
        if (!history.hasRecord(date)) {
            return new CommandResult("No record found for " + formatDate(date));
        }
        DailyRecord record = history.getRecordByDate(date);
        String result = record.toString();
        return new CommandResult(result);
    }
}

