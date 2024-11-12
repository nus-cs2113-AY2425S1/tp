package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.util.Commons;

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
     * Helper function to help create a mixed list from an empty financial list.
     *
     * @param financialList Empty financial list to be filled.
     * @throws FinanceBuddyException when invalid parameters are passed into the financial list.
     */
    private void fillMixedList(FinancialList financialList) throws FinanceBuddyException {
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.of(2024, 10, 10),
                Expense.Category.FOOD));
        financialList.addEntry(new Income(3000.00, "salary", LocalDate.of(2024, 10, 10),
                Income.Category.SALARY));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.of(2024, 10, 10),
                Expense.Category.FOOD));
        financialList.addEntry(new Expense(20.00, "movie ticket", LocalDate.of(2024, 10, 10),
                Expense.Category.ENTERTAINMENT));
        financialList.addEntry(new Income(100.00, "allowance", LocalDate.of(2024, 11, 2),
                Income.Category.GIFT));
        financialList.addEntry(new Income(15.00, "ang pow money", LocalDate.of(2024, 9, 12),
                Income.Category.GIFT));
    }

    /**
     * Test the execute method with a mixed list of incomes and expenses.
     * Expects only income entries to be printed with index relative to
     * income entries only.
     */
    @Test
    void execute_mixedList_expectPrintedIncomes() throws FinanceBuddyException {
        testCommand = new SeeAllIncomesCommand(null, null);
        fillMixedList(financialList);
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                Commons.LINE_SEPARATOR + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "1. [Income] - ang pow money $ 15.00 (on 12/09/2024) [GIFT]" + System.lineSeparator() +
                "3. [Income] - salary $ 3000.00 (on 10/10/2024) [SALARY]" + System.lineSeparator() +
                "6. [Income] - allowance $ 100.00 (on 02/11/2024) [GIFT]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 3" + System.lineSeparator() +
                System.lineSeparator() +
                "Total income: $ 3115.00" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Income Category: SALARY ($3000.00)" + System.lineSeparator() +
                Commons.LINE_SEPARATOR + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    /**
     * Test the execute method with a list containing only expenses.
     * Expects a message indicating no incomes were found.
     */
    @Test
    void execute_onlyExpenseList_expectNothing() throws FinanceBuddyException {
        testCommand = new SeeAllIncomesCommand(null, null);
        financialList.addEntry(new Expense(3.50, "lunch", LocalDate.now(), Expense.Category.FOOD));
        financialList.addEntry(new Expense(4.50, "dinner", LocalDate.now(), Expense.Category.FOOD));
        financialList.addEntry(new Expense(20.00, "movie ticket", LocalDate.now(), Expense.Category.ENTERTAINMENT));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = Commons.LINE_SEPARATOR + System.lineSeparator() +
                "No incomes found." + System.lineSeparator() +
                Commons.LINE_SEPARATOR + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    /**
     * Test the execute method, specifying that only Incomes up to 10/10/2024 inclusive should be printed.
     */
    @Test
    void execute_mixedListBeforeCertainDate_expectPrintedIncomes() throws FinanceBuddyException {
        testCommand = new SeeAllIncomesCommand(null, LocalDate.of(2024, 10, 10));
        fillMixedList(financialList);
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                Commons.LINE_SEPARATOR + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "1. [Income] - ang pow money $ 15.00 (on 12/09/2024) [GIFT]" + System.lineSeparator() +
                "3. [Income] - salary $ 3000.00 (on 10/10/2024) [SALARY]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 2" + System.lineSeparator() +
                System.lineSeparator() +
                "Total income: $ 3015.00" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Income Category: SALARY ($3000.00)" + System.lineSeparator() +
                Commons.LINE_SEPARATOR + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    /**
     * Test the execute method, specifying that only Incomes starting from 10/10/24 inclusive should be printed.
     */
    @Test
    void execute_mixedListAfterCertainDate_expectPrintedIncomes() throws FinanceBuddyException {
        testCommand = new SeeAllIncomesCommand(LocalDate.of(2024, 10, 10), null);
        fillMixedList(financialList);
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                Commons.LINE_SEPARATOR + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "3. [Income] - salary $ 3000.00 (on 10/10/2024) [SALARY]" + System.lineSeparator() +
                "6. [Income] - allowance $ 100.00 (on 02/11/2024) [GIFT]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 2" + System.lineSeparator() +
                System.lineSeparator() +
                "Total income: $ 3100.00" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Income Category: SALARY ($3000.00)" + System.lineSeparator() +
                Commons.LINE_SEPARATOR + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    /**
     * Test the execute method, specifying that only Incomes
     * between 20/9/2024 and 10/10/24 inclusive should be printed.
     */
    @Test
    void execute_mixedListBeforeAndAfterCertainDate_expectPrintedIncomes() throws FinanceBuddyException {
        testCommand = new SeeAllIncomesCommand(LocalDate.of(2024, 9, 20), LocalDate.of(2024, 10, 10));
        fillMixedList(financialList);
        //add extra incomes for this specific test
        financialList.addEntry(new Income(15, "birthday money", LocalDate.of(2024, 9, 20),
                Income.Category.GIFT));
        financialList.addEntry(new Income(5, "voucher", LocalDate.of(2024, 10, 1),
                Income.Category.GIFT));
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                Commons.LINE_SEPARATOR + System.lineSeparator() +
                "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "2. [Income] - birthday money $ 15.00 (on 20/09/2024) [GIFT]" + System.lineSeparator() +
                "3. [Income] - voucher $ 5.00 (on 01/10/2024) [GIFT]" + System.lineSeparator() +
                "5. [Income] - salary $ 3000.00 (on 10/10/2024) [SALARY]" + System.lineSeparator() +
                System.lineSeparator() +
                "Total count: 3" + System.lineSeparator() +
                System.lineSeparator() +
                "Total income: $ 3020.00" + System.lineSeparator() +
                System.lineSeparator() +
                "Highest Income Category: SALARY ($3000.00)" + System.lineSeparator() +
                Commons.LINE_SEPARATOR + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }
}
