package seedu.budgetbuddy.commands.saving;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.saving.SavingsManager;

/**
 * Represents a command that displays the savings of the user.
 */
public class DisplaySavingsCommand extends Command{
    private boolean byMonth;

    /**
     * Constructs a new DisplaySavingsCommand object.
     * @param byMonth true if the user wishes to display savings by month, otherwise false.
     */
    public DisplaySavingsCommand(boolean byMonth) {
        this.byMonth = byMonth;
    }

    /**
     * Checks if the user command matches the display savings command.
     * @param command Command by user.
     * @return true if command starts with "display savings", otherwise false.
     */
    public static boolean isCommand(String command){
        return command.startsWith("display savings");
    }

    /**
     * Executes command to display savings to user, either by month or in total.
     */
    public void execute(){
        if (byMonth){
            Ui.displayToUser(SavingsManager.displayTotalSavingsByMonth());
        } else{
            Ui.displayToUser(SavingsManager.displayTotalSavings());
        }
    }
}
