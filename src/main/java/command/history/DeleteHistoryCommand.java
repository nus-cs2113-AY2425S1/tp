// @@author andreusxcarvalho
package command.history;

import command.CommandResult;
import history.DailyRecord;
import history.History;

import java.time.LocalDate;

import static common.Utils.formatDate;

/**
 * Represents a command to delete a specific history record by date.
 * <p>
 * The {@code DeleteHistoryCommand} checks for the existence of a history record on
 * the given date and removes it if present. If the record exists, it returns a message
 * confirming deletion and displaying the record. If no record is found, it returns a
 * message indicating that no record exists on the specified date.
 * </p>
 */
public class DeleteHistoryCommand extends HistoryCommand {
    public static final String COMMAND_WORD = "delete";

    /**
     * Constructs a {@code DeleteHistoryCommand} with the specified date.
     *
     * @param date the {@link LocalDate} of the record to be deleted
     */
    public DeleteHistoryCommand(LocalDate date) {
        super(date);
    }

    /**
     * Executes the delete command on the given history and returns the result.
     * <p>
     * Attempts to delete the {@link DailyRecord} for the specified date from the {@link History}.
     * If the record exists, it is deleted, and a success message with the record details is returned.
     * If the record does not exist, a message is returned indicating that no record was found.
     * </p>
     *
     * @param history the {@link History} object from which the record is to be deleted
     * @return a {@link CommandResult} indicating success if the record is deleted, or an error message if not
     */
    @Override
    public CommandResult execute(History history) {
        DailyRecord record = history.deleteRecord(date);
        if (record == null) {
            return new CommandResult("No record found at " + formatDate(date));
        }
        String result = record.toString();
        return new CommandResult("Deleted record: \n" + result);
    }
}

