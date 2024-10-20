package command;
import core.Ui;
import programme.ProgrammeList;
import core.History;

import java.util.logging.Level;
import java.util.logging.Logger;


public class InvalidCommand extends Command {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        assert ui != null : "Ui must not be null";
        ui.showMessage("Invalid command.");
        logger.log(Level.INFO, "InvalidCommand executed successfully.");
    }
}
