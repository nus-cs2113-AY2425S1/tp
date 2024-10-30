package seedu.command;

import seedu.datastorage.Storage;
import seedu.transaction.Income;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;
import seedu.utils.AmountUtils;
import seedu.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddIncomeCommand extends AddTransactionCommand {
    public static final String COMMAND_WORD = "add-income";
    public static final String COMMAND_GUIDE = "add-income [DESCRIPTION] a/ AMOUNT [d/ DATE]";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"a/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"d/"};
    public static final String ERROR_MESSAGE = "Error creating Income!";

    public AddIncomeCommand(TransactionList transactions) {
        super(transactions);
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        String incomeName = arguments.get("");
        if (incomeName == null || incomeName.isEmpty()) {
            incomeName = "";
        }

        String amountStr = arguments.get(COMMAND_MANDATORY_KEYWORDS[0]);
        Double amount = null;
        try {
            amount = AmountUtils.parseAmount(amountStr);
        }
        catch (Exception e) {
            return List.of(ERROR_MESSAGE + ": " + e.getMessage());
        }

        String dateString = arguments.get(COMMAND_EXTRA_KEYWORDS[0]);
        if (dateString == null || dateString.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            dateString = LocalDateTime.now().format(formatter);
        } else {
            try {
                DateTimeUtils.parseDateTime(dateString);
            } catch (Exception e) {
                return List.of(ERROR_MESSAGE + ": " + e.getMessage());
            }
        }

        try {
            transactions.addTransaction(createTransaction(amount, incomeName, dateString));

            Storage.saveTransaction(transactions.getTransactions());

            return List.of("Income added successfully!");
        } catch (Exception e) {
            return List.of(ERROR_MESSAGE + ": " + e.getMessage());
        }

    }

    @Override
    protected Transaction createTransaction(double amount, String description, String date) throws Exception {
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
