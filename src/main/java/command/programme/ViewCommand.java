package command.programme;
import command.Command;
import core.History;
import programme.ProgrammeList;
import core.Ui;
import programme.Programme;


public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    private final int progId;

    public ViewCommand(int progId) {
        this.progId = progId;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        Programme programme = pList.getProgramme(progId);
        ui.showMsg("Viewing programme:\n" + programme.toString());
    }
}
