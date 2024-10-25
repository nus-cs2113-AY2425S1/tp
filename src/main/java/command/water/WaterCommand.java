package command.water;

import command.Command;
import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import programme.ProgrammeList;
import water.Water;

public abstract class WaterCommand extends Command {

    public WaterCommand() {
    }

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute (ProgrammeList pList, History history) {
        return execute(history);
    }

    public Water getWaterList(History history) {
        DailyRecord record = history.getRecordByDate(date);
        Water waterList = new Water();
        // waterList = record.getWaterList();
        return waterList;
    }

}

