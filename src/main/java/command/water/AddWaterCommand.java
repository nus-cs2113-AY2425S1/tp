package command.water;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import java.time.LocalDate;


public class AddWaterCommand extends WaterCommand {

    protected float waterToAdd;
    protected LocalDate date;

    public AddWaterCommand(float waterToAdd, LocalDate date) {
        this.waterToAdd = waterToAdd;
        this.date = date;
    }

    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        dailyRecord.addWaterToRecord(waterToAdd);

        return new CommandResult(waterToAdd + " has been added");
    }

}
