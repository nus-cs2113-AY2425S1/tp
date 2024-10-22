package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the SeeAllIncomesCommand.
 * This class contains unit tests to verify the functionality of the SeeAllIncomesCommand
 * when executed on a FinancialList containing various financial entries.
 */
class SeeAllIncomesCommandTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private FinancialList financialList;
    private SeeAllIncomesCommand testCommand;

    /**
     * Set up the test environment before each test.
     * Initializes the FinancialList and SeeAllIncomesCommand instances,
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
     * Test the execute method with a mixed list of incomes and expenses.
     * Expects only income entries to be printed with index relative to
     * income entries only.
     */
    @Test
    void execute_mixedList_expectPrintedIncomes() {
        testCommand = new SeeAllIncomesCommand(null, null);
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.of(24,10,22)));
        financialList.addEntry(new Income(3000.00, "salary", LocalDate.of(24,10,22)));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.of(24,10,22)));
        financialList.addEntry(new Expense(20.00, "movie ticket", LocalDate.of(24,10,22)));
        financialList.addEntry(new Income(100.00, "allowance", LocalDate.of(24,10,22)));
        financialList.addEntry(new Income(15.00, "ang pow money", LocalDate.of(24,10,22)));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "1. [Income] - salary $ 3000.00 (on 22/10/24)" + System.lineSeparator() +
                "2. [Income] - allowance $ 100.00 (on 22/10/24)" + System.lineSeparator() +
                "3. [Income] - ang pow money $ 15.00 (on 22/10/24)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    /**
     * Test the execute method with a list containing only expenses.
     * Expects a message indicating no incomes were found.
     */
    @Test
    void execute_onlyExpenseList_expectNothing() {
        testCommand = new SeeAllIncomesCommand(null, null);
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.now()));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.now()));
        financialList.addEntry(new Expense(20.00, "movie ticket", LocalDate.now()));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "No recorded incomes found." + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    void execute_mixedListBeforeCertainDate_expectPrintedIncomes() {
        testCommand = new SeeAllIncomesCommand(null, LocalDate.of(24, 10, 10));
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Income(3000.00, "salary", LocalDate.of(24, 10, 1)));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Expense(20.00, "movie ticket", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Income(100.00, "allowance", LocalDate.of(24, 11, 2)));
        financialList.addEntry(new Income(15.00, "ang pow money", LocalDate.of(24, 9, 12)));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "1. [Income] - salary $ 3000.00 (on 01/10/24)" + System.lineSeparator() +
                "2. [Income] - ang pow money $ 15.00 (on 12/09/24)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    void execute_mixedListAfterCertainDate_expectPrintedIncomes() {
        testCommand = new SeeAllIncomesCommand(LocalDate.of(24, 10, 10), null);
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Income(3000.00, "salary", LocalDate.of(24, 10, 1)));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Expense(20.00, "movie ticket", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Income(100.00, "allowance", LocalDate.of(24, 11, 2)));
        financialList.addEntry(new Income(15.00, "ang pow money", LocalDate.of(24, 9, 12)));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "1. [Income] - allowance $ 100.00 (on 02/11/24)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    void execute_mixedListBeforeAndAfterCertainDate_expectPrintedIncomes() {
        testCommand = new SeeAllIncomesCommand(LocalDate.of(24, 9, 20), LocalDate.of(24, 10, 10));
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Income(3000.00, "salary", LocalDate.of(24, 10, 1)));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Expense(20.00, "movie ticket", LocalDate.of(24, 10, 10)));
        financialList.addEntry(new Income(100.00, "allowance", LocalDate.of(24, 11, 2)));
        financialList.addEntry(new Income(15.00, "ang pow money", LocalDate.of(24, 9, 12)));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                "--------------------------------------------" + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "1. [Income] - salary $ 3000.00 (on 01/10/24)" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }
}
