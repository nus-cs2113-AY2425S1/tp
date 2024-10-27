package seedu.command;

import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KeywordsSearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_GUIDE = "search k/[keyword_1] [keyword_2] ... [keyword_n]";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"k/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {};

    public static final String ERROR_MESSAGE = "No match found";
    protected TransactionList transactions;

    public KeywordsSearchCommand(TransactionList transactions) {
        this.transactions = transactions;
    }

    @Override
    public List<String> execute() {
        if (!isArgumentsValid()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        // Extract the value under "k/" which contains all keywords
        String keywordsString = arguments.get("k/");
        if (keywordsString == null || keywordsString.trim().isEmpty()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        // Split the keywordsString into individual keywords
        List<String> keywords = Arrays.asList(keywordsString.trim().split("\\s+"));

        if (keywords.isEmpty()) {
            return List.of(LACK_ARGUMENTS_ERROR_MESSAGE);
        }

        // Search for transactions matching the keywords
        List<Transaction> result = transactions.searchTransactionsByKeywords(keywords);

        if (result.isEmpty()) {
            return List.of(ERROR_MESSAGE);
        }

        // Convert the matching transactions to a list of strings
        List<String> resultStrings = result.stream()
                .map(Transaction::toString)
                .collect(Collectors.toList());

        return resultStrings;
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
