package seedu.budgetbuddy.transaction.budget;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Manages the remaining budgets after expenses have been deducted from the budgets.
 * Initializes the remaining budgets based on the current expenses and provides functionality
 * to retrieve and list remaining budgets.
 */
public class RemainingBudgetManager {

    /** Logger to record events and operations for RemainingBudgetManager. */
    private static final Logger LOGGER = LoggerSetup.getLogger();

    /** List of budgets after expenses have been deducted. */
    private ArrayList<Budget> remainingBudgets;

    /**
     * Constructs a new RemainingBudgetManager. Initializes the remaining budgets by copying
     * the existing budgets and deducting the appropriate expenses.
     */
    public RemainingBudgetManager() {
        remainingBudgets = new ArrayList<>();
        copyBudgetManager();
        ArrayList<Expense> expenses = ExpenseManager.getExpenses();
        deductExpensesFromBudget(expenses);
        LOGGER.info("Remaining budgets initialized and updated after deductions.");
    }

    /**
     * Deducts the expenses from the existing budgets based on the list of expenses.
     * For each expense, it attempts to find a matching budget for the given date
     * and category, and deducts the expense amount from the respective budget.
     *
     * @param expenses The list of expenses to be deducted.
     */
    private void deductExpensesFromBudget(ArrayList<Expense> expenses) {
        for (Expense expense : expenses) {
            YearMonth expenseDate = YearMonth.from(expense.getDate());
            Category expenseCategory = expense.getCategory();
            double expenseAmount = expense.getAmount();

            Budget matchingBudget = null;
            matchingBudget = searchForGivenBudget(expenseDate, matchingBudget);
            matchingBudget = createNewBudget(matchingBudget, expenseDate);
            matchingBudget.deductExpense(expenseCategory, expenseAmount);

            LOGGER.info("Deducted " + expenseAmount + " from budget for " + expenseDate
                    + " in category " + expenseCategory);
        }
    }

    /**
     * Creates a new budget for a given date if no matching budget is found.
     *
     * @param matchingBudget The existing matching budget, or null if no budget exists.
     * @param expenseDate The date (YearMonth) of the expense.
     * @return The new or existing budget for the specified date.
     */
    private Budget createNewBudget(Budget matchingBudget, YearMonth expenseDate) {
        assert expenseDate != null : "Expense date cannot be null";
        if (matchingBudget == null) {
            matchingBudget = new Budget(expenseDate);
            remainingBudgets.add(matchingBudget);  // Add the new budget to the list
            LOGGER.info("Created new budget for " + expenseDate + " with initial amount 0.0");
        }
        return matchingBudget;
    }

    /**
     * Searches for an existing budget that matches the specified date.
     *
     * @param expenseDate The date (YearMonth) to search for.
     * @param matchingBudget The budget to match, initially null.
     * @return The matching budget, or null if none is found.
     */
    private Budget searchForGivenBudget(YearMonth expenseDate, Budget matchingBudget) {
        for (Budget budget : remainingBudgets) {
            if (budget.getDate().equals(expenseDate)) {
                matchingBudget = budget;
                LOGGER.info("Found existing budget for date: " + expenseDate);
                break;
            }
        }
        return matchingBudget;
    }

    /**
     * Copies the existing budgets from the BudgetManager to initialize the remainingBudgets.
     */
    private void copyBudgetManager() {
        ArrayList<Budget> budgets = BudgetManager.getBudgets();
        assert budgets != null : "BudgetManager's budgets cannot be null";
        for (Budget budget : budgets) {
            remainingBudgets.add(new Budget(budget));
            LOGGER.info("Copied budget: " + budget);
        }
    }

    /**
     * Lists all remaining budgets after expenses have been deducted.
     * Displays the result to the user through the UI.
     */
    public void listRemainingBudgets() {
        if (remainingBudgets.size() == 0) {
            Ui.displayToUser("No budgets found");
            LOGGER.info("No budgets found");
            return;
        }
        String result = "All budgets after expense deductions:";
        for (Budget budget : remainingBudgets) {
            result += "\n" + budget;
        }
        Ui.displayToUser(result);
        LOGGER.info("Displayed all remaining budgets to user.");
    }

    /**
     * Retrieves the remaining budget for a given date and category.
     *
     * @param date The date (LocalDate) of the expense.
     * @param category The category of the expense.
     * @return A message indicating the remaining budget, or that no budget was found.
     */
    public String getRemainingBudgets(LocalDate date, Category category) {
        YearMonth expenseMonth = YearMonth.from(date);
        assert date != null : "Date cannot be null";
        assert category != null : "Category cannot be null";

        String result = findRemainingAmount(category, expenseMonth);
        if (result != null) {
            return result;
        }

        // If no budget is found for the specified date
        LOGGER.warning("No budget found for " + expenseMonth + ".");
        return "No budget found for " + expenseMonth + ".";
    }

    /**
     * Searches for the remaining budget for the specified date and category.
     * This method checks if a budget exists for the specified month and retrieves the
     * remaining amount for the specified category.
     *
     * @param category The category of the expense.
     * @param expenseMonth The month and year of the expense.
     * @return A string representing the remaining budget for the specified category, or null if no budget is found.
     */
    private String findRemainingAmount(Category category, YearMonth expenseMonth) {
        for (Budget budget : remainingBudgets) {
            if (budget.getDate().equals(expenseMonth)) {
                return processAmount(category, expenseMonth, budget);
            }
        }
        return null;
    }

    /**
     * Processes the remaining amount for the specified category and budget.
     * If the category does not have a budget, it assumes the remaining amount is 0.0.
     *
     * @param category The category for which the remaining budget is being processed.
     * @param expenseMonth The month and year of the expense.
     * @param budget The budget from which the remaining amount will be retrieved.
     * @return A formatted string showing the remaining budget for the category and any warnings if exceeded.
     */
    private static String processAmount(Category category, YearMonth expenseMonth, Budget budget) {
        Double remainingAmount = budget.getCategoryBudgets().get(category);
        if (remainingAmount == null) {
            remainingAmount = 0.00; // If the category does not exist, assume remaining amount is 0
        }
        LOGGER.info("Retrieved remaining budget for " + expenseMonth + " in category " + category
                + ": " + remainingAmount);
        String result = "The remaining budget for " + expenseMonth + " in the " + category
                + " category is: " + String.format("%.2f", remainingAmount);
        if (remainingAmount < 0) {
            result += "\nCaution! You have exceeded your budget!";
        }
        return result;
    }
}
