package command.programme;
import command.Command;
import command.CommandResult;
import programme.ProgrammeList;
import history.History;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        assert pList != null : "Programme list must not be null";

        String result = String.format("Listing programmes: %n%s", pList);

        logger.log(Level.INFO, "ListCommand executed successfully.");

        return new CommandResult(result);
    }
}
