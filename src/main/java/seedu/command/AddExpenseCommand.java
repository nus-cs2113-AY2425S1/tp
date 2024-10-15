package seedu.command;

import seedu.category.Category;
import seedu.transaction.Expense;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;
import java.util.List;

public class AddExpenseCommand extends AddTransactionCommand {
    public static final String COMMAND_WORD = "add-expense";
    public static final String COMMAND_GUIDE = "add-expense [DESCRIPTION] [a/ AMOUNT] [d/ DATE] [c/ hCATEGORY]";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"a/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"d/", "c/"};

    public AddExpenseCommand(TransactionList transactions, String description, String amount, String date) {
        super(transactions, description, amount, date);
    }

    @Override
    public List<String> execute() throws Exception {
        if (!isArgumentsValid()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }
        transactions.addTransaction(createTransaction());
        return List.of("Expense added successfully!");
    }

    @Override
    protected Transaction createTransaction() throws Exception {
        Category category = arguments.containsKey("c/") ? new Category(arguments.get("c/")) : null;
        return new Expense(amount, description, date, category);
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
