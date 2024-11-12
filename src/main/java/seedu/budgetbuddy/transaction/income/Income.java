package seedu.budgetbuddy.transaction.income;

import seedu.budgetbuddy.transaction.Transaction;

import java.time.LocalDate;

/**
 * Represents an income transaction with a description, amount, and date.
 * This class extends the Transaction class to manage income-specific details.
 */
public class Income extends Transaction {
    /**
     * Creates an Income object with the specified description, amount, and date.
     *
     * @param description The description of the income.
     * @param amount The amount of income.
     * @param date The date of the income transaction.
     */
    public Income(String description, double amount, LocalDate date) {
        super(description, amount, date);
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
     * Retrieves the amount involved in the transaction.
     *
     * @return A double representing the amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the date of the transaction.
     *
     * @return A LocalDate representing the date when the transaction occurred.
     */
    public LocalDate getDate() {
        return date;
    }
}
