package seedu.transaction;

import seedu.utils.DateTimeUtils;

public class Income extends Transaction {
    public Income(double amount, String description, String date) throws Exception {
        super(amount, description, date);
    }

    @Override
    public String getTransactionType() {
        return "Income";
    }

    @Override
    public String toString() {
        return "Income [amount=" + amount + ", description=" + description + ", date="
                + DateTimeUtils.getDateTimeString(date) + "]";
    }
}
