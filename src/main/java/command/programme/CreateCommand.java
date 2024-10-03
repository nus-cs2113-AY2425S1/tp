package command.programme;
import java.util.ArrayList;
import command.Command;
import core.Ui;
import programme.ProgrammeList;
import programme.Programme;
import core.History;



public class CreateCommand extends Command {
    public static final String COMMAND_WORD = "create";
    private String name;
    private ArrayList<ArrayList<ArrayList<String>>> contents;

    public CreateCommand(String name, ArrayList<ArrayList<ArrayList<String>>> contents) {
        this.name = name;
        this.contents = contents;
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        Programme created = pList.insertProgramme(name, contents);
        ui.showMsg("New programme created:\n" + created.toString());
    }
}
