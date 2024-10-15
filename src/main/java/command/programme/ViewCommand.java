package command.programme;
import command.Command;
import programme.ProgrammeList;
import programme.Programme;
import core.History;


public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    private int progId;

    public ViewCommand(int progId) {
        this.progId = progId; }

    @Override
    public String execute(ProgrammeList pList, History history){
        Programme programme = pList.getProgramme(progId);
        return String.format("Viewing programme:%s%n",programme);
    }
}
