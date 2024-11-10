package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the SeeAllEntriesCommand.
 * This class contains unit tests to verify the functionality of the SeeAllEntriesCommand
 * when executed on a FinancialList containing various financial entries.
 */
class SeeAllEntriesCommandTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private FinancialList financialList;
    private SeeAllEntriesCommand testCommand;

    /**
     * Set up the test environment before each test.
     * Initializes the FinancialList and SeeAllEntriesCommand instances,
     * and redirects System.out to capture console output.
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
     * Helper function to help create a mixed list from an empty financial list.
     *
     * @param financialList Empty financial list to be filled.
     * @throws FinanceBuddyException when invalid parameters are passed into the financial list.
     */
    private void fillMixedList(FinancialList financialList) throws FinanceBuddyException {
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.of(2024, 10, 23),
                Expense.Category.FOOD));
        financialList.addEntry(new Income(3000.00, "salary", LocalDate.of(2024, 11, 2),
                Income.Category.SALARY));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.of(2024, 9, 1),
                Expense.Category.FOOD));
        financialList.addEntry(new Expense(20.00, "movie", LocalDate.of(2024, 10, 1),
                Expense.Category.ENTERTAINMENT));
        financialList.addEntry(new Income(100.00, "allowance", LocalDate.of(2024, 10, 10),
                Income.Category.GIFT));
    }
    /**
     * Test the execute method with a mixed list of incomes and expenses.
     * Expects all entries to be printed with indexes.
     */
    @Test
    void execute_mixedList_expectPrintedList() throws FinanceBuddyException {
        testCommand = new SeeAllEntriesCommand(null, null);
        fillMixedList(financialList);

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded entries:" + System.lineSeparator() +
                "1. [Expense] - dinner $ 4.50 (on 01/09/2024) [FOOD]" + System.lineSeparator() +
                "2. [Expense] - movie $ 20.00 (on 01/10/2024) [ENTERTAINMENT]" + System.lineSeparator() +
                "3. [Income] - allowance $ 100.00 (on 10/10/2024) [GIFT]" + System.lineSeparator() +
                "4. [Expense] - lunch $ 3.50 (on 23/10/2024) [FOOD]" + System.lineSeparator() +
                "5. [Income] - salary $ 3000.00 (on 02/11/2024) [SALARY]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 5" + System.lineSeparator() +
                System.lineSeparator() +
                "Net cashflow: $ 3072.00" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Expense Category: ENTERTAINMENT ($20.00)" + System.lineSeparator() +
                "Highest Income Category: SALARY ($3000.00)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(
                expectedOutput.trim().replaceAll("\\s+", " "),
                output.trim().replaceAll("\\s+", " ")
        );
    }

    /**
     * Test the execute method with an empty list.
     * Expects a message indicating no entries were found.
     */
    @Test
    void execute_emptyList_expectNothing() throws FinanceBuddyException {
        testCommand = new SeeAllEntriesCommand(null, null);
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "No entries found." + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(
                expectedOutput.trim().replaceAll("\\s+", " "),
                output.trim().replaceAll("\\s+", " ")
        );
    }

    /**
     * Test the execute method, specifying that only Entries up to 10/10/2024 inclusive should be printed.
     */
    @Test
    void execute_listBeforeCertainDate_expectSomeEntries() throws FinanceBuddyException {
        testCommand = new SeeAllEntriesCommand(null, LocalDate.of(2024, 10, 10));
        fillMixedList(financialList);
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded entries:" + System.lineSeparator() +
                "1. [Expense] - dinner $ 4.50 (on 01/09/2024) [FOOD]" + System.lineSeparator() +
                "2. [Expense] - movie $ 20.00 (on 01/10/2024) [ENTERTAINMENT]" + System.lineSeparator() +
                "3. [Income] - allowance $ 100.00 (on 10/10/2024) [GIFT]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 3" + System.lineSeparator() +
                System.lineSeparator() +
                "Net cashflow: $ 75.50" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Expense Category: ENTERTAINMENT ($20.00)" + System.lineSeparator() +
                "Highest Income Category: GIFT ($100.00)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();
        assertEquals(
                expectedOutput.trim().replaceAll("\\s+", " "),
                output.trim().replaceAll("\\s+", " ")
        );
    }

    /**
     * Test the execute method, specifying that only Entries starting from 10/10/2024 inclusive should be printed.
     */
    @Test
    void execute_listAfterCertainDate_expectSomeEntries() throws FinanceBuddyException {
        testCommand = new SeeAllEntriesCommand(LocalDate.of(2024, 10, 10), null);
        fillMixedList(financialList);
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded entries:" + System.lineSeparator() +
                "3. [Income] - allowance $ 100.00 (on 10/10/2024) [GIFT]" + System.lineSeparator() +
                "4. [Expense] - lunch $ 3.50 (on 23/10/2024) [FOOD]" + System.lineSeparator() +
                "5. [Income] - salary $ 3000.00 (on 02/11/2024) [SALARY]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 3" + System.lineSeparator() +
                System.lineSeparator() +
                "Net cashflow: $ 3096.50" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Expense Category: FOOD ($3.50)" + System.lineSeparator() +
                "Highest Income Category: SALARY ($3000.00)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() ;
        assertEquals(
                expectedOutput.trim().replaceAll("\\s+", " "),
                output.trim().replaceAll("\\s+", " ")
        );
    }

    /**
     * Test the execute method, specifying that only Entries
     * between 01/10/2024 and 01/11/2024 inclusive should be printed.
     */
    @Test
    void execute_listBeforeAndAfterCertainDate_expectSomeEntries() throws FinanceBuddyException {
        testCommand = new SeeAllEntriesCommand(LocalDate.of(2024, 10, 1), LocalDate.of(2024, 11, 1));
        fillMixedList(financialList);
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded entries:" + System.lineSeparator() +
                "2. [Expense] - movie $ 20.00 (on 01/10/2024) [ENTERTAINMENT]" + System.lineSeparator() +
                "3. [Income] - allowance $ 100.00 (on 10/10/2024) [GIFT]" + System.lineSeparator() +
                "4. [Expense] - lunch $ 3.50 (on 23/10/2024) [FOOD]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 3" + System.lineSeparator() +
                System.lineSeparator() +
                "Net cashflow: $ 76.50" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Expense Category: ENTERTAINMENT ($20.00)" + System.lineSeparator() +
                "Highest Income Category: GIFT ($100.00)" + System.lineSeparator()+
                "--------------------------------------------" + System.lineSeparator();
        assertEquals(
                expectedOutput.trim().replaceAll("\\s+", " "),
                output.trim().replaceAll("\\s+", " ")
        );
    }

    @Test
    void execute_nullFinancialList_expectNull() throws FinanceBuddyException {
        FinancialList nullList = null;
        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            SeeAllEntriesCommand seeAllEntriesCommand = new SeeAllEntriesCommand(null, null);
            seeAllEntriesCommand.execute(nullList);
        });

        // Verify the error message
        assertEquals("Financial list cannot be null", exception.getMessage());
    }
}
