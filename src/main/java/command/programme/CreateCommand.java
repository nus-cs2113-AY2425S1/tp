package command.programme;
import java.util.ArrayList;
import command.Command;
import core.Ui;
import programme.Day;
import programme.ProgrammeList;
import programme.Programme;
import core.History;



public class CreateCommand extends Command {
    public static final String COMMAND_WORD = "create";
    private String name;
    private ArrayList<Day> contents;

    public CreateCommand(String name, ArrayList<Day> contents) {
        this.name = name;
        this.contents = contents;
    }

    @Override
    public String execute(ProgrammeList pList, History history){
        Programme created = pList.insertProgramme(name, contents);
        return String.format("New programme created:%s%n",created);
    }
}
