package command.programme;
import java.util.ArrayList;
import java.util.logging.Level;

import command.CommandResult;
import programme.Day;
import programme.ProgrammeList;
import programme.Programme;
import history.History;

public class CreateCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "create";
    private final String programmeName;
    private final ArrayList<Day> programmeContents;

    public CreateCommand(String programmeName, ArrayList<Day> programmeContents) {
        this.programmeName = programmeName;
        this.programmeContents = programmeContents;
    }

    @Override
    public CommandResult execute(ProgrammeList programmes, History history){
        assert programmes != null : "Programme list must not be null";
        Programme created = programmes.insertProgramme(programmeName, programmeContents);
        assert created != null : "programme must be created";
        logger.log(Level.INFO, "CreateCommand executed successfully.");
        String result = String.format("New programme created: %n%s",created);
        return new CommandResult(result);
    }
}
