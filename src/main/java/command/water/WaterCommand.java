package command.water;

import command.Command;
import command.CommandResult;
import history.History;
import programme.ProgrammeList;
import water.Water;

public abstract class WaterCommand extends Command {

    public WaterCommand() {}

    public Water getWaterList(History history) {
        // Record record = history.get(date);
        Water waterList = new Water();
        // waterList = record.getWaterList();
        return waterList;
    }

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute (ProgrammeList pList, History history) {
        return execute(history);
    }

}

