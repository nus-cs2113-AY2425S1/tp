package command;
import core.Ui;
import programme.ProgrammeList;
import core.History;

public abstract class Command {
    public Command(){}

    public boolean isExit() {
        return false;
    }

    public abstract void execute(Ui ui, ProgrammeList pList, History history);
}
