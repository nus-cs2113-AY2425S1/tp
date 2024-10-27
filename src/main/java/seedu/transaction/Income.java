package seedu.transaction;

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
}
