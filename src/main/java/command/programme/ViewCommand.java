package command.programme;
import command.Command;
import core.Ui;
import programme.ProgrammeList;
import programme.Programme;
import core.History;

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

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        assert pList != null : "ProgrammeList must not be null";
        assert ui != null: "Ui must not be null";
        Programme programme = pList.getProgramme(progId);
        assert programme != null : "Programme must not be null";
        String result = String.format("Viewing programme:%s%n",programme);
        ui.showMsg(result);
        logger.log(Level.INFO, "ViewCommand executed successfully.");
    }
}
