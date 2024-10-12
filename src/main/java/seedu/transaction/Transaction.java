package seedu.transaction;

import seedu.category.Category;

public class Transaction {
    private final double amount;
    private final String description;
    private final String date;
    private final Category category;

    public Transaction(double amount, String description, String date, Category category) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return "";
    }
    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", description=" + description + ", date=" + date + ", category="
                + category.getName() + "]";
    }
}
