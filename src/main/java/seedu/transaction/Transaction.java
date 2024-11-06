package seedu.transaction;

import seedu.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an abstract transaction involving financial data, including an amount, description, and date.
 * Original Implementation is by @YukeeHong in commit SHA 392a1fbce1f760236c968b3edfca0f4b8d3b81e7
 */
public abstract class Transaction {
    /** The amount involved in the transaction. */
    protected double amount;
    /** A description of the transaction. */
    protected String description;
    /** The date and time of the transaction, stored as a string in ISO format. */
    protected String dateTimeString;

    /**
     * Default constructor required for Gson deserialization.
     */
    public Transaction() {
    }

    /**
     * Constructs a Transaction with the specified amount, description, and date.
     *
     * @param amount         The amount of the transaction.
     * @param description    A description of the transaction.
     * @param dateTimeString The date and time of the transaction, in ISO format.
     */
    public Transaction(double amount, String description, String dateTimeString) {
        this.amount = amount;
        this.description = description;
        this.dateTimeString = dateTimeString;
    }

    /**
     * Gets the amount involved in the transaction.
     *
     * @return The transaction amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount involved in the transaction.
     *
     * @param amount The new transaction amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return The transaction description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description The new transaction description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date and time of the transaction as a LocalDateTime object.
     *
     * @return The date and time of the transaction, or null if parsing fails.
     */
    public LocalDateTime getDate() {
        try {
            // Try parsing as LocalDateTime
            return DateTimeUtils.parseDateTime(dateTimeString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Sets the date and time of the transaction from a LocalDateTime object.
     *
     * @param date The new date and time of the transaction.
     */
    public void setDate(LocalDateTime date) {
        this.dateTimeString = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    /**
     * Gets the date and time of the transaction as a string.
     *
     * @return The date and time string of the transaction.
     */
    public String getDateTimeString() {
        return dateTimeString;
    }

    /**
     * Sets the date and time of the transaction as a string.
     *
     * @param dateTimeString The new date and time string of the transaction.
     */
    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    /**
     * Returns a string representation of the transaction, including the amount, description, and date.
     *
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", description=" + description + ", date="
                + dateTimeString + "]";
    }

    /**
     * Gets the type of the transaction.
     *
     * @return The type of the transaction (e.g., "Income" or "Expense").
     */
    public abstract String getTransactionType();
}
