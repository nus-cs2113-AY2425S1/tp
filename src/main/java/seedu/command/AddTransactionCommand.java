// Abstract AddTransactionCommand
package seedu.command;

import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;
import seedu.utils.DateTimeUtils;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AddTransactionCommand extends Command {

    protected TransactionList transactions;

    public AddTransactionCommand(TransactionList transactions) {
        this.transactions = transactions;
    }

    public abstract List<String> execute() throws Exception ;

    protected abstract Transaction createTransaction(double amount, String description, String date) throws Exception;

    protected abstract String[] getMandatoryKeywords();
    protected abstract String[] getExtraKeywords();
    protected abstract String getCommandWord();
    protected abstract String getCommandGuide();
}
