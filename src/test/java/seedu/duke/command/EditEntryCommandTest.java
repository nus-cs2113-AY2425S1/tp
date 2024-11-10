package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.util.Commons;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;



class EditEntryCommandTest {

    private FinancialList financialList;

    @BeforeEach
    void setUp() throws FinanceBuddyException {
        financialList = new FinancialList();
        financialList.addEntry(new Expense(100.0, "Initial Entry", LocalDate.now(), Expense.Category.UNCATEGORIZED));
    }


    /**
     * Tests the EditEntryCommand when the financial list is null.
     * This test ensures that the command throws a FinanceBuddyException
     * with the appropriate message when attempting to execute with a null financial list.
     *
     * The test creates an EditEntryCommand with sample data and then attempts to execute it
     * with a null financial list. It verifies that a FinanceBuddyException is thrown and
     * that the exception message contains the expected message "Financial list cannot be null".
     */
    @Test
    void testEditEntryCommand_nullFinancialList() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/2023",
                Expense.Category.FOOD);

        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            command.execute(null);
        });

        String expectedMessage = "Financial list cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the EditEntryCommand to ensure that it correctly edits an existing expense entry in the financial list.
     * 
     * This test creates an EditEntryCommand with specific parameters and executes it on the financial list.
     * It then verifies that the entry count remains the same, and that the entry's amount, description, date,
     * and category have been updated to the new values provided in the command.
     * 
     * @throws FinanceBuddyException if there is an error executing the command
     */
    @Test 
    void testEditEntryCommand_editExpense() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/2023",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Groceries", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(0)).getCategory());
    }

    /**
     * Tests the EditEntryCommand's ability to edit an existing expense entry's category.
     * 
     * <p>This test adds an initial expense entry to the financial list, then creates and executes
     * an EditEntryCommand to modify the entry's amount, description, date, and category. 
     * It verifies that the entry count remains the same, and that the entry's details are 
     * updated correctly and also inserted into the financial list in ascending order of date.</p>
     * 
     * @throws FinanceBuddyException if there is an error during the execution of the command
     */
    @Test
    void testEditEntryCommand_editExpenseCategory() throws FinanceBuddyException {
        financialList.addEntry(new Expense(100.0, "Initial Entry", LocalDate.now()));
        EditEntryCommand command = new EditEntryCommand(2, 50.0, "Salary", "01/10/2023",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(2, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Salary", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(0)).getCategory());
    }

    /**
     * Tests the EditEntryCommand's ability to edit the date of an entry in the financial list.
     * 
     * <p>This test creates an EditEntryCommand with specified parameters and executes it on the 
     * financial list. It then verifies that the entry count remains the same, and that the 
     * entry's amount, description, date, and category are updated correctly.</p>
     * 
     * @throws FinanceBuddyException if there is an error executing the command
     */
    @Test
    void testEditEntryCommand_editDate() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/2023",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Groceries", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(0)).getCategory());
    }

    /**
     * Tests the EditEntryCommand class by performing multiple edits on a financial entry.
     * 
     * <p>This test case performs the following steps:
     * <ol>
     *   <li>Creates an EditEntryCommand to edit an entry with specific details and executes it.</li>
     *   <li>Asserts that the entry count is 1 and verifies the details of the edited entry.</li>
     *   <li>Creates another EditEntryCommand to edit the same entry with new details and executes it.</li>
     *   <li>Asserts that the entry count is still 1 and verifies the updated details of the entry.</li>
     * </ol>
     * 
     * @throws FinanceBuddyException if there is an error executing the command
     */
    @Test 
    void testEditEntryCommand_multipleEdits() throws FinanceBuddyException {
        EditEntryCommand command = new EditEntryCommand(1, 50.0, "Groceries", "01/10/2023",
                Expense.Category.FOOD);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(50.0, financialList.getEntry(0).getAmount());
        assertEquals("Groceries", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.FOOD, ((Expense) financialList.getEntry(0)).getCategory());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        command = new EditEntryCommand(1, 100.0, "Initial Entry", LocalDate.now().format(formatter),
                Expense.Category.UTILITIES);
        command.execute(financialList);

        assertEquals(1, financialList.getEntryCount());
        assertEquals(100.0, financialList.getEntry(0).getAmount());
        assertEquals("Initial Entry", financialList.getEntry(0).getDescription());
        assertEquals(LocalDate.now(), financialList.getEntry(0).getDate());
        assertEquals(Expense.Category.UTILITIES, ((Expense) financialList.getEntry(0)).getCategory());
    }

    /**
     * Tests the EditEntryCommand with an index out of bounds of the financial List.
     * Ensures that the entry count in the financial list remains unchanged.
     */
    @Test
    void execute_editOutOfBoundsIndex_expectErrorMessage() {
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            EditEntryCommand editEntryCommand = new EditEntryCommand(0, 5, "Groceries",
                    "01/10/2023", Expense.Category.FOOD);
            editEntryCommand.execute(financialList);
        });

        assertEquals(Commons.ERROR_MESSAGE_OUT_OF_BOUNDS_INDEX, exception.getMessage());
        assertEquals(1, financialList.getEntryCount());
    }

    /**
     * Test the execute method of EditEntryCommand with a negative amount.
     * Verifies that an exception is thrown and entry is unchanged.
     */
    @Test
    void execute_setAmountToNegative_expectErrorMessage() throws FinanceBuddyException {
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            EditEntryCommand editEntryCommand = new EditEntryCommand(1, -5, "Groceries",
                    "01/10/2023", Expense.Category.FOOD);
            editEntryCommand.execute(financialList);
        });

        // Verify error message
        assertEquals("Invalid amount. Amount must be $0.01 or greater.", exception.getMessage());
        // Verify entry is unchanged
        FinancialEntry entry = financialList.getEntry(0);
        assertEquals("Initial Entry", entry.getDescription());
        assertEquals(100.0, entry.getAmount());
        assertEquals(LocalDate.now(), entry.getDate());
        assertEquals(Expense.Category.UNCATEGORIZED, ((Expense) entry).getCategory());
    }

    /**
     * Test the execute method of EditEntryCommand with a very small amount.
     * Verifies that an exception is thrown and entry is unchanged.
     */
    @Test
    void execute_setAmountToVerySmallAmount_expectErrorMessage() throws FinanceBuddyException {
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            EditEntryCommand editEntryCommand = new EditEntryCommand(1, 0.0001, "Groceries",
                    "01/10/2023", Expense.Category.FOOD);
            editEntryCommand.execute(financialList);
        });

        // Verify error message
        assertEquals("Invalid amount. Amount must be $0.01 or greater.", exception.getMessage());
        // Verify entry is unchanged
        FinancialEntry entry = financialList.getEntry(0);
        assertEquals("Initial Entry", entry.getDescription());
        assertEquals(100.0, entry.getAmount());
        assertEquals(LocalDate.now(), entry.getDate());
        assertEquals(Expense.Category.UNCATEGORIZED, ((Expense) entry).getCategory());
    }

    /**
     * Test the execute method of EditEntryCommand with a very large amount.
     * Verifies that an exception is thrown and entry is unchanged.
     */
    @Test
    void execute_setAmountToVeryLarge_expectErrorMessage() throws FinanceBuddyException {
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            EditEntryCommand editEntryCommand = new EditEntryCommand(1, 10000000, "Groceries",
                    "01/10/2023", Expense.Category.FOOD);
            editEntryCommand.execute(financialList);
        });

        // Verify error message
        assertEquals("Invalid amount. Amount must be $9999999.00 or less.", exception.getMessage());
        // Verify entry is unchanged
        FinancialEntry entry = financialList.getEntry(0);
        assertEquals("Initial Entry", entry.getDescription());
        assertEquals(100.0, entry.getAmount());
        assertEquals(LocalDate.now(), entry.getDate());
        assertEquals(Expense.Category.UNCATEGORIZED, ((Expense) entry).getCategory());
    }

    /**
     * Test the execute method of EditEntryCommand with a date after current system date.
     * Verifies that an exception is thrown and entry is unchanged.
     */
    @Test
    void execute_setDateToFutureDate_expectErrorMessage() throws FinanceBuddyException {
        LocalDate laterDate = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String laterDateAsString = laterDate.format(formatter);

        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            EditEntryCommand editEntryCommand = new EditEntryCommand(1, 10, "Groceries",
                    laterDateAsString, Expense.Category.FOOD);
            editEntryCommand.execute(financialList);
        });

        // Verify error message
        assertEquals("Entered date cannot be after current date.", exception.getMessage());
        // Verify entry is unchanged
        FinancialEntry entry = financialList.getEntry(0);
        assertEquals("Initial Entry", entry.getDescription());
        assertEquals(100.0, entry.getAmount());
        assertEquals(LocalDate.now(), entry.getDate());
        assertEquals(Expense.Category.UNCATEGORIZED, ((Expense) entry).getCategory());
    }

    /**
     * Test the execute method of EditEntryCommand with an invalidly formatted date.
     * Verifies that an exception is thrown and entry is unchanged.
     */
    @Test
    void execute_setDateInvalidly_expectErrorMessage() throws FinanceBuddyException {
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            EditEntryCommand editEntryCommand = new EditEntryCommand(1, 5, "Groceries",
                    "01.10.23", Expense.Category.FOOD);
            editEntryCommand.execute(financialList);
        });

        // Verify error message
        assertEquals("Invalid date format. Please use 'dd/MM/yyyy'.", exception.getMessage());
        // Verify entry is unchanged
        FinancialEntry entry = financialList.getEntry(0);
        assertEquals("Initial Entry", entry.getDescription());
        assertEquals(100.0, entry.getAmount());
        assertEquals(LocalDate.now(), entry.getDate());
        assertEquals(Expense.Category.UNCATEGORIZED, ((Expense) entry).getCategory());
    }
}
