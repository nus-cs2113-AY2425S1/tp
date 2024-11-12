package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command for displaying total expenses for a specific year or month.
 */
public class DisplayTotalExpensesCommand extends Command {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private int year;

    /**
     * Constructs a command to display expenses for the specified year.
     *
     * @param year The year for which total expenses are to be displayed.
     */
    public DisplayTotalExpensesCommand(int year) {
        this.year = year;
    }

    /**
     * Checks if the given command matches the expected format for displaying monthly expenses.
     *
     * @param command The command string to check.
     * @return true if the command matches the expected format; false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("display monthly expenses");
    }

    /**
     * Executes the command to display a graph of total expenses for the year
     */
    @Override
    public void execute() {
        LOGGER.log(Level.INFO, "Displaying expense graph");
        Ui.displayToUser("Displaying expense graph for " + year);
        ExpenseManager.displayExpensesOverMonthGraph(year);
    }

    /**
     * Get function to get the message to user.
     *
     * @return Message to User
     */
    public String getFeedbackToUser() {
        return "Displaying expense graph for " + year;
    }
}
