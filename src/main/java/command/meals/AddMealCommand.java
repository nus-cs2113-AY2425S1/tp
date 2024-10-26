package command.meals;

import command.CommandResult;
import history.History;
import meal.Meal;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import meal.MealList;

public class AddMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "add";
    private static final Logger logger = Logger.getLogger(AddMealCommand.class.getName());

    protected final Meal mealToAdd;

    public AddMealCommand(Meal meal, LocalDate date) {
        super(date);

        assert meal != null : "Meal cannot be null";
        assert date != null : "Date cannot be null";

        this.mealToAdd = meal;

        logger.log(Level.INFO, "AddMealCommand created with meal: {0} for date: {1}",
                new Object[]{meal, date});
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);

        meals.addMeal(mealToAdd);
        logger.log(Level.INFO, "Meal added: {0}", mealToAdd);

        return new CommandResult(mealToAdd.toString() + " has been added");
    }
}
