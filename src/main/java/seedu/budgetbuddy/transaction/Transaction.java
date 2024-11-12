package seedu.budgetbuddy.transaction;

import java.time.LocalDate;

/**
 * The {@code Transaction} class represents a financial transaction.
 * It includes details about the transaction description, amount, and date.
 */
public class Transaction {
    protected String description;
    protected double amount;
    protected LocalDate date;

    /**
     * Constructs a Transaction with the specified description, amount, and date.
     *
     * @param description A brief description of the transaction.
     * @param amount      The amount of the transaction.
     * @param date        The date of the transaction.
     */
    public Transaction(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Returns a string representation of the transaction, including its description, amount, and date.
     *
     * @return A formatted string with the transaction details.
     */
    public String toString() {
        String output = "";
        output += "Description: " + description;
        output += "  Amount: " + String.format("%.2f", amount);
        output += "  Date: " + date;
        return output;
    }

    public void editAmount(double amount) {
        this.amount = amount;
    }

    public void editDate(LocalDate date) {
        this.date = date;
    }
}
