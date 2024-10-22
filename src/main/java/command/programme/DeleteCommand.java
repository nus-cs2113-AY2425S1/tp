package command.programme;

import command.CommandResult;
import history.History;
import programme.Programme;
import programme.ProgrammeList;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "delete";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public DeleteCommand(int progId) {
        super(progId);
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        assert pList != null : "Programme list must not be null";

        Programme programme = pList.deleteProgram(progId);
        assert programme != null : "Programme with ID " + progId + " not found";
        String result = String.format("Deleted programme: %n%s",programme);

        logger.log(Level.INFO, "DeleteCommand executed successfully.");

        return new CommandResult(result);
    }
}
