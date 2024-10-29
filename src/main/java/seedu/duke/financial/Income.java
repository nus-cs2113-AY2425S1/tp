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

    public Income.Category getCategory() {
        return category;
    }

    public void setCategory(Income.Category category) {
        this.category = category;
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

    public String toStorageString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yy");
        return String.format("I | %.2f | %s | %s | %s", amount, description, date.format(pattern), category);
    }
}
