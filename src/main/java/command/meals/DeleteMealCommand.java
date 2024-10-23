package command.meals;

import command.CommandResult;
import history.History;
import meal.MealList;
import java.util.Date;

public class DeleteMealCommand extends MealCommand {

    protected final int indexMealToDelete;
    protected final Date date;

    public DeleteMealCommand(int index, Date date) {
        this.indexMealToDelete = index;
        this.date = date;
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);
        meals.deleteMeal(indexMealToDelete);

        return new CommandResult(indexMealToDelete + " has been added");
    }

}


