// Abstract AddTransactionCommand
package seedu.command;

import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;
import seedu.utils.DateTimeUtils;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AddTransactionCommand extends Command {
    protected String amountString;
    protected double amount;
    protected String description;
    protected String date;
    protected TransactionList transactions;

    public AddTransactionCommand(TransactionList transactions, String description, String amount, String date) {
        this.transactions = transactions;
        this.description = description;
        this.amountString = amount; // Store the amount as a string initially
        this.date = date != null && !date.isEmpty() ? date : LocalDateTime.now().toString();
    }

    public abstract List<String> execute() throws Exception ;

    protected abstract Transaction createTransaction() throws Exception;

    protected abstract String[] getMandatoryKeywords();
    protected abstract String[] getExtraKeywords();
    protected abstract String getCommandWord();
    protected abstract String getCommandGuide();
}
