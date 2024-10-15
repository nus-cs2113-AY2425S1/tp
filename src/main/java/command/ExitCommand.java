package command;
import core.History;
import programme.ProgrammeList;
import core.Ui;


public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){}
}
