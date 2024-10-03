package command.programme;
import command.Command;
import core.Ui;
import programme.ProgrammeList;
import programme.Programme;
import core.History;


public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public ViewCommand(int progIndex){
        super(progIndex);
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        int progIndex = getTarget();
        Programme programme = pList.getProgramme(progIndex);
        ui.showMsg("Viewing programme:\n" + programme.toString());
    }
}
