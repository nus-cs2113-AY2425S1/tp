package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the SeeAllExpensesCommand class.
 * 
 * This class tests the functionality of the SeeAllExpensesCommand, which is responsible for displaying all recorded expenses in the FinancialList.
 * 
 * The tests include:
 * - Setting up and tearing down the test environment.
 * - Testing the command execution when there are no expenses recorded.
 * - Testing the command execution when there are expenses recorded.
 * 
 * Methods:
 * - setUp(): Initializes the test environment before each test.
 * - tearDown(): Restores the original System.out after each test.
 * - execute_noExpenses_printsNoRecordedExpenses(): Verifies that the command correctly handles the case when there are no expenses.
 * - execute_withExpenses_printsAllExpenses(): Verifies that the command correctly handles the case when there are expenses recorded.
 */
public class SeeAllExpensesCommandTest {

    private FinancialList financialList;
    private SeeAllExpensesCommand seeAllExpensesCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        financialList = new FinancialList();
        seeAllExpensesCommand = new SeeAllExpensesCommand();
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void execute_noExpenses_printsNoRecordedExpenses() {
        seeAllExpensesCommand.execute(financialList);
        assertEquals("No recorded expenses found." + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void execute_withExpenses_printsAllExpenses() {
        FinancialEntry expense1 = new Expense(10.0, "food");
        FinancialEntry expense2 = new Expense(5.0, "transport");
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);

        seeAllExpensesCommand.execute(financialList);

        String expectedOutput = "Here's a list of all recorded expenses:" + System.lineSeparator() +
                "1. " + expense1 + System.lineSeparator() +
                "2. " + expense2 + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
