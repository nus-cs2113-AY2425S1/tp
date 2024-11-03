package seedu.transaction;

import java.util.Objects;

// Income class extending Transaction
public class Income extends Transaction {

    // Default constructor required for Gson deserialization
    public Income() {
        super();
    }

    public Income(double amount, String description, String dateTimeString) {
        super(amount, description, dateTimeString);
    }

    @Override
    public String getTransactionType() {
        return "Income";
    }

    @Override
    public String toString() {
        return "Income [amount=" + amount + ", description=" + description + ", date="
                + dateTimeString + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Income income)) {
            return false;
        }
        return amount == income.amount &&
                Objects.equals(description, income.description) &&
                Objects.equals(dateTimeString, income.dateTimeString);
    }
}
