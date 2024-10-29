package command.programme;

import command.CommandResult;
import programme.ProgrammeList;
import programme.Programme;
import history.History;

import java.util.logging.Level;

public class StartCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "start";

    public StartCommand(int programmeIndex) {
        super(programmeIndex);
    }

    @Override
    public CommandResult execute(ProgrammeList programmes, History history){
        assert programmes != null : "Programme list must not be null";

        Programme started = programmes.startProgramme(programmeIndex);
        assert started != null : "Programme must not be null";

        String result = String.format("Ok! Started Programme: %n%s",started);
        logger.log(Level.INFO, "StartCommand executed successfully.");
        return new CommandResult(result);
    }
}
