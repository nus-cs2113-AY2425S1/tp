package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.transaction.TransactionList;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddExpenseCommandTest {

    @Test
    public void execute_addExpenseAllValidFields_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024-10-01 1800", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals("Expense added successfully!", result.get(0));
    }

    @Test
    public void execute_addExpenseAllValidFieldsNoCategory_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024-10-01 1800"));
        List<String> result = command.execute();

        assertEquals("Expense added successfully!", result.get(0));
    }

    @Test
    public void execute_addExpenseInvalidAmount_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        command.setArguments(Map.of("", "dinner", "a/", "test", "d/", "2024-10-01 1800", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals("Error creating Expense!: Invalid amount", result.get(0));
    }

    @Test
    public void execute_addExpenseInvalidDate_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals("Error creating Expense!: Your date and/or time is invalid!", result.get(0));
    }

    @Test
    public void execute_addExpenseNoDate_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        command.setArguments(Map.of("", "dinner", "a/", "1000", "c/", "Food"));
        List<String> result = command.execute();

        assertEquals("Expense added successfully!", result.get(0));
    }

}
