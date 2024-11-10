//@@author glenda-1506
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

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void savedData(TrackerData trackerData, Storage storage, UI ui) {
        try {
            storage.saveData(trackerData);
        } catch (IOException e) {
            ui.printSavingError(e.getMessage());
        }
    }

    public void saveData(TrackerData trackerData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Categories, Budgets, and Expenses\n");

            for (Category category : trackerData.getCategories()) {
                writer.write("c/" + category.getName() + "\n");

                Budget budget = trackerData.getBudgets().get(category);
                if (budget != null) {
                    writer.write("l/" + budget.getLimit() + "\n");
                }

                for (Expense expense : trackerData.getExpenses()) {
                    if (expense.getCategory().equals(category)) {
                        writer.write("e/" + expense.getName() + " | " + expense.getAmount() + "\n");
                    }
                }
                writer.write("-----\n");
            }

            writer.write("r/" + trackerData.getResetStatus() + "\n");
        }
    }


    public void loadData(TrackerData trackerData) throws IOException {
        UI ui = new UI();
        File file = new File(filePath);
        if (!file.exists()) {
            ui.printFileNotFound();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Category currentCategory = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("c/")) {
                    currentCategory = parseCategory(line, trackerData);
                } else if (line.startsWith("l/") && currentCategory != null) {
                    parseBudget(line, currentCategory, trackerData);
                } else if (line.startsWith("e/") && currentCategory != null) {
                    parseExpense(line, currentCategory, trackerData);
                } else if (line.startsWith("r/")) {
                    parseResetStatus(line, trackerData);
                }
            }
        }
        ui.printDataLoaded();
    }

    private Category parseCategory(String line, TrackerData trackerData) {
        String categoryName = line.substring(2).trim();
        return loadCategory(trackerData, categoryName);
    }

    private Category loadCategory(TrackerData trackerData, String categoryName) {
        for (Category category : trackerData.getCategories()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        for (Category category : trackerData.getBudgets().keySet()) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        Category newCategory = new Category(categoryName);
        trackerData.getCategories().add(newCategory);
        return newCategory;
    }


    private void parseBudget(String line, Category currentCategory, TrackerData trackerData) {
        try {
            double budgetLimit = Double.parseDouble(line.substring(2).trim());
            Budget budget = new Budget(currentCategory, budgetLimit);
            trackerData.getBudgets().put(currentCategory, budget);
        } catch (NumberFormatException e) {
            System.out.println("Invalid budget format: " + line);
        }
    }

    private void parseExpense(String line, Category currentCategory, TrackerData trackerData) {
        String[] expenseParts = line.substring(2).split(" \\| ");
        if (expenseParts.length < 2) {
            System.out.println("Invalid expense data format. Skipping line: " + line);
            return;
        }
        try {
            String expenseName = expenseParts[0].trim();
            double amount = Double.parseDouble(expenseParts[1].trim());
            Expense expense = new Expense(expenseName, amount, currentCategory);
            trackerData.getExpenses().add(expense);
        } catch (NumberFormatException e) {
            System.out.println("Invalid expense amount format: " + line);
        }
    }

    private void parseResetStatus(String line, TrackerData trackerData) {
        boolean resetStatus = Boolean.parseBoolean(line.substring(2).trim());
        trackerData.setResetStatus(resetStatus);
    }

}
