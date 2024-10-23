package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

public class ExpenseTracker {
    private List<Category> categories;
    private List<Expense> expenses;
    private Map<String, Budget> budgets = new HashMap<>();
    private boolean autoResetEnabled;
    private int lastResetMonth;

    public ExpenseTracker() {
        this.categories = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.autoResetEnabled = false;
        this.lastResetMonth = -1;
    }

    public void toggleAutoReset() {
        autoResetEnabled = !autoResetEnabled;
        System.out.println("Automatic budget reset is now " + (autoResetEnabled ? "ON" : "OFF") + ".");
    }

    // Method to reset budgets for each category
    private void resetBudgets() {
        for (Budget budget : budgets.values()) {
            // Resetting the budget logic can be adjusted as needed
            budget.setLimit(budget.getLimit()); // For now, just maintaining the same limit
        }
        System.out.println("Budgets have been reset for all categories.");
    }

    // Method to check if it's a new month and reset budgets if enabled
    public void checkAndResetBudgets() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);

        if (autoResetEnabled && currentMonth != lastResetMonth) {
            resetBudgets();
            lastResetMonth = currentMonth;  // Update last reset month
        }
    }

    // Call this method to manage monthly reset
    public void manageMonthlyReset() {
        checkAndResetBudgets();
    }

    private String formatInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public void addExpense(String name, double amount, String category) {
        String formattedCategoryName = formatInput(category.trim());
        Expense newExpense = new Expense(name, amount, formattedCategoryName);
        expenses.add(newExpense);
        System.out.println("Added" + newExpense);
    }

    public void addCategory(String categoryName) {
        String trimmedCategoryName = categoryName.substring("add category".length()).trim();
        if (trimmedCategoryName.isEmpty()) {
            System.out.println("Category name is empty!");
            return;
        }

        String formattedCategoryName = formatInput(trimmedCategoryName.trim());
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                System.out.println("Category '" + formattedCategoryName + "' already exists!");
                return;
            }
        }
        Category newCategory = new Category(formattedCategoryName);
        categories.add(newCategory);
        System.out.println("Category '" + newCategory + "' added successfully.");
    }

    public void deleteExpense(int expenseIndex) {
        if (expenseIndex < 0 || expenseIndex >= expenses.size()) {
            System.out.println("Invalid index. Unable to delete expense.");
            return;
        }
        Expense removedExpense = expenses.remove(expenseIndex);
        System.out.println("Deleted expense: " + removedExpense);
    }


    private void tagExpenseHelper(int expenseIndex, String categoryName) {
        if (expenseIndex < 0 || expenseIndex >= expenses.size()) {
            System.out.println("Invalid index");
            return;
        }
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                Expense expense = expenses.get(expenseIndex);
                expense.setCategory(categoryName);
                System.out.println("Tagged expense: " + expense);
                return;
            }
        }
        System.out.println("Category '" + categoryName + "' does not exist.");
    }

    public void tagExpense(String input) {
        try {
            String[] parts = input.split(" ");
            int expenseIndex = -1;
            String category = null;
            for (String part : parts) {
                if (part.startsWith("e/")) {
                    expenseIndex = Integer.parseInt(part.substring(2).trim()) - 1; // 1-based index
                } else if (part.startsWith("c/")) {
                    category = part.substring(2).trim();
                }
            }
            if (expenseIndex < 0 || category == null) {
                System.out.println("Invalid input! Please provide an expense index and category.");
                return;
            }
            tagExpenseHelper(expenseIndex, category);
        } catch (Exception e) {
            System.out.println("Error parsing the input. Please use correct format for tag expense commands.");
        }
    }

    /**
     * Displays all expenses grouped by their respective categories.
     *
     * This method first checks if there are any expenses in the tracker. If no
     * expenses are present, it informs the user. If expenses exist, they are grouped
     * by their category and displayed in a formatted manner under each category.
     *
     * Each category is displayed once with its associated expenses listed under it.
     *
     * Assumes that the Expense class has a getCategory() method and that categories
     * are stored as strings.
     */
    public void viewExpensesByCategory() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }
        System.out.println("Expenses grouped by categories:");
        // Create a map to group expenses by their category
        Map<String, List<Expense>> expensesByCategory = new HashMap<>();
        // Populate the map
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            if (!expensesByCategory.containsKey(category)) {
                expensesByCategory.put(category, new ArrayList<>());
            }
            expensesByCategory.get(category).add(expense);
        }
        // Display the expenses grouped by category
        for (String category : expensesByCategory.keySet()) {
            System.out.println("Category: " + category);
            List<Expense> categoryExpenses = expensesByCategory.get(category);
            for (Expense expense : categoryExpenses) {
                System.out.println("  " + expense);
            }
        }
    }

    /**
     * Sets a budget limit for a specific category.
     *
     * If the category already has a budget, this method updates the budget limit.
     * If the category does not have a budget set, it creates a new budget for the category.
     *
     * This method is used to track and control spending limits for different categories.
     * After setting the budget, a message is displayed to confirm the action.
     *
     * @param category The name of the category to set the budget for
     * @param limit The budget limit to be set for the category (in dollars)
     */
    public void setBudgetLimit(String category, double limit) {
        if (budgets.containsKey(category)) {
            budgets.get(category).setLimit(limit);
            System.out.println("Updated budget for category '" + category + "' to $" + limit);
        } else {
            Budget newBudget = new Budget(category, limit);
            budgets.put(category, newBudget);
            System.out.println("Set budget for category '" + category + "' to $" + limit);
        }
    }


    public void viewBudget() {
        if (budgets.isEmpty()) {
            System.out.println("No budgets set for any category.");
            return;
        }

        // mapping total expenses for a category to each category
        Map<String, Double> totalExpensesToCategory = new HashMap<>();
        for (Expense expense: expenses) {
            String category = expense.getCategory();
            if (totalExpensesToCategory.containsKey(category)) {
                totalExpensesToCategory.put(category, totalExpensesToCategory.get(category) + expense.getAmount());
            } else {
                totalExpensesToCategory.put(category, expense.getAmount());
            }
        }

        // Calculate remaining budget, and display as needed
        for (String category: budgets.keySet()) {
            Budget budget = budgets.get(category);
            double totalExpense = totalExpensesToCategory.getOrDefault(category, 0.0);
            double remainingBudget = budget.getLimit() - totalExpense;

            if (remainingBudget >= 0) {
                System.out.println(category + ": $" + totalExpense + " spent, $" + remainingBudget + " remaining");
            } else {
                Double positive = Math.abs(remainingBudget);
                System.out.println(category + ": $" + totalExpense + " spent, Over budget by $" + positive);
            }
        }

        // if no budget set for certain category
        for (String category: totalExpensesToCategory.keySet()) {
            if (!budgets.containsKey(category)) {
                System.out.println(category + ": No budget set");
            }
        }

    }


}
