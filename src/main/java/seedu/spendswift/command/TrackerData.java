package seedu.spendswift.command;

//@@author kq2003
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the core data structure for tracking categories, expenses, and budgets
 * in the SpendSwift application.
 */
public class TrackerData {
    private List<Category> categories;
    private List<Expense> expenses;
    private Map<Category, Budget> budgets;

    /**
     * Constructs an empty {@code TrackerData} object.
     * Initializes categories, expenses, and budgets as empty collections.
     */
    public TrackerData() {
        this.categories = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.budgets = new HashMap<>();
    }

    /**
     * Retrieves the list of categories being tracked.
     * @return A list of category objects.
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Retrieves the list of expenses being tracked.
     * @return A list of expense objects.
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Retrieves the mapping og categories to their respective budgets.
     * @return A mapping from categories to their budgets..
     */
    public Map<Category, Budget> getBudgets() {
        return budgets;
    }

    /**
     * Updates the list of categories.
     * @param categories The updated list of categories being tracked.
     */
    public void setCategories(List<Category> categories) {
        assert categories != null : "Categories list should not be null";
        this.categories = categories;
    }

    /**
     * Updates the list of expenses.
     * @param expenses The updated list of expenses being tracked.
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        assert expenses != null : "Expenses list should not be null";
    }

    /**
     * Updates the mapping from categories to their budgets.
     * @param budgets The new mapping from categories to their budgets.
     */
    public void setBudgets(Map<Category, Budget> budgets) {
        this.budgets = budgets;
    }
}
