package command;
import core.History;
import programme.ProgrammeList;
import core.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        ui.showMsg("Invalid command.");
    }
}
