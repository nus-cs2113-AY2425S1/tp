package command.meals;

import command.CommandResult;
import history.History;
import meal.MealList;

import java.time.LocalDate;

public class ViewMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "view";

    public ViewMealCommand(LocalDate date) {
        super(date);
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);
        return new CommandResult(meals.toString());
    }
}
