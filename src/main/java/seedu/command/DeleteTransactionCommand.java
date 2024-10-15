package seedu.command;

import seedu.transaction.TransactionList;

import java.util.List;

public class DeleteTransactionCommand extends Command {
    public static final String COMMAND_WORD = "delete-transaction";
    public static final String COMMAND_GUIDE = "delete-transaction i/ INDEX: Delete a transaction";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"i/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {};

    protected TransactionList transactions;

    public DeleteTransactionCommand(TransactionList transactions) {
        this.transactions = transactions;
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        String indexString = arguments.get(COMMAND_MANDATORY_KEYWORDS[0]);
        Integer index = Integer.parseInt(indexString);
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            return List.of("Invalid index format. Please enter a valid integer index.");
        }

        int transactionListSize = transactions.size();
        if (index < 1 || index > transactionListSize) {
            return List.of("Invalid transaction index!");
        }

        transactions.deleteTransaction(index-1);
        return List.of("Transaction deleted successfully!");
    }

    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    @Override
    protected String[] getExtraKeywords() {
        return COMMAND_EXTRA_KEYWORDS;
    }

    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }
}
