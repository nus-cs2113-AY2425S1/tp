package command;
import programme.ProgrammeList;
import core.History;

public class InvalidCommand extends Command {
    @Override
    public String execute(ProgrammeList pList, History history){
        return "Invalid command.";
    }
}
