package seedu.spendswift.storage;

import seedu.spendswift.command.Category;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.ui.UI;

import java.io.IOException;
import java.util.Set;

/**
 * Manages the overall storage and retrieval of data for categories and expenses.
 * Utilizes {@code CategoryStorage} and {@code ExpenseStorage} to handle their respective data.
 */
public class Storage {
    private final CategoryStorage categoryStorage;
    private final ExpenseStorage expenseStorage;
    private final UI ui;

    /**
     * Constructs a {@code Storage} object with the specified file path and UI.
     *
     * @param ui The {@code UI} instance which displays messages to users.
     * @param expenseFilePath File paths where expenses are stored.
     * @param categoryFilePath File paths where categories and their respective budgets are stored.
     */
    public Storage(String expenseFilePath, String categoryFilePath, UI ui) {
        this.ui = ui;
        this.categoryStorage = new CategoryStorage(categoryFilePath, ui);
        this.expenseStorage = new ExpenseStorage(expenseFilePath, ui);
    }

    /**
     * Saves the expenses and categories to the file.
     *
     * @param trackerData The {@code TrackerData} containing the categories and budgets to save.
     */
    public void saveData(TrackerData trackerData) throws IOException {
        categoryStorage.saveCategories(trackerData);
        expenseStorage.saveExpenses(trackerData);
    }

    /**
     * Loads the current expenses and categories from the file into the provided tracker data.
     *
     * @param trackerData The {@code TrackerData} to save the expenses.
     * @throws IOException If an error occurs while reading from the file.
     */
    public void loadData(TrackerData trackerData) throws IOException {
        Set<String> validCategories = categoryStorage.loadCategories(trackerData);
        expenseStorage.loadExpenses(trackerData, validCategories);
    }

    /**
     * Loads an existing category from the tracker data or creates a new one if it does not exist.
     *
     * @param trackerData The {@code TrackerData} containing the current categories.
     * @param categoryName The name of the category to load or create.
     * @return The existing or newly created {@code Category}.
     */
    public static Category loadOrCreateCategory(TrackerData trackerData, String categoryName) {
        for (Category category : trackerData.getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        Category newCategory = new Category(categoryName);
        trackerData.getCategories().add(newCategory);
        return newCategory;
    }
}
