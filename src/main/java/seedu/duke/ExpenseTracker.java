package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private List<Category> categories;

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
}
