package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.transaction.TransactionList;

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

        assertEquals("Income added successfully!", result.get(0));
    }

    @Test
    public void execute_addIncomeInvalidDate_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "1000", "d/", "2024"));
        List<String> result = command.execute();

        assertEquals("Error creating Income!: Your date and/or time is invalid!", result.get(0));
    }

    @Test
    public void execute_addIncomeInvalidAmount_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "test", "d/", "2024-10-01"));
        List<String> result = command.execute();

        assertEquals("Error creating Income!: Invalid Amount", result.get(0));
    }

    @Test
    public void execute_addIncomeNoAmount_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary"));
        List<String> result = command.execute();

        assertEquals("Lack mandatory arguments.", result.get(0));
    }

    @Test
    public void execute_addIncomeNoDescription_success() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "", "a/", "100", "d/", "2024-10-01 1800"));
        List<String> result = command.execute();

        assertEquals("Income added successfully!", result.get(0));
    }
}
