//@@author Bev-low
package command.water;

import command.CommandResult;
import common.Utils;
import history.DailyRecord;
import history.History;
import water.Water;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to view the water intake for a specific date.
 */
public class ViewWaterCommand extends WaterCommand {
    public static final String COMMAND_WORD = "view";
    private static final Logger logger = Logger.getLogger(ViewWaterCommand.class.getName());

    /**
     * Constructs a ViewWaterCommand for the specified date.
     *
     * @param date The date for which the water intake is to be viewed. Must not be null.
     * @throws AssertionError if date is null.
     */
    public ViewWaterCommand(LocalDate date) {
        super(date);

        logger.log(Level.INFO, "ViewWaterCommand created for date: {0}", date);
    }

    /**
     * Executes the command to view the water intake for the specified date in the provided history.
     *
     * @param history The {@code History} object that contains daily records.
     * @return A {@code CommandResult} containing a message displaying the water intake for the date.
     * @throws AssertionError if the daily record for the specified date is not found.
     */
    public CommandResult execute(History history) {
        String formattedDate = Utils.formatDate(date);

        DailyRecord dailyRecord = history.getRecordByDate(date);
        assert dailyRecord != null : "Daily record not found";
        Water water = dailyRecord.getWaterFromRecord();

        logger.log(Level.INFO, "Retrieved Water record for date: {0}, Water: {1}", new Object[]{date, water});
        return new CommandResult("Water intake for " + formattedDate +  ": \n\n" + water.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewWaterCommand that)) {
            return false;
        }
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
