package command;
import core.History;
import core.Ui;
import programme.ProgrammeList;


public abstract class Command {
    public Command(){}

    public boolean isExit(){
        return false;
    }

    public abstract void execute(Ui ui, ProgrammeList pList, History history);
}
