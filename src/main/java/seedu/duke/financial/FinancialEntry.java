package seedu.duke.financial;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.util.Commons;

import java.time.LocalDate;

/**
 * Abstract base class for all financial transactions (Income and Expense).
 */
public abstract class FinancialEntry {
    protected String description;
    protected double amount;
    protected LocalDate date;

    /**
     * Constructs a FinancialEntry with the specified amount, description, and type.
     * Ensures that FinancialEntry has an amount between 0.01 and 9999999.00, has a
     * non-blank description and has date that is not after system date.
     *
     * @param amount The amount of the transaction.
     * @param description A description of the transaction.
     * @param date The date of the transaction (dd/MM/yyyy).
     */
    public FinancialEntry(double amount, String description, LocalDate date) throws FinanceBuddyException {
        double roundedAmount = Commons.roundToTwoDP(amount);
        checkValidParameters(roundedAmount, description, date);
        this.description = description;
        this.amount = roundedAmount;
        this.date = date;
    }

    /**
     * Returns the description of the transaction.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of the transaction.
     *
     * @param newDescription The new description.
     */
    public void setDescription(String newDescription) {
        assert !newDescription.isBlank() : "Attempted to set a null description";
        this.description = newDescription;
    }

    /**
     * Updates the date of the transaction.
     *
     * @param newDateString The new date as a String.
     */
    public void setDate(String newDateString) {
        LocalDate newDate = LocalDate.parse(newDateString);
        setDate(newDate);
    }

    /**
     * Updates the date of the transaction.
     *
     * @param newDate The new date.
     */
    public void setDate(LocalDate newDate) {
        assert !newDate.isAfter(LocalDate.now()) : "Attempted to set date after system date";
        this.date = newDate;
    }

    /**
     * Returns the amount of the transaction.
     *
     * @return The transaction amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Updates the amount of the transaction.
     *
     * @param newAmount The new amount.
     */
    public void setAmount(double newAmount) {
        assert newAmount >= Commons.MIN_AMOUNT : "Attempted to set amount less than 0.01";
        assert newAmount <= Commons.MAX_AMOUNT : "Attempted to set amount greater than 9999999.00";
        this.amount = newAmount;
    }

    /**
     * Returns the date of the transaction.
     *
     * @return The transaction date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string representation of the financial entry.
     * Subclasses (Income, Expense) must implement this.
     *
     * @return A string representation of the financial entry.
     */
    public abstract String toString();

    /**
     * Converts the financial entry to a formatted string for storage purposes.
     * This method is implemented by subclasses to provide specific formats for each entry type.
     * The string includes relevant information such as the entry type, amount, description, date,
     * and category, formatted appropriately.
     *
     * @return A formatted string representing the financial entry for storage.
     */
    public abstract String toStorageString();

    /**
     * Sets the category of the financial entry.
     * This method allows assigning a specific category to the entry and should be implemented by
     * subclasses to ensure the correct category type (Expense or Income) is used.
     *
     * @param category The category to assign to the financial entry.
     *                 Must be a valid enum value of the appropriate type.
     * @throws IllegalArgumentException if the provided category is invalid for the entry type.
     */
    public abstract void setCategory(Enum<?> category);

    private static void checkValidParameters(double amount, String description, LocalDate date)
            throws FinanceBuddyException {
        if (amount < Commons.MIN_AMOUNT) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_AMOUNT_TOO_SMALL);
        }
        if (amount > Commons.MAX_AMOUNT) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_AMOUNT_TOO_LARGE);
        }
        if (description == null || description.isBlank()) {
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_BLANK_DESCRIPTION);
        }
        if (date.isAfter(LocalDate.now())){
            throw new FinanceBuddyException(Commons.ERROR_MESSAGE_DATE_TOO_LATE);
        }
    }
}
