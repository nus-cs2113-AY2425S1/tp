package command.meals;

import command.CommandResult;
import history.History;
import meal.MealList;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteMealCommand extends MealCommand {
    public static final String COMMAND_WORD = "delete";
    private static final Logger logger = Logger.getLogger(DeleteMealCommand.class.getName());

    protected final int indexMealToDelete;

    public DeleteMealCommand(int index, LocalDate date) {
        super(date);

        assert index >= 0 : "Index to delete cannot be negative";

        this.indexMealToDelete = index;

        logger.log(Level.INFO, "DeleteMealCommand created for index: {0} on date: {1}",
                new Object[]{index, date});
    }

    public CommandResult execute(History history) {
        MealList meals = getMealList(history);

        logger.log(Level.INFO, "Attempting to delete meal at index: {0}", indexMealToDelete);

        String mealToDeleteName;
        try {
            mealToDeleteName = meals.deleteMeal(indexMealToDelete).toString();
            logger.log(Level.INFO, "Successfully deleted meal: {0}", mealToDeleteName);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Invalid index for deletion: {0}", indexMealToDelete);
            throw new IndexOutOfBoundsException("Index " + indexMealToDelete + " is out of bounds for the meal list.");
        }

        return new CommandResult(mealToDeleteName + " has been deleted");
    }
}
