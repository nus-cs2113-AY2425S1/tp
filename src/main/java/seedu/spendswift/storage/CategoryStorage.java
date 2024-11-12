package seedu.spendswift.storage;

import seedu.spendswift.command.Budget;
import seedu.spendswift.command.Category;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.ui.UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

/**
 * Handles the storage and retrieval of categories and associated budgets from a file.
 * Responsible for saving categories to and loading them from the specified file path.
 */
public class CategoryStorage {
    private final String categoryFilePath;
    private final UI ui;

    /**
     * Constructs a {@code categoryStorage} object with the specified file path and UI.
     *
     * @param ui The {@code UI} instance which displays messages to users.
     * @param categoryFilePath File paths where categories and relative budgets are stored.
     */
    public CategoryStorage(String categoryFilePath, UI ui) {
        this.categoryFilePath = categoryFilePath;
        this.ui = ui;
    }


    /**
     * Saves the current categories and budgets to the file.
     * Each line in the files represents a category, optionally comes with a budget.
     *
     * @param trackerData The {@code TrackerData} containing the categories and budgets to save.
     */
    public void saveCategories(TrackerData trackerData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(categoryFilePath))) {
            for (Category category : trackerData.getCategories()) {
                Budget budget = trackerData.getBudgets().get(category);
                writer.write(category.getName().trim());
                if (budget != null) {
                    writer.write(" | " + budget.getLimit());
                }
                writer.write("\n");
            }
        }
    }


    /**
     * Loads the current categories and budgets from the file into the provided tracker data.
     * Each line in the files represents a category, optionally comes with a budget.
     * If the file does not exist, appropriate messages will be displayed, and no categories would be added.
     *
     * @param trackerData The {@code TrackerData} to save the categories and budgets.
     * @return A set of valid category names loaded from the file.
     * @throws IOException If an error occurs while reading from the file.
     */
    public Set<String> loadCategories(TrackerData trackerData) throws IOException {
        Set<String> validCategories = new HashSet<>();
        File categoryFile = new File(categoryFilePath);

        if (!categoryFile.exists()) {
            ui.printCategoryFileNotFound();
            return validCategories;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(categoryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                String categoryName = parts[0].trim();
                validCategories.add(categoryName);

                Category category = Storage.loadOrCreateCategory(trackerData, categoryName);
                if (parts.length == 2) {
                    try {
                        double limit = Double.parseDouble(parts[1].trim());
                        trackerData.getBudgets().put(category, new Budget(category, limit));
                    } catch (NumberFormatException e) {
                        ui.printInvalidCategoryLineLoad(line);
                    }
                }
            }
        }
        return validCategories;
    }
}
