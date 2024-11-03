package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.category.Category;
import seedu.transaction.Expense;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddExpenseCommandTest {

    @Test
    public void execute_addExpenseAllValidFields_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        ArrayList<Transaction> expectedList = new ArrayList<>();
        Expense expectedExpense = new Expense(1000, "dinner", "2024-10-01 1800", new Category("Food"));
        expectedList.add(expectedExpense);

        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024-10-01 1800", "c/", "Food"));

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addExpenseAllValidFieldsNoCategory_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        ArrayList<Transaction> expectedList = new ArrayList<>();
        Expense expectedExpense = new Expense(1000, "dinner", "2024-10-01 1800", new Category(""));
        expectedList.add(expectedExpense);

        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024-10-01 1800"));

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addExpenseInvalidAmount_notAdding() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        ArrayList<Transaction> expectedList = new ArrayList<>();

        command.setArguments(Map.of("", "dinner", "a/", "test", "d/", "2024-10-01 1800", "c/", "Food"));

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addExpenseInvalidDate_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        ArrayList<Transaction> expectedList = new ArrayList<>();
        command.setArguments(Map.of("", "dinner", "a/", "1000", "d/", "2024", "c/", "Food"));

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addExpenseNoDate_success() {
        TransactionList transactionList = new TransactionList();
        AddExpenseCommand command = new AddExpenseCommand(transactionList);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String dateString = LocalDateTime.now().format(formatter);

        ArrayList<Transaction> expectedList = new ArrayList<>();
        Expense expectedExpense = new Expense(1000, "dinner", dateString, new Category("Food"));
        expectedList.add(expectedExpense);

        command.setArguments(Map.of("", "dinner", "a/", "1000", "c/", "Food"));

        command.execute();

        assertEquals(transactionList.getTransactions(), expectedList);
    }
}
