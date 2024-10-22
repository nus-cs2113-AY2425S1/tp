package command.programme;
import command.Command;
import command.CommandResult;

import programme.ProgrammeList;
import programme.Programme;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final int progId;

    public ViewCommand(int progId) {
        this.progId = progId;
        assert progId >= 0 : "progId must not be negative";
    }

    public int getProgId() {
        return progId;
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
