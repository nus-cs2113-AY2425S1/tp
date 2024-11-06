package seedu.duke.Command;

import seedu.duke.Format;
import seedu.duke.TrackerData;

import java.util.List;

public class CategoryManager {

    //@@author glenda-1506
    public void addCategory(TrackerData trackerData, String categoryName) {
        List<Category> categories = trackerData.getCategories();
        String trimmedCategoryName = categoryName.substring("add category".length()).trim();
        if (trimmedCategoryName.isEmpty()) {
            System.out.println("Category name is empty!");
            return;
        }

        String formattedCategoryName = Format.formatInput(trimmedCategoryName.trim());
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                System.out.println("Category '" + formattedCategoryName + "' already exists!");
                return;
            }
        }
        Category newCategory = new Category(formattedCategoryName);
        categories.add(newCategory);
        trackerData.setCategories(categories);
        System.out.println("Category '" + newCategory + "' added successfully.");
    }
}

