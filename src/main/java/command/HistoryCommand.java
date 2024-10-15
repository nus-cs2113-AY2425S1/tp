package command;
import programme.ProgrammeList;
import core.History;


public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    @Override
    public String execute(ProgrammeList pList, History history){
        String result = String.format("Your workout history: %s%n",history);
        return result;
    }
}
