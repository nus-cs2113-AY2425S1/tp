package seedu.budgetbuddy.transaction.budget;

import seedu.budgetbuddy.Ui;
import seedu.budgetbuddy.transaction.Category;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a budget for a specific month and year.
 * A budget tracks a specified amount of money for each category and calculates the total budget for the month.
 * Provides methods to add category-specific amounts, adjust the budget, and retrieve details.
 */
public class Budget {

    private double amount;
    private YearMonth date;
    private Map<Category, Double> categoryBudgets;
    private double totalMonthlyBudget;

    /**
     * Constructs a Budget object with the specified amount and date.
     *
     * @param date The YearMonth representing the month and year for the budget.
     */
    public Budget(YearMonth date) {
        assert amount >= 0 : "Initial amount cannot be negative";
        this.date = date;
        this.categoryBudgets = new HashMap<>();
    }

    /**
     * Constructs a Budget object by copying another Budget object.
     *
     * @param other The Budget object to copy from.
     */
    public Budget(Budget other) {
        this.amount = other.amount; // Copy the original amount
        this.date = other.date; // Copy the date
        this.categoryBudgets = new HashMap<>(other.categoryBudgets); // Deep copy of the category budgets
        this.totalMonthlyBudget = other.totalMonthlyBudget; // Copy the total monthly budget
    }


    /**
     * Adds or updates the budget amount for a specific category.
     * If the category already has a budget, the new amount is added to the existing amount.
     *
     * @param category The category to which the amount should be allocated.
     * @param amount   The amount to be added for the specified category.
     */
    public void addAmount(Category category, double amount) {
        categoryBudgets.put(category, categoryBudgets.getOrDefault(category, 0.0) + amount);
        updateTotalBudget();
    }

    /**
     * Deducts an amount from the budget for a specific category.
     * If the deducted amount brings the category's budget to zero or below, it is removed.
     *
     * @param category        The category from which the amount should be deducted.
     * @param deductedAmount  The amount to be deducted.
     */
    public void deductAmount(Category category, double deductedAmount) {
        assert deductedAmount >= 0 : "Amount to deduct cannot be negative";
        double currentAmount = categoryBudgets.getOrDefault(category, 0.0);

        // Deduct the amount or remove the category if the budget goes to zero or below
        if (currentAmount - deductedAmount <= 0) {
            categoryBudgets.remove(category);
        } else {
            categoryBudgets.put(category, currentAmount - deductedAmount);
        }
        if (categoryBudgets.isEmpty()) {
            BudgetManager.deleteBudget(this);
        } else {
            updateTotalBudget();
            Ui.displayBudgetTransactionMessage(toString(), BudgetManager.getNumberOfBudgets());
        }
    }

    /**
     * Deducts an amount from the budget for a specific category.
     * This is used by RemainingBudgetManager
     *
     * @param category        The category from which the amount should be deducted.
     * @param deductedAmount  The amount to be deducted.
     */
    public void deductExpense(Category category, double deductedAmount) {
        assert deductedAmount >= 0 : "Amount to deduct cannot be negative";
        double currentAmount = categoryBudgets.getOrDefault(category, 0.0);

        // Deduct the amount and allow the category to go negative
        categoryBudgets.put(category, currentAmount - deductedAmount);

        // Update the total budget
        updateTotalBudget();
    }

    /**
     * Updates the total monthly budget by summing up all the category budgets.
     */
    private void updateTotalBudget() {
        totalMonthlyBudget = categoryBudgets.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Retrieves the total monthly budget, which is the sum of all category budgets for the month.
     *
     * @return The total budget for the month.
     */
    public double getTotalMonthlyBudget() {
        return totalMonthlyBudget;
    }

    /**
     * Retrieves the Hashmap of category budgets.
     *
     * @return A Map where the keys are Category objects and the values represent the budget amounts for each category.
     */
    public Map<Category, Double> getCategoryBudgets() {
        return categoryBudgets;
    }

    /**
     * Retrieves the budget amount for a specific category.
     *
     * @param category The category for which the budget amount is to be retrieved.
     * @return The budget amount for the given category, or 0.0 if no budget
     *         has been set for the specified category.
     */
    public double getCategoryBudgetAmount(Category category) {
        return categoryBudgets.getOrDefault(category, 0.0);
    }

    /**
     * Gets the date of the budget.
     *
     * @return The YearMonth representing the budget date.
     */
    public YearMonth getDate() {
        return date;
    }

    /**
     * Returns a string representation of the budget, including its amount and date.
     *
     * @return A string in the format "Amount: {amount}  Date: {date}".
     */
    @Override
    public String toString() {
        String output = "Total Monthly Budget: " + String.format("%.2f", totalMonthlyBudget);
        output += "  Date: " + date + "\n";

        // Sort categoryBudgets by key (category name) and build the string with rounded budget values
        String sortedCategories = categoryBudgets.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())  // Sort by category names
                .map(entry -> entry.getKey() + "=" + String.format("%.2f", entry.getValue()))
                .reduce((c1, c2) -> c1 + ", " + c2)  // Join entries with ", "
                .orElse("");  // Handle the case when the map is empty

        output += " Category: {" + sortedCategories + "}";
        return output;
    }
}
