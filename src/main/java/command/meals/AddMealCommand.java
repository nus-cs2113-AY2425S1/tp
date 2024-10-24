package command.meals;

import command.CommandResult;
import history.History;
import meal.Meal;
import java.time.LocalDate;
import dailyrecord.DailyRecord;
import meal.MealList;

public class AddMealCommand extends MealCommand {

    protected final Meal mealToAdd;
    protected final LocalDate date;

    public AddMealCommand(Meal meal, LocalDate date) {
        this.mealToAdd = meal;
        this.date = date;
    }

    public CommandResult execute(History history) {
        assert history != null;
        DailyRecord dailyRecord = history.getRecordByDate(date);
        if(dailyRecord == null) {
            dailyRecord = new DailyRecord(new MealList());
        }
        assert dailyRecord != null;
        dailyRecord.addMealToRecord(mealToAdd);

        return new CommandResult(mealToAdd + " has been added");
    }
}
