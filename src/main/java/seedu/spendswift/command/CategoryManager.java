//@@author glenda-1506
package seedu.spendswift.command;

import seedu.spendswift.ErrorMessage;
import seedu.spendswift.Format;
import seedu.spendswift.SuccessMessage;
import seedu.spendswift.parser.InputParser;

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

    public static void viewAllCategories(TrackerData trackerData) {
        List<Category> categories = trackerData.getCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
        } else {
            System.out.println("Categories:");
            int index = 1;
            for (Category category : categories) {
                System.out.println(index + ". " + category.getName());
                index++;
            }
        }
    }

    private void deleteCategoryHelper(TrackerData trackerData, String categoryName) {
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
            System.out.println("Category \"" + categoryName + "\" does not exist.");
            return;
        }

        for (Expense expense : expenses) {
            if (expense.getCategory() != null && expense.getCategory().equals(categoryToDelete)) {
                hasTaggedExpenses = true;
                break;
            }
        }

        if (hasTaggedExpenses) {
            System.out.println("Category \"" + categoryName + "\" cannot be deleted because some expenses are tagged to it.");
            System.out.println("Please delete those expenses or re-tag them to another category before deleting this category.");
        } else {
            categories.remove(categoryToDelete);
            trackerData.setCategories(categories);
            System.out.println("Category \"" + categoryName + "\" has been deleted successfully.");
        }
    }


    public static void deleteCategory(String input, TrackerData trackerData, CategoryManager categoryManager) {
        InputParser parser = new InputParser();
        String categoryName = parser.parseCategory(input);

        if (categoryName == null || categoryName.isEmpty()) {
            ErrorMessage.printExpensesManagerEmptyCategory();
            return;
        }

        categoryManager.deleteCategoryHelper(trackerData, categoryName);
    }
}

