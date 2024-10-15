package command;


import core.History;
import programme.ProgrammeList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public String execute(ProgrammeList pList, History history){
        return "";
    }
}
