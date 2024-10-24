package command.meals;

import command.Command;
import command.CommandResult;
import history.History;
import meal.MealList;
import programme.ProgrammeList;
import dailyrecord.DailyRecord;

import java.time.LocalDate;

public abstract class MealCommand extends Command {

    public MealCommand(){}

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute (ProgrammeList pList, History history) {
        return execute(history);
    }
}
