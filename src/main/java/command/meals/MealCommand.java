package command.meals;

import command.Command;
import command.CommandResult;
import dailyrecord.DailyRecord;
import history.History;
import meal.MealList;
import programme.ProgrammeList;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MealCommand extends Command {

    private static final Logger logger = Logger.getLogger(MealCommand.class.getName());

    protected LocalDate date;

    public MealCommand(LocalDate date) {
        assert date != null : "Date cannot be null";

        this.date = date;

        logger.log(Level.INFO, "MealCommand initialized with date: {0}", date);
    }

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        logger.log(Level.INFO, "Executing MealCommand with ProgrammeList and History.");
        return execute(history);
    }
}
