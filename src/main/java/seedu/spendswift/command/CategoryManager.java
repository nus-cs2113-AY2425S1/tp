//@@author glenda-1506
package seedu.spendswift.command;

import seedu.spendswift.ErrorMessage;
import seedu.spendswift.Format;
import seedu.spendswift.SuccessMessage;
import seedu.spendswift.UI;

import java.util.List;

public class CategoryManager {
    public static void addCategory(TrackerData trackerData, String categoryName) {
        List<Category> categories = trackerData.getCategories();
        String trimmedCategoryName = categoryName.substring("add category".length()).trim();
        if (trimmedCategoryName.isEmpty()) {
            ErrorMessage.printMissingCategory();
            return;
        }

        String formattedCategoryName = Format.formatInput(trimmedCategoryName.trim());
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(formattedCategoryName)) {
                SuccessMessage.printExistingCategory(formattedCategoryName);
                return;
            }
        }
        Category newCategory = new Category(formattedCategoryName);
        categories.add(newCategory);
        trackerData.setCategories(categories);
        SuccessMessage.printAddCategory(newCategory);
    }
}

