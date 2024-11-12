package seedu.budgetbuddy.commands.expense;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;

/**
 * Represents a command to breakdown all expenses in terms of category to allow the user to
 * know where they are spending their money.
 */
public class BreakdownExpensesCommand extends Command {
    /**
     * Verifies that the user input starts with "display expenses"
     * @param command User input command
     * @return true if command starts with "breakdown expenses", false otherwise.
     */
    public static boolean isCommand(String command){
        return command.equals("breakdown expenses");
    }

    /**
     * Executes the command to display expenses by category breakdown, and prints the output to the user.
     */
    public void execute(){
        Ui.displayToUser(ExpenseManager.breakdownExpensesByCategory());
    }
}
