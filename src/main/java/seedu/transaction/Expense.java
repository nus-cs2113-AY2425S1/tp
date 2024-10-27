package seedu.transaction;

import seedu.category.Category;

// Expense class extending Transaction
public class Expense extends Transaction {
    private Category category;

    // Default constructor required for Gson deserialization
    public Expense() {
        super();
    }

    // Constructor with category
    public Expense(double amount, String description, String dateTimeString, Category category) {
        super(amount, description, dateTimeString);
        this.category = category;
    }

    // Constructor without category
    public Expense(double amount, String description, String dateTimeString) {
        super(amount, description, dateTimeString);
        this.category = new Category("");
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String getTransactionType() {
        return "Expense";
    }

    @Override
    public String toString() {
        String categoryString = (category != null) ? ", category=" + category.getName() : "";
        return "Expense [amount=" + amount + ", description=" + description + ", date="
                + dateTimeString + categoryString + "]";
    }
}
