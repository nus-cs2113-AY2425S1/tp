package command.programme;

import command.CommandResult;

import programme.ProgrammeList;
import programme.Programme;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "view";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public ViewCommand(int progId) {
        super(progId);
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        assert pList != null : "ProgrammeList must not be null";

        Programme programme = pList.getProgramme(progId);
        assert programme != null : "Programme must not be null";
        String result = String.format("Viewing programme: %n%s",programme);
        logger.log(Level.INFO, "ViewCommand executed successfully.");
        return new CommandResult(result);
    }
}
