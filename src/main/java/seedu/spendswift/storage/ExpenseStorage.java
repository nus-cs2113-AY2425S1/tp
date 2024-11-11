package seedu.spendswift.storage;

import seedu.spendswift.model.Category;
import seedu.spendswift.model.Expense;
import seedu.spendswift.model.TrackerData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;
import java.io.IOException;

public class ExpenseStorage {
    private final String expenseFilePath;

    public ExpenseStorage(String expenseFilePath) {
        this.expenseFilePath = expenseFilePath;
    }

    public void saveExpenses(TrackerData trackerData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expenseFilePath))) {
            for (Expense expense : trackerData.getExpenses()) {
                writer.write(expense.getName().trim() + " | " + expense.getAmount() + " | " +
                        expense.getCategory().getName().trim() + "\n");
            }
        }
    }

    public void loadExpenses(TrackerData trackerData, Set<String> validCategories) throws IOException {
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

                    Category category = Storage.loadOrCreateCategory(trackerData, categoryName);
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
}
