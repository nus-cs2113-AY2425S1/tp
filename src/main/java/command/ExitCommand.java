package command;
import core.Ui;
import programme.ProgrammeList;
import core.History;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        logger.log(Level.INFO, "ExitCommand executed.");
    }
}
