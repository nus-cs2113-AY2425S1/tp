package seedu.budgetbuddy.commands.income;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.income.IncomeManager;

import java.time.YearMonth;

/**
 * Represents a command that lists all incomes in the income manager.
 * This command retrieves and displays the current incomes for the user.
 */
public class ListIncomeCommand extends Command {

    private YearMonth month;

    /**
     * Constructs a ListIncomeCommand with no specified date or category
     */
    public ListIncomeCommand() {
        this.month = null;
    }

    /**
     * Constructs a ListIncomeCommand with valid month field
     *
     * @param month
     */
    public ListIncomeCommand(YearMonth month) {
        this.month = month;
    }

    /**
     * Checks if the provided command matches the command to list incomes.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list incomes", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("list incomes");
    }

    /**
     * Executes the command to list all incomes by invoking the IncomeManager's method.
     */
    @Override
    public void execute() {
        if (month == null) {
            IncomeManager.listIncomes();
        } else {
            IncomeManager.listIncomeWithMonth(month);
        }
    }
}
