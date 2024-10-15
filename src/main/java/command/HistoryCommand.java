package command;
import core.History;
import programme.ProgrammeList;
import core.Ui;


public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        ui.showMsg("Your workout history: \n" + history.toString());
    }
}
