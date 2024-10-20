package command;

import core.History;
import core.Ui;
import programme.ProgrammeList;

public class TestCommand extends Command {

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history) {
        ui.showMsg("Test Command Executed");
    }
}
