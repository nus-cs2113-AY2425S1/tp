package command.programme;
import command.Command;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        ui.showMsg("Listing programmes:\n" + pList.toString());
    }
}
