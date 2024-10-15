package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private List<Category> categories;
    private List<Expense> expenses;

    public ExpenseTracker() {
        this.categories = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addExpense(String name, double amount, String category) {
        Expense newExpense = new Expense(name, amount, category);
        expenses.add(newExpense);
        System.out.println("Added" + newExpense);
    }

    public void addCategory(String categoryName) {
        for (Category category: categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                System.out.println("Category '" + categoryName + "' already exists!");
                return;
            }
        }
        String trimmedCategoryName = categoryName.substring("add category".length()).trim();
        Category newCategory = new Category(trimmedCategoryName);
        categories.add(newCategory);
        System.out.println("Category '" + newCategory + "' added successfully.");
    }

    private void tagExpenseHelper(int expenseIndex, String categoryName) {
        if (expenseIndex < 0 || expenseIndex >= expenses.size()) {
            System.out.println("Invalid index");
            return;
        }

        for (Category category: categories) {
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
            for (String part: parts) {
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
}
