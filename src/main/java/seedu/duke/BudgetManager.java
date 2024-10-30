package seedu.duke;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BudgetManager {
    private int lastResetMonth;
    private boolean isAutoResetEnabled;

    public BudgetManager() {
        this.lastResetMonth = -1;
        this.isAutoResetEnabled = false;
    }

    public void toggleAutoReset() {
        isAutoResetEnabled = !isAutoResetEnabled;
        System.out.println("Automatic budget reset is now " + (isAutoResetEnabled ? "ON" : "OFF") + ".");
    }

    public void checkAndResetBudgets(TrackerData trackerData) {

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);

        if (isAutoResetEnabled && currentMonth != lastResetMonth) {
            resetBudgets(trackerData);
            lastResetMonth = currentMonth;
        }
    }

    public void manageMonthlyReset(TrackerData trackerData) {
        checkAndResetBudgets(trackerData);
    }

    private void resetBudgets(TrackerData trackerData) {
        Map<Category, Budget> budgets = trackerData.getBudgets();

        for (Budget budget : budgets.values()) {
            // Resetting the budget logic can be adjusted as needed
            budget.setLimit(budget.getLimit()); // For now, just maintaining the same limit
        }

        trackerData.setBudgets(budgets);
        System.out.println("Budgets have been reset for all categories.");
    }

    public void setBudgetLimit(TrackerData trackerData, String categoryName, double limit) {
        List<Category> categories = trackerData.getCategories();
        Map<Category, Budget> budgets = trackerData.getBudgets();
        String formattedCategoryName = formatInput(categoryName.trim());

        Category existingCategory = null;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                existingCategory = category;
                break;
            }
        }

        if (existingCategory == null) {
            System.out.println("Category '" + formattedCategoryName + "' not found. Please add the category first.");
            return;
        }

        if (budgets.containsKey(existingCategory)) {
            budgets.get(existingCategory).setLimit(limit);
            System.out.println("Updated budget for category '" + existingCategory + "' to " + formatDecimal(limit));
        } else {
            Budget newBudget = new Budget(existingCategory, limit);
            budgets.put(existingCategory, newBudget);
            System.out.println("Set budget for category '" + existingCategory + "' to " + formatDecimal(limit));
        }

        trackerData.setBudgets(budgets);
    }

    public void viewBudget(TrackerData trackerData) {
        List<Expense> expenses = trackerData.getExpenses();
        Map<Category, Budget> budgets = trackerData.getBudgets();

        if (budgets.isEmpty()) {
            System.out.println("No budgets set for any category.");
            return;
        }

        // mapping total expenses for a category to each category
        Map<Category, Double> totalExpensesToCategory = new HashMap<>();
        for (Expense expense: expenses) {
            Category category = expense.getCategory();
            if (totalExpensesToCategory.containsKey(category)) {
                totalExpensesToCategory.put(category, totalExpensesToCategory.get(category) + expense.getAmount());
            } else {
                totalExpensesToCategory.put(category, expense.getAmount());
            }
        }

        // Calculate remaining budget, and display as needed
        for (Category category: budgets.keySet()) {
            Budget budget = budgets.get(category);
            double totalExpense = totalExpensesToCategory.getOrDefault(category, 0.0);
            double remainingBudget = budget.getLimit() - totalExpense;

            if (remainingBudget >= 0) {
                System.out.println(category + ": " + formatDecimal(totalExpense) + " spent, " +
                        formatDecimal(remainingBudget) + " remaining");
            } else {
                Double positive = Math.abs(remainingBudget);
                System.out.println(category + ": " + formatDecimal(totalExpense) + " spent, " +
                        "Over budget by " + formatDecimal(positive));
            }
        }

        // if no budget set for certain category
        for (Category category: totalExpensesToCategory.keySet()) {
            if (!budgets.containsKey(category)) {
                System.out.println(category + ": No budget set");
            }
        }
    }

    private String formatDecimal(double value) {
        BigDecimal roundedValue = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        DecimalFormat decimalFormat = new DecimalFormat("$#.00");
        return decimalFormat.format(roundedValue);
    }

    private String formatInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
