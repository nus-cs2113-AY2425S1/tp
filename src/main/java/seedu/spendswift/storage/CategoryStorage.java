package seedu.spendswift.storage;

import seedu.spendswift.model.Budget;
import seedu.spendswift.model.Category;
import seedu.spendswift.model.TrackerData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

public class CategoryStorage {
    private final String categoryFilePath;

    public CategoryStorage(String categoryFilePath) {
        this.categoryFilePath = categoryFilePath;
    }

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

    public Set<String> loadCategories(TrackerData trackerData) throws IOException {
        Set<String> validCategories = new HashSet<>();
        File categoryFile = new File(categoryFilePath);

        if (!categoryFile.exists()) {
            System.out.println("Category file not found.");
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
                        System.out.println("Invalid budget format in category file, skipping line: " + line);
                    }
                }
            }
        }
        return validCategories;
    }
}
