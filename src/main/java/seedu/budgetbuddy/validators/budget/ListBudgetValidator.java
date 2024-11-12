package seedu.budgetbuddy.validators.budget;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.budget.ListBudgetCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;

import java.time.YearMonth;

import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates user commands for listing budgets.
 */
public class ListBudgetValidator {

    /**
     * Processes the command string to determine if it is valid for listing budgets.
     * If valid, it returns a ListBudgetCommand with the parsed date.
     *
     * @param command The command string entered by the user.
     * @return A ListBudgetCommand if valid; otherwise, an IncorrectCommand.
     */
    public static Command processCommand(String command) throws BudgetBuddyException {
        if (command.equals("list budgets")) {
            return new ListBudgetCommand(null); // No date provided, list all budgets
        }

        // Check for date in the command
        String trimmedCommand = command.substring("list budgets ".length()).trim();
        YearMonth date = null;

        if (!trimmedCommand.isEmpty()) {
            date = validateYearMonth(trimmedCommand);
            if (date == null) {
                throw new BudgetBuddyException("Invalid format. Use 'list budgets [m/MM/yyyy]'.");
            }
        }

        return new ListBudgetCommand(date); // Return command with date
    }
}
