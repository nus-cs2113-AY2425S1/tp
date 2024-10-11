package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AppUiTest {

    private AppUi appUi;

    @BeforeEach
    void setUp() {
        appUi = new AppUi();
        appUi.financialList = new FinancialList();
    }

    @Test
    void testMatchCommand_listCommand() {
        // Prepare command arguments for the "list" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put("command", "list");

        // Execute the command
        boolean result = appUi.matchCommand("list", commandArguments);

        // Validate that the result is true and no exceptions are thrown
        assertTrue(result);
    }

    @Test
    void testMatchCommand_expenseCommand() {
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put("argument", "Lunch");
        commandArguments.put("/a", "12.00");

        boolean result = appUi.matchCommand("expense", commandArguments);

        // Validate that the result is true and that the financial list has an entry
        assertTrue(result);
        assertEquals(1, appUi.financialList.getEntryCount());  // Ensure one entry is added
    }

    @Test
    void testMatchCommand_incomeCommand() {
        // Prepare command arguments for the "income" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put("argument", "Salary");
        commandArguments.put("/a", "2500.00");

        // Execute the command
        boolean result = appUi.matchCommand("income", commandArguments);

        // Validate that the result is true and that the financial list has an entry
        assertTrue(result);
        assertEquals(1, appUi.financialList.getEntryCount());  // Ensure one entry is added
    }

    @Test
    void testMatchCommand_editCommand() {
        // Add an entry first to edit it later
        appUi.financialList.addEntry(new Expense(100, "Initial Entry"));

        // Prepare command arguments for the "edit" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put("argument", "0");  // Editing first entry
        commandArguments.put("/a", "25.00");
        commandArguments.put("/des", "Edited Description");

        // Execute the command
        boolean result = appUi.matchCommand("edit", commandArguments);

        // Validate that the entry is edited
        assertTrue(result);
        FinancialEntry editedEntry = appUi.financialList.getEntry(0);
        assertEquals(25.00, editedEntry.getAmount());
        assertEquals("Edited Description", editedEntry.getDescription());
    }

    @Test
    void testMatchCommand_deleteCommand() {
        // Add an entry first to delete it later
        appUi.financialList.addEntry(new Expense(100, "Entry to delete"));

        // Prepare command arguments for the "delete" command
        HashMap<String, String> commandArguments = new HashMap<>();
        commandArguments.put("argument", "1");  // Deleting first entry

        // Execute the command
        boolean result = appUi.matchCommand("delete", commandArguments);

        // Validate that the entry is deleted
        assertTrue(result);
        assertEquals(0, appUi.financialList.getEntryCount());  // Ensure no entries remain
    }

    @Test
    void testMatchCommand_helpCommand() {
        // Prepare command arguments for the "help" command
        HashMap<String, String> commandArguments = new HashMap<>();

        // Execute the command
        boolean result = appUi.matchCommand("help", commandArguments);

        // Validate that the result is true (help does not modify the financial list)
        assertTrue(result);
    }

    @Test
    void testMatchCommand_exitCommand() {
        // Prepare command arguments for the "exit" command
        HashMap<String, String> commandArguments = new HashMap<>();

        // Execute the command
        boolean result = appUi.matchCommand("exit", commandArguments);

        // Validate that the result is false (indicating the program should exit)
        assertFalse(result);
    }

    @Test
    void testMatchCommand_unrecognizedCommand() {
        // Prepare command arguments for an unrecognized command
        HashMap<String, String> commandArguments = new HashMap<>();

        // Execute the command
        boolean result = appUi.matchCommand("unknownCommand", commandArguments);

        // Validate that an unrecognized command does not cause errors and returns true
        assertTrue(result);
    }
}
