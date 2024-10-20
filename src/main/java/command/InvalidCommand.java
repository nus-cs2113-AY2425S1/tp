package command;
import core.Ui;
import programme.ProgrammeList;
import core.History;

public class InvalidCommand extends Command {
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        ui.showMessage("Invalid command.");
    }
}
