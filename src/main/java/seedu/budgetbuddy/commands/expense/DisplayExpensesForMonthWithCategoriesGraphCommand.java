package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command for displaying a pie chart of expenses categorized by month.
 */
public class DisplayExpensesForMonthWithCategoriesGraphCommand extends Command {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private YearMonth yearMonth;

    /**
     * Constructs a command to display expenses for a specific month with categories.
     *
     * @param yearMonth The year and month for which to display expenses.
     */
    public DisplayExpensesForMonthWithCategoriesGraphCommand(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    /**
     * Checks if the given command is a display expenses with categories command.
     *
     * @param command The command string to check.
     * @return True if the command starts with "display expenses with categories", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("display expenses with categories");
    }

    /**
     * Gets the YearMonth attribute of the class
     *
     * @return YearMonth attribute
     */
    public YearMonth getYearMonth() {
        return this.yearMonth;
    }

    /**
     * Executes the command to display the expenses pie chart for the specified month.
     */
    @Override
    public void execute(){
        LOGGER.log(Level.INFO, "Displaying expense PieChart");
        Ui.displayToUser("Displaying Expenses PieChart for " + yearMonth.toString());
        ExpenseManager.displayExpensesForMonthWithCategoriesGraph(yearMonth);
    }
}

