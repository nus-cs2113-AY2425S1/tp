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
 * Represents a command to add a specified amount of water to the daily record for a given date.
 */
public class AddWaterCommand extends WaterCommand {
    public static final String COMMAND_WORD = "add";
    private static final Logger logger = Logger.getLogger(AddWaterCommand.class.getName());

    protected float waterToAdd;

    /**
     * Constructs an AddWaterCommand with the specified amount of water to add and the date.
     *
     * @param waterToAdd The amount of water to add in liters. Must be positive.
     * @param date The date for which the water is to be added. Must not be null.
     * @throws AssertionError if waterToAdd is not positive or date is null.
     */
    public AddWaterCommand(float waterToAdd, LocalDate date) {
        super(date);

        assert waterToAdd > 0 : "Water to add must be positive";

        this.waterToAdd = waterToAdd;
        logger.log(Level.INFO, "AddWaterCommand created to add {0} liters for date: {1}",
                new Object[]{waterToAdd, date});
    }

    /**
     * Executes the command to add water to the daily record in the specified history.
     *
     * @param history The {@code History} object that contains daily records.
     * @return A {@code CommandResult} containing a message indicating the success of the operation.
     * @throws AssertionError if the daily record for the specified date is not found.
     */
    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        assert dailyRecord != null : "Daily record not found";
        dailyRecord.addWaterToRecord(waterToAdd);
        logger.log(Level.INFO, "{0} liters of water added for date: {1}",
                new Object[]{waterToAdd, date});

        return new CommandResult(waterToAdd + " liters of water has been added");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddWaterCommand that)) {
            return false;
        }
        return Objects.equals(waterToAdd, that.waterToAdd) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waterToAdd, date);
    }
}
