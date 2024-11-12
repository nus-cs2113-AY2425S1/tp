package seedu.budgetbuddy.validators.budget;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.commands.budget.DeductBudgetCommand;
import seedu.budgetbuddy.exceptions.BudgetBuddyException;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Logger;

import static seedu.budgetbuddy.validators.AmountValidator.validateAmount;
import static seedu.budgetbuddy.validators.CategoryValidator.validateCategory;
import static seedu.budgetbuddy.validators.DateValidator.validateYearMonth;

/**
 * Validates commands for deducting budgets.
 */
public class DeductBudgetValidator {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    public static Command processCommand(String command) throws BudgetBuddyException {
        assert command != null : "Command cannot be null";

        if (command.equals("deduct budget")) {
            LOGGER.warning("Attempted to deduct budget without amount.");
            throw new BudgetBuddyException("No amount provided.");
        }

        String trimmedCommand = command.substring("deduct budget ".length());
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
            throw new BudgetBuddyException("Invalid amount: " + amount + ". Must be a positive value.");
        }

        // Validate date
        if (date == null) {
            throw new BudgetBuddyException("Invalid date format. Use m/MM/yyyy.");
        }

        // Check if budget exists for the date
        if (BudgetManager.getBudget(date) == null) {
            throw new BudgetBuddyException("Budget does not exist for the specified date: " + date);
        }

        if (BudgetManager.getBudget(date).getCategoryBudgetAmount(category) <= 0) {
            throw new BudgetBuddyException("Budget's category does not exist for the specified date: " + category
                    + ", " + date + "\n" + "Try deducting from another category.");
        }

        // All validations passed, return the command
        return new DeductBudgetCommand(amount, date, category);
    }
}
