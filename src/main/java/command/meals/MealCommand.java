package command.meals;

import command.Command;
import command.CommandResult;
import history.History;
import meal.MealList;
import programme.ProgrammeList;

public abstract class MealCommand extends Command {

    public MealCommand(){};

    // Helper method to get the meal list from history
    protected MealList getMealList(History history) {
        // Record record = history.get(date);
        MealList meals = new MealList();
        // meals = record.getMealList();
        return meals;
    }

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute (ProgrammeList pList, History history) {
        return execute(history);
    }
}
