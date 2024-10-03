package command;
import core.Ui;
import programme.ProgrammeList;
import core.History;

public abstract class Command {
    private int target;

    public Command(int target) {
        this.target = target;
    }

    public Command(){}

    protected int getTarget() {
        return target;
    }

    public boolean isExit(){
        return false;
    }

    public abstract void execute(Ui ui, ProgrammeList pList, History history);
}
