//@@author Bev-low
package command.water;

import command.CommandResult;
import history.DailyRecord;
import history.History;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to delete a specified entry of water intake from the daily record for a given date.
 */
public class DeleteWaterCommand extends WaterCommand {
    public static final String COMMAND_WORD = "delete";
    private static final Logger logger = Logger.getLogger(DeleteWaterCommand.class.getName());

    protected int indexWaterToDelete;

    /**
     * Constructs a {@code DeleteWaterCommand} with the specified index of the water entry to delete and the date.
     *
     * @param indexOfWaterToDelete The index of the water entry to delete. Must be zero or greater.
     * @param date The date for which the water entry is to be deleted. Must not be {@code null}.
     * @throws AssertionError if {@code indexOfWaterToDelete} is negative or {@code date} is {@code null}.
     */
    public DeleteWaterCommand(int indexOfWaterToDelete, LocalDate date) {
        super(date);

        assert indexOfWaterToDelete >= 0 : "Index to delete cannot be negative";

        this.indexWaterToDelete = indexOfWaterToDelete;
        logger.log(Level.INFO, "DeleteWaterCommand created for index: {0} on date: {1}",
                new Object[]{indexWaterToDelete, date});
    }

    /**
     * Executes the command to delete a water entry from the daily record in the specified history.
     *
     * @param history The {@code History} object that contains daily records.
     * @return A {@code CommandResult} containing a message indicating the success of the deletion.
     * @throws AssertionError if the daily record for the specified date is not found.
     */
    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        assert dailyRecord != null : "Daily record not found";
        float deletedWater = dailyRecord.removeWaterFromRecord(indexWaterToDelete);

        return new CommandResult(deletedWater + " liters of water has been deleted");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteWaterCommand)) {
            return false;
        }
        DeleteWaterCommand that = (DeleteWaterCommand) o;
        return indexWaterToDelete == that.indexWaterToDelete &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexWaterToDelete, date);
    }
}
