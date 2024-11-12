package seedu.budgetbuddy.commands.budget;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.budget.RemainingBudgetManager;

/**
 * Represents a command to list all budgets managed by the BudgetManager.
 * This command retrieves and displays the current budgets for the user.
 */
public class ListRemainingBudgetCommand extends Command {
    /**
     * Checks if the provided command matches the command to list budget.
     *
     * @param command The command to be checked.
     * @return True if the command matches "list budget", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.equals("list remaining budget");
    }

    /**
     * Executes the command to list all budgets by invoking the BudgetManager's method.
     */
    @Override
    public void execute() {
        new RemainingBudgetManager().listRemainingBudgets();
    }
}
