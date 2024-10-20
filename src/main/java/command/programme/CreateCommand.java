package command.programme;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import command.Command;
import core.Ui;
import programme.Day;
import programme.ProgrammeList;
import programme.Programme;
import core.History;

public class CreateCommand extends Command {
    public static final String COMMAND_WORD = "create";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final String name;
    private final ArrayList<Day> contents;

    public CreateCommand(String name, ArrayList<Day> contents) {
        this.name = name;
        this.contents = contents;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        assert pList != null : "Programme list must not be null";
        Programme created = pList.insertProgramme(name, contents);
        assert created != null : "programme must be created";
        String result = String.format("New programme created:%s%n",created);
        ui.showMsg(result);
        logger.log(Level.INFO, "CreateCommand executed successfully.");
    }
}
