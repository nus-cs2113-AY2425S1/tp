package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.transaction.TransactionList;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AddIncomeCommandTest {

    @Test
    public void execute_addIncomeWithAllValidFields_success() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "1000", "d/", "2024-10-01 1800"));
        List<String> result = command.execute();

        assertEquals("Income added successfully!", result.get(0));
    }

    @Test
    public void execute_addIncomeWithInvalidDate_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "1000", "d/", "2024"));
        List<String> result = command.execute();

        assertEquals("Error creating Income!: Your date and/or time is invalid!", result.get(0));
    }

    @Test
    public void execute_addIncomeWithInvalidAmount_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary", "a/", "test", "d/", "2024-10-01"));
        List<String> result = command.execute();

        assertEquals("Error creating Income!: Invalid Amount", result.get(0));
    }

    @Test
    public void execute_addIncomeWithNoAmount_exceptionThrown() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "Salary"));
        List<String> result = command.execute();

        assertEquals("Lack mandatory arguments.", result.get(0));
    }

    @Test
    public void execute_addIncomeWithNoDescription_success() {
        TransactionList transactionList = new TransactionList();
        AddIncomeCommand command = new AddIncomeCommand(transactionList);

        command.setArguments(Map.of("", "", "a/", "100", "d/", "2024-10-01 1800"));
        List<String> result = command.execute();

        assertEquals("Income added successfully!", result.get(0));
    }
}