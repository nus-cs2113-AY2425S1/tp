// @@author TVageesan

package command.programme.edit;

import command.CommandResult;
import programme.Day;
import programme.ProgrammeList;

import java.util.logging.Level;

/**
 * Command to delete a specific day from a programme.
 * <p>
 * This class encapsulates the functionality to remove a day from a
 * specified programme, identified by its index.
 * </p>
 */
public class DeleteDayCommand extends EditCommand {
    public static final String SUCCESS_MESSAGE_FORMAT = "Deleted day: %s%n";

    /**
     * Constructs a DeleteDayCommand with the specified programme index and day ID.
     *
     * @param programmeIndex the index of the programme from which the day will be deleted
     * @param dayId the ID of the day to be deleted from the programme
     */
    public DeleteDayCommand(int programmeIndex, int dayId) {
        super(programmeIndex, dayId);
    }

    /**
     * Executes the command to delete the specified day from the programme.
     *
     * @param programmes the ProgrammeList containing the programmes where the day will be deleted
     * @return a CommandResult containing a success message indicating the deleted day
     */
    public CommandResult execute(ProgrammeList programmes) {
        assert programmes != null : "programmes cannot be null";
        Day deleted = programmes.deleteDay(programmeIndex, dayIndex);
        String result = String.format(SUCCESS_MESSAGE_FORMAT, deleted);
        logger.log(Level.INFO, "DeleteDayCommand executed successfully.");
        return new CommandResult(result);
    }
}
