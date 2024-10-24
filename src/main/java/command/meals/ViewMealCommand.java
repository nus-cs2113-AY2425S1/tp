package command.meals;

import command.CommandResult;
import history.History;
import meal.Meal;
import meal.MealList;

import java.time.LocalDate;

public class ViewMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "view";

    protected final LocalDate date;

    public ViewMealCommand(LocalDate date) {
        this.date = date;
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);
        StringBuilder output = new StringBuilder();
        int count = 1;

        for (Meal meal : meals.getMeals()) {
            output.append(count).append(": ").append(meal.getName()).append("\n");
            count++;
        }

        return new CommandResult(output.toString());
    }




}
