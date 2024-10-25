package command.meals;

import command.CommandResult;
import history.History;
import meal.Meal;
import java.time.LocalDate;
import meal.MealList;

public class AddMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "add";

    protected final Meal mealToAdd;

    public AddMealCommand(Meal meal, LocalDate date) {
        super(date);
        this.mealToAdd = meal;
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);
        meals.addMeal(mealToAdd);

        return new CommandResult(mealToAdd.toString() + " has been added");
    }
}
