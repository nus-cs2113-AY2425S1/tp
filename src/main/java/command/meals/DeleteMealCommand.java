package command.meals;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import meal.MealList;

import java.time.LocalDate;

public class DeleteMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "delete";

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
        MealList meals = dailyRecord.getMealList();
        String mealToDeleteName = meals.deleteMeal(indexMealToDelete).toString();

        return new CommandResult( mealToDeleteName + " has been deleted");
    }

}
