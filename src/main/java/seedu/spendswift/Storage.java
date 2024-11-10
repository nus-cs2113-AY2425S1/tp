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
import java.util.Map;

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
            writer.write("Budgets\n");
            for (Map.Entry<Category, Budget> entry : trackerData.getBudgets().entrySet()) {
                String categoryName = entry.getKey().getName();
                double budgetLimit = entry.getValue().getLimit();
                writer.write(categoryName + ", " + budgetLimit + "\n");
            }

            writer.write("Expenses\n");
            for (Expense expense : trackerData.getExpenses()) {
                String expenseName = expense.getName();
                double amount = expense.getAmount();
                String categoryName = expense.getCategory().getName();
                writer.write(expenseName + ", " + amount + ", " + categoryName + "\n");
            }
        }
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

    public void loadData(TrackerData trackerData) throws IOException {
        UI ui = new UI();
        File file = new File(filePath);
        if (!file.exists()) {
            ui.printFileNotFound();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isBudgetSection = true;

            while ((line = reader.readLine()) != null) {
                if (line.equals("Budgets")) {
                    isBudgetSection = true;
                    continue;
                } else if (line.equals("Expenses")) {
                    isBudgetSection = false;
                    continue;
                }

                String[] parts = line.split(", ");
                try {
                    if (isBudgetSection) {
                        if (parts.length < 2) {
                            System.out.println("Invalid budget data format. Skipping line: " + line);
                            continue;
                        }
                        String categoryName = parts[0];
                        double limit = Double.parseDouble(parts[1]);
                        Category category = new Category(categoryName);
                        Budget budget = new Budget(category, limit);
                        trackerData.getBudgets().put(category, budget);
                    } else {
                        if (parts.length < 3) {
                            System.out.println("Invalid expense data format. Skipping line: " + line);
                            continue;
                        }
                        String expenseName = parts[0];
                        double amount = Double.parseDouble(parts[1]);
                        String categoryName = parts[2];
                        Category category = loadCategory(trackerData, categoryName);
                        Expense expense = new Expense(expenseName, amount, category);
                        trackerData.getExpenses().add(expense);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid format in line: " + line);
                }
            }
        }
        ui.printDataLoaded();
    }
}
