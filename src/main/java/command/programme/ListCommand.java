package command.programme;
import command.Command;
import core.Ui;
import programme.ProgrammeList;
import core.History;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        assert pList != null : "Programme list must not be null";
        assert ui != null : "Ui must not be null";
        String result = String.format("Listing programmes: %n%s", pList);
        ui.showMessage(result);
        logger.log(Level.INFO, "ListCommand executed successfully.");
    }
}
