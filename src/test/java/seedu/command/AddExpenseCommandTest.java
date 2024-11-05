package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.category.Category;
import seedu.transaction.Expense;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddExpenseCommandTest {
    private TransactionList transactionList;
    private AddExpenseCommand command;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @BeforeEach
    void setUp() {
        transactionList = new TransactionList();
        command = new AddExpenseCommand(transactionList);
    }

    @Test
    public void execute_addExpenseAllValidFields_success() {
        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024-10-01 1800", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals(List.of("Expense added successfully!"), result);
        verifyLastTransaction("dinner", 1000.0, "2024-10-01 1800", "Food");
    }

    @Test
    public void execute_addExpenseAllValidFieldsNoCategory_success() {
        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024-10-01 1800"));
        List<String> result = command.execute();

        assertEquals(List.of("Expense added successfully!"), result);
        verifyLastTransaction("dinner", 1000.0, "2024-10-01 1800", null);
    }

    @Test
    public void execute_addExpenseInvalidAmount_failure() {
        command.setArguments(Map.of("", "dinner", "a/", "test", "d/", "2024-10-01 1800", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals(List.of("Error creating Expense!: Invalid amount"), result);
        assertTrue(transactionList.getTransactions().isEmpty());
    }

    @Test
    public void execute_addExpenseInvalidDate_failure() {
        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals(List.of("Error creating Expense!: Your date and/or time is invalid!"), result);
        assertTrue(transactionList.getTransactions().isEmpty());
    }

    @Test
    public void execute_addExpenseNoDate_success() {
        command.setArguments(Map.of("", "dinner", "a/", "1000", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals(List.of("Expense added successfully!"), result);

        Transaction lastTransaction = transactionList.getTransactions().get(0);
        String currentDate = LocalDateTime.now().format(FORMATTER);
        assertEquals(currentDate, lastTransaction.getDate());
    }

    @Test
    public void execute_addExpenseNoDescription_success() {
        command.setArguments(Map.of("a/", "1000", "d/", "2024-10-01 1800", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals(List.of("Expense added successfully!"), result);
        verifyLastTransaction("", 1000.0, "2024-10-01 1800", "Food");
    }

    @Test
    public void execute_addExpenseNoMandatoryAmount_failure() {
        command.setArguments(Map.of("", "dinner", "d/", "2024-10-01 1800", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals(List.of("Missing mandatory fields!"), result);
        assertTrue(transactionList.getTransactions().isEmpty());
    }

    @Test
    public void execute_addExpenseNegativeAmount_success() {
        command.setArguments(Map.of("", "dinner", "a/", "-1000", "d/", "2024-10-01 1800"));
        List<String> result = command.execute();

        assertEquals(List.of("Expense added successfully!"), result);
        verifyLastTransaction("dinner", -1000.0, "2024-10-01 1800", null);
    }

    @Test
    public void execute_addExpenseEmptyCategory_success() {
        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024-10-01 1800", "c/", ""));
        List<String> result = command.execute();

        assertEquals(List.of("Expense added successfully!"), result);
        verifyLastTransaction("dinner", 1000.0, "2024-10-01 1800", null);
    }

    private void verifyLastTransaction(String expectedDescription, double expectedAmount,
                                       String expectedDate, String expectedCategory) {
        Transaction lastTransaction = transactionList.getTransactions().get(0);
        assertTrue(lastTransaction instanceof Expense);
        assertEquals(expectedDescription, lastTransaction.getDescription());
        assertEquals(expectedAmount, lastTransaction.getAmount());
        assertEquals(expectedDate, lastTransaction.getDate());

        if (expectedCategory != null) {
            assertEquals(expectedCategory, ((Expense) lastTransaction).getCategory().toString());
        } else {
            assertEquals(null, ((Expense) lastTransaction).getCategory());
        }
    }
}