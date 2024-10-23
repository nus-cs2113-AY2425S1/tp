package command.meals;

import command.CommandResult;
import history.History;
import meal.Meal;
import meal.MealList;
import java.util.Date;

public class AddMealCommand extends MealCommand {

    protected final Meal mealToAdd;
    protected final Date date;

    public AddMealCommand(Meal meal, Date date) {
        super(meal);
        this.mealToAdd = meal;
        this.date = date;
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);
        meals.addMeal(mealToAdd);

        return new CommandResult(mealToAdd + " has been added");
    }
}
