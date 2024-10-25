package command.water;

import command.Command;
import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import programme.ProgrammeList;
import water.Water;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class WaterCommand extends Command {

    private static final Logger logger = Logger.getLogger(WaterCommand.class.getName());

    protected LocalDate date;

    public WaterCommand(LocalDate date) {
        assert date != null : "Date cannot be null";

        this.date = date;

        logger.log(Level.INFO, "WaterCommand initialized with date: {0}", date);
    }

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        logger.log(Level.INFO, "Executing WaterCommand with ProgrammeList and History.");
        return execute(history);
    }

    public Water getWaterList(History history) {
        logger.log(Level.INFO, "Retrieving Water record for date: {0}", date);

        DailyRecord record = history.getRecordByDate(date);
        if (record == null) {
            record = new DailyRecord(new Water());
            logger.log(Level.INFO, "No DailyRecord found for date {0}, creating a new one.", date);
        }

        Water water = record.getWater();
        logger.log(Level.INFO, "Retrieved Water: {0}", water);

        return water;
    }
}
