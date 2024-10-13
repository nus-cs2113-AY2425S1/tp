package seedu.transaction;

import seedu.category.Category;
import seedu.utils.DateTimeUtils;

class Expense extends Transaction {
    private final Category category;

    public Expense(double amount, String description, String date, Category category) {
        super(amount, description, date);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String getTransactionType() {
        return "Expense";
    }

    @Override
    public String toString() {
        return "Expense [amount=" + amount + ", description=" + description + ", date="
                + DateTimeUtils.getDateTimeString(date) + ", category=" + category.getName() + "]";
    }
}
