//@@author glenda-1506
package seedu.spendswift.command;

import seedu.spendswift.ui.ErrorMessage;
import seedu.spendswift.format.Format;
import seedu.spendswift.ui.SuccessMessage;
import seedu.spendswift.model.Category;
import seedu.spendswift.model.Expense;
import seedu.spendswift.model.TrackerData;
import seedu.spendswift.parser.InputParser;

import java.util.List;

public class CategoryManager {
    public static void addCategoryHelper(TrackerData trackerData, String categoryName) {
        List<Category> categories = trackerData.getCategories();

        if (categoryName.isEmpty()) {
            ErrorMessage.printMissingCategory();
            return;
        }

        String formattedCategoryName = Format.formatInput(categoryName);
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

    public static void addCategory(String input, TrackerData trackerData) {
        InputParser parser = new InputParser();
        String categoryName = parser.parseCategory(input);

        if (categoryName == null || categoryName.isEmpty()) {
            ErrorMessage.printExpensesManagerEmptyCategory();
            return;
        }

        addCategoryHelper(trackerData, categoryName);
    }

    public static void viewAllCategories(TrackerData trackerData) {
        List<Category> categories = trackerData.getCategories();
        if (categories.isEmpty()) {
            SuccessMessage.printNoCategory();
        } else {
            SuccessMessage.printAllCategories(categories);
        }
    }

    private static void deleteCategoryHelper(TrackerData trackerData, String categoryName) {
        List<Category> categories = trackerData.getCategories();
        List<Expense> expenses = trackerData.getExpenses();
        boolean hasTaggedExpenses = false;

        Category categoryToDelete = null;
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                categoryToDelete = category;
                break;
            }
        }

        if (categoryToDelete == null) {
            SuccessMessage.printCategoryDoesNotExist(categoryName);
            return;
        }

        for (Expense expense : expenses) {
            if (expense.getCategory() != null && expense.getCategory().equals(categoryToDelete)) {
                hasTaggedExpenses = true;
                break;
            }
        }

        if (hasTaggedExpenses) {
            SuccessMessage.printCategoryHasExpense(categoryName);
        } else {
            categories.remove(categoryToDelete);
            trackerData.setCategories(categories);
            SuccessMessage.printDeleteCategory(categoryName);
        }
    }

    public static void deleteCategory(String input, TrackerData trackerData) {
        InputParser parser = new InputParser();
        String categoryName = parser.parseCategory(input);

        if (categoryName == null || categoryName.isEmpty()) {
            ErrorMessage.printExpensesManagerEmptyCategory();
            return;
        }

        deleteCategoryHelper(trackerData, categoryName);
    }
}

