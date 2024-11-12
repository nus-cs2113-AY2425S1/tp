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

/**
 * Handles the storage and retrieval of expenses from a file.
 * Responsible for saving expenses to and loading them from the specified file path.
 */
public class ExpenseStorage {
    private final String expenseFilePath;
    private final UI ui;

    /**
     * Constructs a {@code categoryStorage} object with the specified file path and UI.
     *
     * @param ui The {@code UI} instance which displays messages to users.
     * @param expenseFilePath File paths where expenses are stored.
     */
    public ExpenseStorage(String expenseFilePath, UI ui) {
        this.expenseFilePath = expenseFilePath;
        this.ui = ui;
    }


    /**
     * Saves the expenses to the file.
     * Each line in the files represents an expense, including details of the expense.
     *
     * @param trackerData The {@code TrackerData} containing the categories and budgets to save.
     */
    public void saveExpenses(TrackerData trackerData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expenseFilePath))) {
            for (Expense expense : trackerData.getExpenses()) {
                writer.write(expense.getName().trim() + " | " + expense.getAmount() + " | " +
                        expense.getCategory().getName().trim() + "\n");
            }
        }
    }


    /**
     * Loads the current expenses from the file into the provided tracker data.
     * If the file does not exist, appropriate messages will be displayed, and no categories would be added.
     *
     * @param trackerData The {@code TrackerData} to save the expenses.
     * @return A set of valid category names loaded from the file.
     * @throws IOException If an error occurs while reading from the file.
     */
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
