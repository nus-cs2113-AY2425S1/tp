package command.programme;
import command.Command;
import core.Ui;
import programme.ProgrammeList;
import programme.Programme;
import core.History;

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

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        assert pList != null : "Programme list must not be null";
        assert ui != null : "UI must not be null";
        Programme started = pList.startProgramme(progId);
        assert started != null : "Programme must not be null";
        String result = String.format("Ok! Started Programme: %n%s",started);
        ui.showMessage(result);
        logger.log(Level.INFO, "StartCommand executed successfully.");
    }
}
