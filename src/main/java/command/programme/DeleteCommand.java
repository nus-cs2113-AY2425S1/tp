package command.programme;

import command.Command;
import core.History;
import core.Ui;
import programme.Programme;
import programme.ProgrammeList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int progId;

    public DeleteCommand(int progId) {
        this.progId = progId;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        Programme programme = pList.deleteProgram(progId);
        ui.showMsg("Deleted programme:\n" + programme);
    }
}
