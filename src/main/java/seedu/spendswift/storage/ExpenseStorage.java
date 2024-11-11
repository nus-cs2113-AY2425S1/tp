package seedu.spendswift.storage;

import seedu.spendswift.command.Category;
import seedu.spendswift.command.Expense;
import seedu.spendswift.command.TrackerData;
import seedu.spendswift.ui.UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Set;
import java.io.IOException;

public class ExpenseStorage {
    private final String expenseFilePath;
    private final UI ui;

    public ExpenseStorage(String expenseFilePath, UI ui) {
        this.expenseFilePath = expenseFilePath;
        this.ui = ui;
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
            ui.printExpenseFileNotFound();
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
                    ui.printInvalidExpenseLineLoad(line);
                    continue;
                }

                String expenseName = parts[0].trim();
                try {
                    double amount = Double.parseDouble(parts[1].trim());
                    String categoryName = parts[2].trim();

                    Category category = Storage.loadOrCreateCategory(trackerData, categoryName);
                    if (!validCategories.contains(categoryName)) {
                        ui.printUndefinedCategory(categoryName);
                        validCategories.add(categoryName);
                    }

                    Expense expense = new Expense(expenseName, amount, category);
                    trackerData.getExpenses().add(expense);
                } catch (NumberFormatException e) {
                    ui.printInvalidExpenseLineLoad(line);
                }
            }
        }
    }
}
