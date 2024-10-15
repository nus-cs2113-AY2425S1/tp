package seedu.command;

import seedu.transaction.Income;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;
import java.util.List;

public class AddIncomeCommand extends AddTransactionCommand {
    public static final String COMMAND_WORD = "add-income";
    public static final String COMMAND_GUIDE = "add-income [DESCRIPTION] [a/ AMOUNT] [d/ DATE]";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"a/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"d/"};

    public AddIncomeCommand(TransactionList transactions, String description, String amount, String date) {
        super(transactions, description, amount, date);
    }

    @Override
    public List<String> execute() throws Exception{
        System.out.println(amountString); // For testing
        if (!isArgumentsValid()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        amount = Double.parseDouble(amountString);
        transactions.addTransaction(createTransaction());
        return List.of("Income added successfully!");
    }

    @Override
    protected Transaction createTransaction() throws Exception {
        return new Income(amount, description, date);
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