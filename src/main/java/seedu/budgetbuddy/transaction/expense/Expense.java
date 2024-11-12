package seedu.budgetbuddy.transaction.expense;

import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.Transaction;

import java.time.LocalDate;

/**
 * Represents an expense transaction in the budget management system.
 * An expense has a description, amount, date, and category.
 */
public class Expense extends Transaction {
    /** The category of the expense. */
    private Category category;

    /**
     * Constructs an Expense object with the specified details.
     *
     * @param description the description of the expense
     * @param amount      the amount of the expense
     * @param date        the date of the expense
     * @param category    the category of the expense
     */
    public Expense(String description, double amount, LocalDate date, Category category) {
        super(description, amount, date);
        this.category = category;
    }

    /**
     * Returns a string representation of the expense, including its details.
     *
     * @return a string describing the expense
     */
    @Override
    public String toString() {
        return String.format("Description: %s  Amount: %.2f  Date: %s  Category: %s",
                description, amount, date, category);
    }

    /**
     * Retrieves the description of the transaction.
     *
     * @return A string representing the description of the transaction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the amount of the transaction.
     *
     * @return A double representing the amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the date of the transaction.
     *
     * @return A LocalDate representing the date of the transaction.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Retrieves the category of the expense.
     *
     * @return The {@code Category} of the expense.
     */
    public Category getCategory() {
        return category;
    }


    /**
     * Updates the category of the expense.
     *
     * @param changeCategory The new category for the expense.
     */
    public void editCategory(Category changeCategory) {
        this.category = changeCategory;
    }
}
