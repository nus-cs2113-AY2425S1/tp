package command.programme;
import command.Command;

import java.util.ArrayList;

public class CreateCommand extends Command {
    public static final String COMMAND_WORD = "create";
    private String name;
    private ArrayList<ArrayList<String>> contents;

    public CreateCommand(String name, ArrayList<ArrayList<String>> contents) {
        this.name = name;
        this.contents = contents;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        Programme createdProgramme = insertProgramme(name, contents);
        ui.showMsg("New programme created:\n" + Programme.toString());
    }
}
