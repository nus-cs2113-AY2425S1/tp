package command;
import programme.ProgrammeList;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        logger.log(Level.INFO, "ExitCommand executed.");
        return new CommandResult("Exiting BuffBuddy...");
    }
}
