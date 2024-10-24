package command.water;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import water.Water;

import java.time.LocalDate;


public class AddWaterCommand extends WaterCommand {

    protected float waterToAdd;
    protected LocalDate date;

    public AddWaterCommand(float waterToAdd, LocalDate date) {
        assert waterToAdd > 0;
        assert date != null;
        this.waterToAdd = waterToAdd;
        this.date = date;
    }

    public CommandResult execute(History history) {
        assert history != null;
        DailyRecord dailyRecord = history.getRecordByDate(date);
        if(dailyRecord == null) {
            dailyRecord = new DailyRecord(new Water());
        }
        assert dailyRecord != null;
        dailyRecord.addWaterToRecord(waterToAdd);

        return new CommandResult(waterToAdd + " has been added");
    }

}
