package command.meals;

import command.CommandResult;
import history.History;
import meal.Meal;
import meal.MealList;

import java.time.LocalDate;

public class AddMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "add";

    protected final Meal mealToAdd;
    protected final LocalDate date;

    public AddMealCommand(Meal meal, LocalDate date) {
        this.mealToAdd = meal;
        this.date = date;
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);

        return new CommandResult(mealToAdd.getName() + " has been added");
    }
}
