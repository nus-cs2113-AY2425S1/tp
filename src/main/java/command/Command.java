package command;
import programme.ProgrammeList;
import core.History;

public abstract class Command {
    public Command(){}

    public boolean isExit(){
        return false;
    }

    public abstract String execute(ProgrammeList pList, History history);
}
