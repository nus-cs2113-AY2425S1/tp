package command.programme;
import command.Command;
import command.CommandResult;
import programme.ProgrammeList;
import programme.Programme;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StartCommand extends Command {
    public static final String COMMAND_WORD = "start";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final int progId;

    public StartCommand(int progId) {
        this.progId = progId;
        assert progId >= 0 : "progId must not be negative";
    }

    public int getProgId() {
        return progId;
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        assert pList != null : "Programme list must not be null";

        Programme started = pList.startProgramme(progId);
        assert started != null : "Programme must not be null";
        String result = String.format("Ok! Started Programme: %n%s",started);

        logger.log(Level.INFO, "StartCommand executed successfully.");

        return new CommandResult(result);
    }
}
