package command.water;

import command.CommandResult;
import history.History;
import water.Water;

import java.util.Date;

public class AddWaterCommand extends WaterCommand {

    protected float waterToAdd;
    protected Date date;

    public AddWaterCommand(float waterToAdd, Date date) {
        this.waterToAdd = waterToAdd;
        this.date = date;
    }

    public CommandResult execute(History history) {
        Water water = getWaterList(history);
        water.addWater(waterToAdd);

        return new CommandResult(waterToAdd + " has been added");
    }

}
