package seedu.duke;

import seedu.duke.command.Budget;
import seedu.duke.command.Category;
import seedu.duke.command.Expense;
import seedu.duke.command.TrackerData;

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
        Category newCategory = new Category(categoryName);
        trackerData.getCategories().add(newCategory);
        return newCategory;
    }

    public void loadData(TrackerData trackerData) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Data file not found.");
            return;
        }

        //System.out.println("Loading data from file: " + filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isBudgetSection = true;

            while ((line = reader.readLine()) != null) {
                //System.out.println("Read line: " + line);
                if (line.equals("Budgets")) {
                    isBudgetSection = true;
                    continue;
                } else if (line.equals("Expenses")) {
                    isBudgetSection = false;
                    continue;
                }

                String[] parts = line.split(", ");
                if (isBudgetSection) {
                    String categoryName = parts[0];
                    double limit = Double.parseDouble(parts[1]);
                    Category category = new Category(categoryName);
                    Budget budget = new Budget(category, limit);
                    trackerData.getBudgets().put(category, budget);
                } else {
                    String expenseName = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String categoryName = parts[2];
                    Category category = loadCategory(trackerData, categoryName);
                    Expense expense = new Expense(expenseName, amount, category);
                    trackerData.getExpenses().add(expense);
                }
            }
        }
    }
}
