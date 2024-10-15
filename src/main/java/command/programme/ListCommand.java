package command.programme;
import command.Command;
import programme.ProgrammeList;
import core.History;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(ProgrammeList pList, History history){
        return String.format("Listing programmes:%s%n",pList);
    }
}
