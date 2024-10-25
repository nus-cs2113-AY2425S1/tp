package command.water;

import command.Command;
import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import programme.ProgrammeList;
import water.Water;

import java.time.LocalDate;

public abstract class WaterCommand extends Command {

    protected LocalDate date;

    public WaterCommand(LocalDate date) {
        this.date = date;
    }

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute (ProgrammeList pList, History history) {
        return execute(history);
    }

    public Water getWaterList(History history) {
        DailyRecord record = history.getRecordByDate(date);
        if(record == null) {
            record = new DailyRecord(new Water());
        }
        return record.getWater();
    }

}

