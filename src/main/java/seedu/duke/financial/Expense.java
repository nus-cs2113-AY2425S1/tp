package seedu.duke.financial;

import seedu.duke.exception.FinanceBuddyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an expense transaction.
 * An expense reduces the available balance.
 */
public class Expense extends FinancialEntry {

    /**
     * Enum for predefined expense categories.
     */
    public enum Category {
        FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, OTHER, UNCATEGORIZED;
    }

    private Category category;

    /**
     * Constructs an Expense object with the specified amount and description.
     *
     * @param amount The amount of the expense.
     * @param description A description of the expense.
     * @param date The date the expense occurred.
     * @param category The category of the expense.
     */
    public Expense(double amount, String description, LocalDate date, Category category) throws FinanceBuddyException {
        super(amount, description, date);
        this.category = category;
    }

    /**
     * Constructs an Expense object with the specified amount and description.
     *
     * @param amount The amount of the expense.
     * @param description A description of the expense.
     * @param date The date the expense occurred.
     */
    public Expense(double amount, String description, LocalDate date) throws FinanceBuddyException {
        super(amount, description, date);
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Enum<?> category) {
        if (category instanceof Category) {
            this.category = (Category) category;
        } else {
            throw new IllegalArgumentException("Invalid category for Expense.");
        }
    }

    /**
     * Returns a string representation of the expense including the date.
     *
     * @return A string in the format "[Expense] $amount - description (on date)".
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yy");
        return String.format("[Expense] - %s $ %.2f (on %s) [%s]", description, amount, date.format(pattern),category);
    }

    public String toStorageString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yy");
        return String.format("E | %.2f | %s | %s | %s", amount, description, date.format(pattern),category);
    }
}
