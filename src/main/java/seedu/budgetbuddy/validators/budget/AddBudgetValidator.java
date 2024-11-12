package seedu.budgetbuddy.validators.budget;

import seedu.budgetbuddy.commands.budget.AddBudgetCommand;
import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.CategoryValidator.validateCategory;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates commands for adding budgets.
 */
public class AddBudgetValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    public static Command processCommand(String command) throws BudgetBuddyException {
        assert command != null : "Command cannot be null";

        if (command.equals("add budget")) {
            LOGGER.warning("Attempted to add budget without amount.");
            throw new BudgetBuddyException("No amount provided.");
        }

        String trimmedCommand = command.substring("add budget ".length());
        String[] parts = trimmedCommand.split(" ");

        // Initialize default values
        double amount = 0; // invalid amount initially
        YearMonth date = YearMonth.now();
        Category category = Category.OTHERS;

        // Process parts to extract details
        for (String part : parts) {
            if (part.startsWith("a/")) {
                amount = validateAmount(part);
                if (amount == -1) {
                    throw new BudgetBuddyException("Invalid amount format.");
                }
            } else if (part.startsWith("m/")) {
                date = validateYearMonth(part);
            } else if (part.startsWith("c/")) {
                category = validateCategory(part);
            } else {
                throw new BudgetBuddyException("Unrecognised input: '" + part + "'. Please check the command format.");
            }
        }

        // Validate amount
        if (amount <= 0) {
            throw new BudgetBuddyException("Invalid amount: " + amount + ". Amount must be a positive value.");
        }

        // Validate date
        if (date == null) {
            throw new BudgetBuddyException("Invalid date format. Use m/MM/yyyy.");
        }

        // All validations passed, return the command
        return new AddBudgetCommand(amount, date, category);
    }
}
