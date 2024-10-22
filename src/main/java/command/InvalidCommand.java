package command;
import programme.ProgrammeList;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;


public class InvalidCommand extends Command {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        logger.log(Level.INFO, "InvalidCommand executed successfully.");
        return new CommandResult("Invalid command.");
    }
}
