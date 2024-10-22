package command.programme;

import command.Command;
import core.History;
import core.Ui;
import programme.Programme;
import programme.ProgrammeList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final int progId;

    public DeleteCommand(int progId) {
        this.progId = progId;
        assert progId >= 0 : "progId must not be negative";
    }

    public int getProgId() {
        return progId;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        assert pList != null : "Programme list must not be null";
        assert ui != null : "UI must not be null";
        Programme programme = pList.deleteProgram(progId);
        assert programme != null : "Programme with ID " + progId + " not found";
        String result = String.format("Deleted programme: %n%s",programme);
        ui.showMessage(result);
        logger.log(Level.INFO, "DeleteCommand executed successfully.");
    }
}
