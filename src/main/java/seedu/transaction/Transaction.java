package seedu.transaction;

import seedu.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Abstract Transaction class
public abstract class Transaction {
    protected double amount;
    protected String description;
    protected String dateTimeString;  // Date stored as a string in ISO format

    // Default constructor required for Gson deserialization
    public Transaction() {
    }

    // Constructor that takes amount, description, and date as string
    public Transaction(double amount, String description, String dateTimeString) {
        this.amount = amount;
        this.description = description;
        this.dateTimeString = dateTimeString;
    }

    // Getters and setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method to get date as LocalDateTime
    public LocalDateTime getDate() {
        try {
            // Try parsing as LocalDateTime
            return DateTimeUtils.parseDateTime(dateTimeString);
        } catch (Exception e) {
            return null;
        }
    }

    // Method to set date from LocalDateTime
    public void setDate(LocalDateTime date) {
        this.dateTimeString = date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    // Getter and setter for dateTimeString
    public String getDateTimeString() {
        return dateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", description=" + description + ", date="
                + dateTimeString + "]";
    }

    public abstract String getTransactionType();
}
