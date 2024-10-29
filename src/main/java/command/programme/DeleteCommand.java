package command.programme;

import command.CommandResult;
import history.History;
import programme.Programme;
import programme.ProgrammeList;

import java.util.logging.Level;

public class DeleteCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int programmeIndex) {
        super(programmeIndex);
    }

    @Override
    public CommandResult execute(ProgrammeList programmes, History history){
        assert programmes != null : "Programme list must not be null";
        Programme programme = programmes.deleteProgram(programmeIndex);
        assert programme != null : "Programme with ID " + programmeIndex + " not found";
        String result = String.format("Deleted programme: %n%s",programme);
        logger.log(Level.INFO, "DeleteCommand executed successfully.");
        return new CommandResult(result);
    }
}
