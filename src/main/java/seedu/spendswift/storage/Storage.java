package seedu.spendswift.storage;

import seedu.spendswift.model.Category;
import seedu.spendswift.model.TrackerData;
import seedu.spendswift.ui.UI;

import java.io.IOException;
import java.util.Set;

public class Storage {
    private final CategoryStorage categoryStorage;
    private final ExpenseStorage expenseStorage;
    private final UI ui;

    public Storage(String expenseFilePath, String categoryFilePath, UI ui) {
        this.ui = ui;
        this.categoryStorage = new CategoryStorage(categoryFilePath, ui);
        this.expenseStorage = new ExpenseStorage(expenseFilePath, ui);
    }

    public void saveData(TrackerData trackerData) throws IOException {
        categoryStorage.saveCategories(trackerData);
        expenseStorage.saveExpenses(trackerData);
    }

    public void loadData(TrackerData trackerData) throws IOException {
        Set<String> validCategories = categoryStorage.loadCategories(trackerData);
        expenseStorage.loadExpenses(trackerData, validCategories);
    }

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
