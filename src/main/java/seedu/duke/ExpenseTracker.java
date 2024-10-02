package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTracker {
    private List<Category> categories;

    public ExpenseTracker() {
        this.categories = new ArrayList<>();
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
