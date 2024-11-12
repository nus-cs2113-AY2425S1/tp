package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command that lists all expenses in the expense manager.
 * This command retrieves and displays the current expenses for the user.
 */
public class ListExpenseCommand extends Command {

    private static final Logger LOGGER = LoggerSetup.getLogger();
    private Category category;
    private YearMonth month;

    /**
     * Constructs a ListExpenseCommand with no specified date or category
     */
    public ListExpenseCommand() {
        this.category = null;
        this.month = null;
    }

    /**
     * Constructs a ListExpenseCommand with valid category and month field
     *
     * @param category
     * @param month
     */
    public ListExpenseCommand(Category category, YearMonth month) {
        this.category = category;
        this.month = month;
    }

    /**
     * Constructs a ListExpenseCommand with valid month field
     *
     * @param month
     */
    public ListExpenseCommand(YearMonth month) {
        this.category = null;
        this.month = month;
    }

    /**
     * Constructs a ListExpenseCommand with valid category field
     *
     * @param category
     */
    public ListExpenseCommand(Category category) {
        this.category = category;
        this.month = null;
    }

    /**
     * Checks if the provided command matches the command to list expenses.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list expenses", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("list expenses");
    }

    public Category getCategory() {
        return category;
    }

    public YearMonth getMonth() {
        return month;
    }

    /**
     * Executes the command to list all expenses by invoking the ExpenseManager's method.
     */
    @Override
    public void execute() {
        if (category == null && month == null) {
            LOGGER.log(Level.INFO, "Displaying expenses listed with no Filter");
            ExpenseManager.listExpenses();
        } else if (category == null) {
            LOGGER.info("Displaying expenses listed in the Month: " + month);
            Ui.displayToUser(ExpenseManager.listExpensesWithDate(month));
        } else if (month == null) {
            LOGGER.info("Displaying expenses listed with Category: " + category);
            Ui.displayToUser(ExpenseManager.listExpensesWithCategory(category));
        } else {
            LOGGER.info("Displaying expenses listed with Category: " + category + " and Month: " + month);
            Ui.displayToUser(ExpenseManager.listExpensesWithCategoryAndDate(category,month));
        }
    }
}
