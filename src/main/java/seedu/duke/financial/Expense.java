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
     * Retrieves the expense category for this entry.
     *
     * @return The category of this income entry as an {@link Expense.Category}.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category for this expense entry. Ensures the provided category is of the correct type
     * for income entries.
     *
     * @param category The category to set, expected to be an {@link Expense.Category} type.
     * @throws IllegalArgumentException if the provided category is not an instance of {@link Expense.Category}.
     */
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
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[Expense] - %s $ %.2f (on %s) [%s]", description, amount, date.format(pattern),category);
    }

    /**
     * Converts the expense entry to a string format suitable for storage.
     * The returned string includes the entry type, amount, description, date, and category,
     * all formatted in a specific structure.
     *
     * @return A formatted string representing the expense entry for storage.
     *         The format is: "E ¦¦ amount ¦¦ description ¦¦ date ¦¦ category"
     *         where the date is formatted as "dd/MM/yyyy".
     */
    public String toStorageString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("E ¦¦ %.2f ¦¦ %s ¦¦ %s ¦¦ %s", amount, description, date.format(pattern),category);
    }
}
