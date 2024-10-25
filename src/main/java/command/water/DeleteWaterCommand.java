package command.water;

import command.CommandResult;
import history.History;
import water.Water;

import java.time.LocalDate;

public class DeleteWaterCommand extends WaterCommand {
    public static final String COMMAND_WORD = "delete";

    protected int indexWaterToDelete;

    public DeleteWaterCommand(int indexOfWaterToDelete, LocalDate date) {
        super(date);
        this.indexWaterToDelete = indexOfWaterToDelete;
    }

    public CommandResult execute(History history) {
        Water water = getWaterList(history);
        Float waterToBeDeleted = water.deleteWater(indexWaterToDelete);

        return new CommandResult(waterToBeDeleted + " has been added");
    }
}

