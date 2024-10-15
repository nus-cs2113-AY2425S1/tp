package command.programme;
import command.Command;
import core.History;
import programme.ProgrammeList;
import core.Ui;
import programme.Programme;

public class StartCommand extends Command {
    public static final String COMMAND_WORD = "start";
    private final int progId;

    public StartCommand(int progId) {
        this.progId = progId;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        Programme startedProgramme = pList.startProgramme(progId);
        ui.showMsg("Ok! Started Programme:\n" + startedProgramme.toString());
    }
}
