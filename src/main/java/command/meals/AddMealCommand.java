package command.meals;

import command.CommandResult;
import history.History;
import meal.Meal;
import java.time.LocalDate;
import dailyrecord.DailyRecord;

public class AddMealCommand extends MealCommand {

    protected final Meal mealToAdd;
    protected final LocalDate date;

    public AddMealCommand(Meal meal, LocalDate date) {
        this.mealToAdd = meal;
        this.date = date;
    }

    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        dailyRecord.addMealList(mealToAdd);

        return new CommandResult(mealToAdd + " has been added");
    }
}
