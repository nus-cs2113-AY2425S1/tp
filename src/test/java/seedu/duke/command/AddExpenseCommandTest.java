package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.FinancialList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the AddExpenseCommand.
 * This class contains unit tests to verify the functionality of the AddExpenseCommand
 * when adding a new expense to the financial list.
 */
class AddExpenseCommandTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private FinancialList financialList;
    private AddExpenseCommand addExpenseCommand;

    /**
     * Set up the test environment before each test.
     * Initializes the FinancialList and redirects System.out to capture console output.
     */
    @BeforeEach
    void setUp() {
        financialList = new FinancialList();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Clean up the test environment after each test.
     * Restores the original System.out PrintStream.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Test the execute method of AddExpenseCommand.
     * Verifies that the expense is added to the financial list and that the correct output is printed.
     */
    @Test
    void execute_addExpense_expectAddedToFinancialList() throws FinanceBuddyException {
        String specificDate = "14/10/24";
        addExpenseCommand = new AddExpenseCommand(50.00, "groceries", specificDate);
        addExpenseCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Got it! I've added this expense:" + System.lineSeparator() +
                "[Expense] - groceries $ 50.00 (on "+ specificDate + ")" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(1, financialList.getEntryCount());  // Verify the entry count
        assertEquals(expectedOutput, output);  // Verify the printed output
    }

    /**
     * Test the execute method of AddExpenseCommand without a date.
     * Verifies that the expense is added to the financial list with the current system date.
     */
    @Test
    void execute_addExpenseWithoutDate_expectAddedToFinancialListWithCurrentDate() throws FinanceBuddyException {
        // Use current system date
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        addExpenseCommand = new AddExpenseCommand(30.00, "lunch", null);
        addExpenseCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Got it! I've added this expense:" + System.lineSeparator() +
                "[Expense] - lunch $ 30.00 (on " + currentDate + ")" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(1, financialList.getEntryCount()); 
        assertEquals(expectedOutput, output);
    }

    /**
     * Test adding multiple expenses to the financial list.
     * Verifies that all expenses are added correctly and that the output is printed for each.
     */
    @Test
    void execute_addMultipleExpenses_expectAllAddedToFinancialList() throws FinanceBuddyException {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        String specificDate = "12/10/24";

        // Add first expense without a specific date
        addExpenseCommand = new AddExpenseCommand(30.00, "lunch", null);
        addExpenseCommand.execute(financialList);

        // Add second expense with a specific date
        addExpenseCommand = new AddExpenseCommand(100.00, "electronics", specificDate);
        addExpenseCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Got it! I've added this expense:" + System.lineSeparator() +
                "[Expense] - lunch $ 30.00 (on " + currentDate + ")" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Got it! I've added this expense:" + System.lineSeparator() +
                "[Expense] - electronics $ 100.00 (on " + specificDate + ")" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(2, financialList.getEntryCount());
        assertEquals(expectedOutput, output);
    }

    /**
     * Test the execute method of AddExpenseCommand with an empty date string.
     * Verifies that the command prints an error message when an empty date is provided.
     */
    @Test
    void execute_addExpenseWithEmptyDate_expectErrorMessage() {
        String emptyDate = "";  // Empty date string

        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            addExpenseCommand = new AddExpenseCommand(50.00, "shopping", emptyDate);
            addExpenseCommand.execute(financialList);
        });

        // Verify the error message
        assertEquals("Invalid date format. Please use 'dd/MM/yy'.", exception.getMessage());
    }

}
