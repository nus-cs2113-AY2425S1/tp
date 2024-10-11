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
 * Test class for SeeAllExpensesCommand.
 * This class contains unit tests for the SeeAllExpensesCommand class, 
 * which is responsible for displaying all recorded expenses.
 * 
 * <p>It includes the following tests:
 * <ul>
 *   <li>execute_noExpenses_printsNoRecordedExpenses: 
 *      Verifies that the command correctly handles the case where there are no recorded expenses.</li>
 *   <li>execute_withExpenses_printsAllExpenses: 
 *      Verifies that the command correctly displays all recorded expenses when they are 
 *      present in the financial list.</li>
 * </ul>
 * 
 * <p>Setup and teardown methods are provided to initialize and clean up the test environment:
 * <ul>
 *   <li>setUp: Initializes the FinancialList and SeeAllExpensesCommand objects, 
 *      and redirects System.out to a ByteArrayOutputStream for capturing output.</li>
 *   <li>tearDown: Restores the original System.out after each test.</li>
 * </ul>
 * 
 * <p>Dependencies:
 * <ul>
 *   <li>FinancialList: A list that holds financial entries.</li>
 *   <li>SeeAllExpensesCommand: The command being tested.</li>
 *   <li>FinancialEntry: Represents a financial entry (e.g., an expense).</li>
 *   <li>Expense: A specific type of FinancialEntry representing an expense.</li>
 * </ul>
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
