package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.message.ErrorMessages;
import seedu.transaction.Income;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddIncomeCommandTest {

    @Test
    public void execute_addIncomeAllValidFields_success() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "1000", "d/", "2024-10-01 1800"));
        List<String> result = command.execute();

        ArrayList<Transaction> expectedList = new ArrayList<>();
        Income expectedIncome = new Income(1000, "Salary", "2024-10-01 1800");
        expectedList.add(expectedIncome);
        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addIncomeInvalidDate_noAdding() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "1000", "d/", "2024"));
        command.execute();

        ArrayList<Transaction> expectedList = new ArrayList<>();
        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addIncomeInvalidAmount_noAdding() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "test", "d/", "2024-10-01"));
        command.execute();

        ArrayList<Transaction> expectedList = new ArrayList<>();
        assertEquals(transactionList.getTransactions(), expectedList);
    }

    @Test
    public void execute_addIncomeNoAmount_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary"));
        List<String> result = command.execute();

        assertEquals(ErrorMessages.LACK_ARGUMENTS_ERROR_MESSAGE, result.get(0));
    }

    @Test
    public void execute_addIncomeNoDescription_success() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "", "a/", "100", "d/", "2024-10-01 1800"));

        command.execute();

        ArrayList<Transaction> expectedList = new ArrayList<>();
        Income expectedIncome = new Income(100, "", "2024-10-01 1800");
        expectedList.add(expectedIncome);
        assertEquals(transactionList.getTransactions(), expectedList);
    }
}