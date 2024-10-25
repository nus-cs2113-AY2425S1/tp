package command.water;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import water.Water;

import java.time.LocalDate;

public class DeleteWaterCommand extends WaterCommand {

    protected int indexWaterToDelete;
    protected final LocalDate date;

    public DeleteWaterCommand(int indexOfWaterToDelete, LocalDate date) {
    public DeleteWaterCommand(int indexOfWaterToDelete, LocalDate date) {
        assert indexOfWaterToDelete >= 0;
        assert date != null;
        this.indexWaterToDelete = indexOfWaterToDelete;
        this.date = date;
    }

    public CommandResult execute(History history) {
        Water water = getWaterList(history);
        water.deleteWater(indexWaterToDelete);

        return new CommandResult(indexWaterToDelete + " has been added");
    }
}

