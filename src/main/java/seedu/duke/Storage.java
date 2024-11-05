package seedu.duke;

import java.io.BufferedWriter;
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
}
