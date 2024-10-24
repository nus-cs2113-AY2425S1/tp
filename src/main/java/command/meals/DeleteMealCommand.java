package command.meals;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import meal.MealList;
import water.Water;

import java.time.LocalDate;

public class DeleteMealCommand extends MealCommand {

    protected final int indexMealToDelete;
    protected final LocalDate date;

    public DeleteMealCommand(int index, LocalDate date) {
        assert index >= 0;
        this.indexMealToDelete = index;
        this.date = date;
    }

    public CommandResult execute(History history) {
        assert history != null;
        DailyRecord dailyRecord = history.getRecordByDate(date);
        if(dailyRecord == null) {
            dailyRecord = new DailyRecord(new MealList());
        }

        assert dailyRecord != null;

        dailyRecord.deleteMealFromRecord(indexMealToDelete);

        return new CommandResult(indexMealToDelete + " has been added");
    }

}


