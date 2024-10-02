package command.programme;
import command.Command;

public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public ViewCommand(int progIndex){
        super(progIndex);
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        int progIndex = getTarget();
        String programme = getProgramme(progIndex);
        ui.showMsg("Viewing programme:\n" + programme);
    }
}
