package seedu;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    private final List<Category> categories;

    public CategoryList() {
        categories = new ArrayList<>();
        // Adding default categories
        categories.add(new Category("Food"));
        categories.add(new Category("Transport"));
        categories.add(new Category("Entertainment"));
        categories.add(new Category("Utilities"));
    }

    public void addCategory(Category category) {
        categories.add(category);
        System.out.println("Category added: " + category);
    }

    public void deleteCategory(int index) {
        if (index >= 0 && index < categories.size()) {
            Category removed = categories.remove(index);
            System.out.println("Category removed: " + removed);
        } else {
            System.out.println("Invalid category index!");
        }
    }

    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }
    public void listCategories() {
        System.out.println("All categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
    }
}