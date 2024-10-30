package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    public void addExpense(TrackerData trackerData, String name, double amount, String categoryName) {
        List<Expense> expenses = trackerData.getExpenses();
        List<Category> categories = trackerData.getCategories();

        String formattedCategoryName = formatInput(categoryName.trim());
        Category existingCategory = null;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                existingCategory = category;
                break;
            }
        }
        if (existingCategory == null) {
            existingCategory = new Category(formattedCategoryName);
            categories.add(existingCategory);
            System.out.println("Category '" + formattedCategoryName + "' added successfully.");
        }
        Expense newExpense = new Expense(name, amount, existingCategory);
        expenses.add(newExpense);

        // update categories and expenses
        trackerData.setExpenses(expenses);
        trackerData.setCategories(categories);
        System.out.println("Added" + newExpense);
    }

    public void deleteExpense(TrackerData trackerData, int expenseIndex) {
        List<Expense> expenses = trackerData.getExpenses();

        if (expenseIndex < 0 || expenseIndex >= expenses.size()) {
            System.out.println("Invalid index. Unable to delete expense.");
            return;
        }
        Expense removedExpense = expenses.remove(expenseIndex);
        System.out.println("Deleted expense: " + removedExpense);
        trackerData.setExpenses(expenses);
    }

    public void viewExpensesByCategory(TrackerData trackerData) {
        List<Expense> expenses = trackerData.getExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }
        System.out.println("Expenses grouped by categories:");
        // Create a map to group expenses by their category
        Map<Category, List<Expense>> expensesByCategory = new HashMap<>();
        // Populate the map
        for (Expense expense : expenses) {
            Category category = expense.getCategory();
            if (!expensesByCategory.containsKey(category)) {
                expensesByCategory.put(category, new ArrayList<>());
            }
            expensesByCategory.get(category).add(expense);
        }
        // Display the expenses grouped by category
        for (Category category : expensesByCategory.keySet()) {
            System.out.println("Category: " + category);
            List<Expense> categoryExpenses = expensesByCategory.get(category);
            for (Expense expense : categoryExpenses) {
                System.out.println("  " + expense);
            }
        }
    }

    public void tagExpense(TrackerData trackerData, String input) {

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
            tagExpenseHelper(trackerData, expenseIndex, category);
        } catch (Exception e) {
            System.out.println("Error parsing the input. Please use correct format for tag expense commands.");
        }
    }

    private void tagExpenseHelper(TrackerData trackerData, int expenseIndex, String categoryName) {
        List<Expense> expenses = trackerData.getExpenses();
        List<Category> categories = trackerData.getCategories();

        if (expenseIndex < 0 || expenseIndex >= expenses.size()) {
            System.out.println("Invalid index");
            return;
        }
        String formattedCategoryName = formatInput(categoryName.trim());
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                Expense expense = expenses.get(expenseIndex);
                expense.setCategory(category);
                System.out.println("Tagged expense: " + expense);
                return;
            }
        }

        trackerData.setExpenses(expenses);
        System.out.println("Category '" + formattedCategoryName + "' does not exist.");
    }

    private String formatInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}


