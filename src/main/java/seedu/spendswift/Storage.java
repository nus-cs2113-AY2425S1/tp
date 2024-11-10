package seedu.spendswift;

import seedu.spendswift.command.Budget;
import seedu.spendswift.command.Category;
import seedu.spendswift.command.Expense;
import seedu.spendswift.command.TrackerData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Storage {
    private final String expenseFilePath;
    private final String categoryFilePath;

    public Storage(String expenseFilePath, String categoryFilePath) {
        this.expenseFilePath = expenseFilePath;
        this.categoryFilePath = categoryFilePath;
    }

    public void saveData(TrackerData trackerData) throws IOException {
        saveCategories(trackerData);
        saveExpenses(trackerData);
    }

    private void saveCategories(TrackerData trackerData) throws IOException {
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

    private void saveExpenses(TrackerData trackerData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expenseFilePath))) {
            for (Expense expense : trackerData.getExpenses()) {
                writer.write(expense.getName().trim() + " | " + expense.getAmount() + " | " +
                        expense.getCategory().getName().trim() + "\n");
            }
        }
    }

    public void loadData(TrackerData trackerData) throws IOException {
        Set<String> validCategories = loadCategories(trackerData);
        loadExpenses(trackerData, validCategories);
    }

    private Set<String> loadCategories(TrackerData trackerData) throws IOException {
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

                Category category = loadOrCreateCategory(trackerData, categoryName);
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

    private void loadExpenses(TrackerData trackerData, Set<String> validCategories) throws IOException {
        File expenseFile = new File(expenseFilePath);

        if (!expenseFile.exists()) {
            System.out.println("Expense file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(expenseFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                if (parts.length != 3) {
                    System.out.println("Invalid expense format, skipping line: " + line);
                    continue;
                }

                String expenseName = parts[0].trim();
                try {
                    double amount = Double.parseDouble(parts[1].trim());
                    String categoryName = parts[2].trim();

                    Category category = loadOrCreateCategory(trackerData, categoryName);
                    if (!validCategories.contains(categoryName)) {
                        System.out.println("Warning: Expense has an undefined category. Adding category: " +
                                categoryName);
                        validCategories.add(categoryName);
                    }

                    Expense expense = new Expense(expenseName, amount, category);
                    trackerData.getExpenses().add(expense);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid expense amount format, skipping line: " + line);
                }
            }
        }
    }

    private Category loadOrCreateCategory(TrackerData trackerData, String categoryName) {
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
