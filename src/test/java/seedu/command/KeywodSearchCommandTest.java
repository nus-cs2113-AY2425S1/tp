package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.category.Category;
import seedu.message.CommandResultMessages;
import seedu.message.ErrorMessages;
import seedu.transaction.Expense;
import seedu.transaction.Income;
import seedu.transaction.TransactionList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeywordsSearchCommandTest {

    private TransactionList transactionList;
    private KeywordsSearchCommand command;

    @BeforeEach
    void setUp() {
        transactionList = new TransactionList();
        command = new KeywordsSearchCommand(transactionList);

        transactionList.addTransaction(
                new Expense(100, "lunch at food court", "2024-01-01 1200", new Category("Food")));
        transactionList.addTransaction(
                new Income(2000, "salary", "2024-01-01 1400"));
        transactionList.addTransaction(
                new Expense(50, "bus to work", "2024-01-02 0900", new Category("Transport")));
    }

    @Test
    void execute_validKeywords_success() {
        // Arrange
        command.setArguments(Map.of("k/", "lunch food"));

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("lunch at food court"));
    }

    @Test
    void execute_singleMatchWithDate_success() {
        // Arrange
        command.setArguments(Map.of("k/", "lunch 2024-01-01"));

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("lunch at food court"));
    }

    @Test
    void execute_noMatchingKeywords_returnsEmptyMessage() {
        // Arrange
        command.setArguments(Map.of("k/", "nonexistent"));

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(1, result.size());
        assertEquals(CommandResultMessages.FIND_KEYWORD_EMPTY, result.get(0));
    }

    @Test
    void execute_emptyKeywords_errorMessage() {
        // Arrange
        command.setArguments(Map.of("k/", ""));

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE, result.get(0));
    }

    @Test
    void execute_onlySpacesKeywords_errorMessage() {
        // Arrange
        command.setArguments(Map.of("k/", "   "));

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE, result.get(0));
    }

    @Test
    void execute_missingKeywordsArgument_errorMessage() {
        // Arrange
        Map<String, String> arguments = new HashMap<>();
        command.setArguments(arguments);

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE, result.get(0));
        assertEquals(command.COMMAND_GUIDE, result.get(1));
    }

    @Test
    void execute_caseInsensitiveSearch_success() {
        // Arrange
        command.setArguments(Map.of("k/", "LUNCH FOOD"));

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("lunch at food court"));
    }

    @Test
    void execute_singleKeywordSearch_success() {
        // Arrange
        command.setArguments(Map.of("k/", "lunch"));

        // Act
        List<String> result = command.execute();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("lunch"));
    }
}
