package seedu.budgetbuddy.commands.budget;

import seedu.budgetbuddy.commands.Command;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.budget.Budget;
import seedu.budgetbuddy.transaction.budget.BudgetManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.logging.Logger;

/**
 * Represents a command to deduct a budget for a specific month and year.
 */
public class DeductBudgetCommand extends Command {
    private static final Logger LOGGER = LoggerSetup.getLogger();
    private double amount;
    private YearMonth date;
    private Category category;

    /**
     * Constructs a DeductBudgetCommand with the specified amount and date.
     *
     * @param amount The amount of the budget to be deducted.
     * @param date The YearMonth representing the month and year for the budget.
     */
    public DeductBudgetCommand(double amount, YearMonth date, Category category) {
        assert amount >= 0 : "Amount must be non-negative";
        assert date != null : "Date cannot be null";
        assert category != null : "Category cannot be null";
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    /**
     * Returns the amount associated with the budget. Useful for unit testing.
     *
     * @return The amount as a double value.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the date associated with the budget. Useful for unit testing.
     *
     * @return The date as a YearMonth object.
     */
    public YearMonth getDate() {
        return date;
    }

    /**
     * Returns the category associated with the budget. Useful for unit testing.
     *
     * @return The category as a Category object.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Checks if the given command string starts with "deduct budget".
     *
     * @param command The command string entered by the user.
     * @return true if the command starts with "deduct budget", false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.startsWith("deduct budget");
    }

    /**
     * Executes the DeductBudgetCommand by checking if a budget already exists for the specified month and year.
     * If a budget exists, the specified amount is deducted from the existing budget.
     * If no budget exists, the method simply returns, as the exception is handled in the DeductBudgetValidator.
     */
    @Override
    public void execute() {
        Budget existingBudget = BudgetManager.getBudget(date);
        assert existingBudget != null;
        existingBudget.deductAmount(category, amount);
        LOGGER.info("Deducted " + amount + " from budget for date: " + date);
    }
}
