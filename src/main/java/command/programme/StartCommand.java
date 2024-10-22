package command.programme;

import command.CommandResult;
import programme.ProgrammeList;
import programme.Programme;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StartCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "start";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public StartCommand(int progId) {
        super(progId);
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
