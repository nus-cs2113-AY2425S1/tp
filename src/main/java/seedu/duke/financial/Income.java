package seedu.duke.financial;

import seedu.duke.exception.FinanceBuddyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an income transaction.
 * An income increases the available balance.
 */
public class Income extends FinancialEntry {

    /**
     * Enum for predefined income categories.
     */
    public enum Category {
        SALARY, INVESTMENT, GIFT, OTHER, UNCATEGORIZED;
    }

    private Category category;

    /**
     * Constructs an Income object with the specified amount and description.
     *
     * @param amount The amount of the income.
     * @param description A description of the income.
     * @param date The date the expense occurred.
     * @param category The category of the income.
     */
    public Income(double amount, String description, LocalDate date, Category category) throws FinanceBuddyException {
        super(amount, description, date);
        this.category = category;
    }

    /**
     * Retrieves the income category for this entry.
     *
     * @return The category of this income entry as an {@link Income.Category}.
     */
    public Income.Category getCategory() {
        return category;
    }

    /**
     * Sets the category for this income entry. Ensures the provided category is of the correct type
     * for income entries.
     *
     * @param category The category to set, expected to be an {@link Income.Category} type.
     * @throws IllegalArgumentException if the provided category is not an instance of {@link Income.Category}.
     */
    @Override
    public void setCategory(Enum<?> category) {
        if (category instanceof Category) {
            this.category = (Category) category;
        } else {
            throw new IllegalArgumentException("Invalid category for Income.");
        }
    }

    /**
     * Returns a string representation of the income.
     *
     * @return A string in the format "[Income] $amount - description".
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yy");
        return String.format("[Income] - %s $ %.2f (on %s) [%s]", description, amount, date.format(pattern), category);
    }

    /**
     * Converts the income entry to a string format suitable for storage.
     * The returned string includes the entry type, amount, description, date, and category,
     * all formatted in a specific structure.
     *
     * @return A formatted string representing the income entry for storage.
     *         The format is: "I ¦¦ amount ¦¦ description ¦¦ date ¦¦ category"
     *         where the date is formatted as "dd/MM/yy".
     */
    public String toStorageString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yy");
        return String.format("I ¦¦ %.2f ¦¦ %s ¦¦ %s ¦¦ %s", amount, description, date.format(pattern), category);
    }
}
