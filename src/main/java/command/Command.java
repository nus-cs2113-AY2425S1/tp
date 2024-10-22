package command;
import programme.ProgrammeList;
import history.History;

public abstract class Command {
    public Command(){}

    public abstract CommandResult execute(ProgrammeList pList, History history);
}
