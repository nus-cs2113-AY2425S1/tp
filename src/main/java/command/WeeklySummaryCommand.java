package command;
import programme.ProgrammeList;
import history.History;

public class WeeklySummaryCommand extends Command {
    public static final String COMMAND_WORD = "weeklysummary";

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        // Call the method that retrieves the weekly summary from the History class
        String weeklySummary = history.getWeeklySummary();
        return new CommandResult("Your weekly workout summary: \n" + weeklySummary);
    }
}

