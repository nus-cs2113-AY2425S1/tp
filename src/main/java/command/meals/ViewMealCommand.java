package command.meals;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import meal.MealList;

import java.time.LocalDate;

public class ViewMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "view";

    protected final LocalDate date;

    public ViewMealCommand(LocalDate date) {
        this.date = date;
    }

    public CommandResult execute(History history) {
        DailyRecord dailyRecord = history.getRecordByDate(date);
        MealList meals = dailyRecord.getMealList();
        return new CommandResult(meals.toString());
    }




}
