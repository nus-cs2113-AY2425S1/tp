package command.meals;

import command.Command;
import command.CommandResult;
import history.History;
import programme.ProgrammeList;

public abstract class MealCommand extends Command {

    public MealCommand(){}

    public abstract CommandResult execute(History history);

    @Override
    public CommandResult execute (ProgrammeList pList, History history) {
        return execute(history);
    }
}
