package command.meals;

import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
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
        DailyRecord dailyRecord = history.getRecordByDate(date);
        assert dailyRecord != null : "Daily record not found";
        dailyRecord.deleteMealFromRecord(indexMealToDelete);

        return new CommandResult("Meal index: " + indexMealToDelete + " has been deleted");
    }
}
