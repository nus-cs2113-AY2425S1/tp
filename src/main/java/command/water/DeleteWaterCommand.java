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
        this.indexWaterToDelete = indexOfWaterToDelete;
        this.date = date;
    }

    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        dailyRecord.removeWaterfromRecord(indexWaterToDelete);

        return new CommandResult(indexWaterToDelete + " has been added");
    }
}

