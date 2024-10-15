package command.programme;
import command.Command;
import programme.ProgrammeList;
import programme.Programme;
import core.History;

public class StartCommand extends Command {
    public static final String COMMAND_WORD = "start";
    private int progId;

    public StartCommand(int progId) {
        this.progId = progId; }

    @Override
    public String execute( ProgrammeList pList, History history){
        Programme startedProgramme = pList.startProgramme(progId);
        return String.format("Ok! Started Programme:%s%n" + startedProgramme);
    }
}
