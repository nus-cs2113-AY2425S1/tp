package command.meals;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import java.time.LocalDate;

public class DeleteMealCommand extends MealCommand {

    protected final int indexMealToDelete;
    protected final LocalDate date;

    public DeleteMealCommand(int index, LocalDate date) {
        this.indexMealToDelete = index;
        this.date = date;
    }

    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        dailyRecord.deleteMealFromRecord(indexMealToDelete);

        return new CommandResult(indexMealToDelete + " has been added");
    }

}


