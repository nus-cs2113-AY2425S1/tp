package command;
import programme.ProgrammeList;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;


public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        assert history != null : "History must not be null";

        String result = String.format("Your workout history: %s%n",history);
        logger.log(Level.INFO, "HistoryCommand executed successfully.");
        return new CommandResult(result);
    }
}

