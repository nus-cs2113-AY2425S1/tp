//@@author BevLow
package command.water;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import water.Water;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewWaterCommand extends WaterCommand {
    public static final String COMMAND_WORD = "view";
    private static final Logger logger = Logger.getLogger(ViewWaterCommand.class.getName());

    public ViewWaterCommand(LocalDate date) {
        super(date);

        logger.log(Level.INFO, "ViewWaterCommand created for date: {0}", date);
    }

    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        assert dailyRecord != null : "Daily record not found";
        Water water = dailyRecord.getWater();

        logger.log(Level.INFO, "Retrieved Water record for date: {0}, Water: {1}", new Object[]{date, water});
        return new CommandResult(water.toString());
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
