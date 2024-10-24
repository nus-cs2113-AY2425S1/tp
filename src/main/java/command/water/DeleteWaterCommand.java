package command.water;

import command.CommandResult;
import history.History;
import water.Water;

import java.util.Date;

public class DeleteWaterCommand extends WaterCommand {

    protected int indexWaterToDelete;
    protected final Date date;

    public DeleteWaterCommand(int indexOfWaterToDelete, Date date) {
        this.indexWaterToDelete = indexOfWaterToDelete;
        this.date = date;
    }

    public CommandResult execute(History history) {
        Water water = getWaterList(history);
        water.deleteWater(indexWaterToDelete);

        return new CommandResult(indexWaterToDelete + " has been added");
    }
}

