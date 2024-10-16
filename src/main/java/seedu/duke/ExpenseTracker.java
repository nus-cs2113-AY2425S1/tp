package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashmap
import java.util.Map

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

}
