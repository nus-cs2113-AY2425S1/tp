package seedu.transaction;

import java.time.LocalDateTime;
import seedu.utils.DateTimeUtils;

// Abstract Transaction class
public abstract class Transaction {
    protected double amount;
    protected String description;
    protected LocalDateTime date;

    public Transaction(double amount, String description, String date) throws Exception {
        this.amount = amount;
        this.description = description;
        this.date = DateTimeUtils.parseDateTime(date); // Using the parseDateTime method from DateTimeUtils
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction [amount=" + amount + ", description=" + description + ", date="
                + DateTimeUtils.getDateTimeString(date) + "]";
    }
    public abstract String getTransactionType();
}

