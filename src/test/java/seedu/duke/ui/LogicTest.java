package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.budget.Budget;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.logic.BudgetLogic;
import seedu.duke.logic.Logic;
import seedu.duke.storage.Storage;
import seedu.duke.util.Commons;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link Logic} class.
 * These tests ensure that various commands are processed correctly by the {@code matchCommand} method.
 */
public class LogicTest {

    private Logic logic;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Sets up the test environment by initializing the {@link AppUi} instance and its financial list.
     */
    @BeforeEach
    void setUp() {
        AppUi appUi = new AppUi();
        Storage storage = new Storage();
        Budget budget = new Budget();
        FinancialList financialList = new FinancialList();
        BudgetLogic budgetLogic = new BudgetLogic(budget, appUi);
        logic = new Logic(financialList, storage, appUi, budgetLogic);
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tests the behavior of the "list" command.
     * Ensures that the {@code matchCommand} method returns {@code true} and no exceptions are thrown.
     */
    @Test
    void testMatchCommand_listCommand() throws FinanceBuddyException {
        // Prepare command arguments for the "list" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put("command", "list");

        // Execute the command
        boolean result = logic.matchCommand("list", commandArguments);

        // Validate that the result is true and no exceptions are thrown
        assertTrue(result);
    }

    /**
     * Tests the behavior of the "seeAllExpenses" command.
     * Verifies that all expenses are displayed and that the output matches the expected format.
     */
    @Test
    void testMatchCommand_seeAllExpensesCommand() throws FinanceBuddyException {
        LocalDate date1 = LocalDate.of(2024, 10, 29);
        LocalDate date2 = LocalDate.of(2024, 10, 14);

        // add an expense to the financial list
        logic.financialList.addEntry(new Expense(100, "Lunch", date1, Expense.Category.FOOD));
        // add an income to the financial list
        logic.financialList.addEntry(new Income(100, "Salary", date2, Income.Category.SALARY));

        // Prepare command arguments for the "seeAllExpenses" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put(Commons.KEY_FIRST_ARGUMENT, "expense");

        // Execute the command
        logic.matchCommand("list", commandArguments);

        String output = outputStream.toString();

        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded expenses:" + System.lineSeparator() +
                "2. [Expense] - Lunch $ 100.00 (on " + date1.format(pattern) + ") [FOOD]" + System.lineSeparator()  +
                System.lineSeparator() +
                "Total count: 1" + System.lineSeparator() +
                System.lineSeparator() +
                "Total expense: $ 100.00" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Expense Category: FOOD ($100.00)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "No budget has been set." + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        // Validate that the expected output is equal to the actual output
        assertEquals(expectedOutput, output);
    }

    /**
     * Tests the behavior of the "expense" command.
     * Ensures that an expense entry is added to the financial list and the method returns {@code true}.
     */
    @Test
    void testMatchCommand_expenseCommand() throws FinanceBuddyException {
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put(Commons.KEY_FIRST_ARGUMENT, "Lunch");
        commandArguments.put(Commons.FLAG_AMOUNT, "12.00");

        boolean result = logic.matchCommand("expense", commandArguments);

        // Validate that the result is true and that the financial list has an entry
        assertTrue(result);
        assertEquals(1, logic.financialList.getEntryCount());
    }

    /**
     * Tests the behavior of the "income" command.
     * Ensures that an income entry is added to the financial list and the method returns {@code true}.
     */
    @Test
    void testMatchCommand_incomeCommand() throws FinanceBuddyException {
        // Prepare command arguments for the "income" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put(Commons.KEY_FIRST_ARGUMENT, "Salary");
        commandArguments.put(Commons.FLAG_AMOUNT, "2500.00");

        // Execute the command
        boolean result = logic.matchCommand("income", commandArguments);

        // Validate that the result is true and that the financial list has an entry
        assertTrue(result);
        assertEquals(1, logic.financialList.getEntryCount());
    }

    /**
     * Tests the behavior of the "edit" command.
     * Ensures that an existing financial entry can be edited and the changes are applied correctly.
     */
    @Test
    void testMatchCommand_editCommand() throws FinanceBuddyException {
        LocalDate date1 = LocalDate.of(2024, 10, 17);

        // Add an entry first to edit it later
        logic.financialList.addEntry(new Expense(100, "Initial Entry", date1, Expense.Category.UNCATEGORIZED));

        // Prepare command arguments for the "edit" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put(Commons.KEY_FIRST_ARGUMENT, "1");
        commandArguments.put(Commons.FLAG_AMOUNT, "25.00");
        commandArguments.put(Commons.FLAG_DESCRIPTION, "Edited Description");
        commandArguments.put(Commons.FLAG_DATE, "11/11/2011");

        // Execute the command
        boolean result = logic.matchCommand("edit", commandArguments);

        // Validate that the entry is edited
        assertTrue(result);
        FinancialEntry editedEntry = logic.financialList.getEntry(0);
        assertEquals(25.00, editedEntry.getAmount());
        assertEquals("Edited Description", editedEntry.getDescription());
        assertEquals(LocalDate.of(2011, 11, 11), editedEntry.getDate());
    }

    /**
     * Tests the behavior of the "delete" command.
     * Ensures that an entry can be deleted from the financial list and the method returns {@code true}.
     */
    @Test
    void testMatchCommand_deleteCommand() throws FinanceBuddyException {
        LocalDate date1 = LocalDate.of(2024, 9, 4);
        // Add an entry first to delete it later
        logic.financialList.addEntry(new Expense(100, "Entry to delete", date1, Expense.Category.UNCATEGORIZED));

        // Prepare command arguments for the "delete" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put(Commons.KEY_FIRST_ARGUMENT, "1");  // Deleting first entry

        // Execute the command
        boolean result = logic.matchCommand("delete", commandArguments);

        // Validate that the entry is deleted
        assertTrue(result);
        assertEquals(0, logic.financialList.getEntryCount());
    }

    /**
     * Tests the behavior of the "help" command.
     * Ensures that the help menu is displayed and the method returns {@code true}.
     */
    @Test
    void testMatchCommand_helpCommand() throws FinanceBuddyException {
        // Prepare command arguments for the "help" command
        HashMap<String, String> commandArguments = new HashMap<>();

        // Execute the command
        boolean result = logic.matchCommand("help", commandArguments);

        // Validate that the result is true (help does not modify the financial list)
        assertTrue(result);
    }

    /**
     * Tests the behavior of the "exit" command.
     * Ensures that the method returns {@code false} to indicate that the program should exit.
     */
    @Test
    void testMatchCommand_exitCommand() throws FinanceBuddyException {
        // Prepare command arguments for the "exit" command
        HashMap<String, String> commandArguments = new HashMap<>();

        // Execute the command
        boolean result = logic.matchCommand("exit", commandArguments);

        // Validate that the result is false (indicating the program should exit)
        assertFalse(result);
    }

    /**
     * Tests the behavior of an unrecognized command.
     * Ensures that unrecognized commands are handled gracefully and the method returns {@code true}.
     */
    @Test
    void testMatchCommand_unrecognizedCommand() throws FinanceBuddyException {
        // Prepare command arguments for an unrecognized command
        HashMap<String, String> commandArguments = new HashMap<>();

        // Execute the command
        boolean result = logic.matchCommand("unknownCommand", commandArguments);

        // Validate that an unrecognized command does not cause errors and returns true
        assertTrue(result);
    }

}
