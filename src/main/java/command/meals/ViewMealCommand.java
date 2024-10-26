package command.meals;

import command.CommandResult;
import history.History;
import meal.MealList;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "view";
    private static final Logger logger = Logger.getLogger(ViewMealCommand.class.getName());

    public ViewMealCommand(LocalDate date) {
        super(date);

        logger.log(Level.INFO, "ViewMealCommand created for date: {0}", date);
    }

    public CommandResult execute(History history) {
        logger.log(Level.INFO, "Executing ViewMealCommand for date: {0}", date);

        MealList meals = getMealList(history);

        logger.log(Level.INFO, "Retrieved MealList for date {0}: {1}", new Object[]{date, meals});

        return new CommandResult(meals.toString());
    }
}
