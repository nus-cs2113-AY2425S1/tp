package command;

import core.Ui;
import programme.ProgrammeList;
import core.History;

public class WeeklySummaryCommand extends Command {
    public static final String COMMAND_WORD = "weekly summary";

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history) {
        // Call the method that retrieves the weekly summary from the History class
        String weeklySummary = history.getWeeklySummary();

        // Display the weekly summary using the Ui class
        ui.showMsg("Your weekly workout summary: \n" + weeklySummary);
    }
}