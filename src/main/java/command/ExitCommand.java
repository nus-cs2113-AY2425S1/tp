package command;
import core.Ui;
import programme.ProgrammeList;
import core.History;


public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){}
}
