package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financial.FinancialList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the HelpCommand.
 */
class HelpCommandTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private FinancialList financialList;
    private HelpCommand testCommand;

    /**
     * Set up the test environment before each test.
     * Initializes the FinancialList and SeeAllIncomesCommand instances,
     * and redirects System.out to capture console output.
     */
    @BeforeEach
    void setUp() {
        financialList = new FinancialList();
        testCommand = new HelpCommand();
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
     * Test the execute method.
     * Expects help menu to be printed out.
     */
    @Test
    void execute_printHelpMenu() {
        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "--------------------------------------------\n" +
                "List of commands:\n" +
                "--------------------------------------------\n" +
                "1. list [income|expense] [/from START_DATE] [/to END_DATE]\n" +
                "   - Shows a list of logged transactions\n" +
                "    - Also displays categories with highest income/expenditure, monthly budget and balance\n" +
                "    - Optional: Specify 'income' or 'expense' to filter the list\n" +
                "    - Optional: Specify start/end date to only list transactions before/after specified dates\n" +
                "2. expense DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]\n" +
                "   - Adds a new expense with an optional date and category\n" +
                "    - Categories include: FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, OTHER, UNCATEGORIZED\n" +
                "3. income DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]\n" +
                "   - Adds a new income with an optional date and category\n" +
                "    - Categories include: SALARY, INVESTMENT, GIFT, OTHER, UNCATEGORIZED\n" +
                "4. edit [INDEX] [/des DESCRIPTION] [/a AMOUNT] [/d DATE] [/c CATEGORY]\n" +
                "   - Edits the transaction at the specified INDEX with optional fields\n" +
                "    - If no INDEX is specified, last amended transaction will be edited by default\n" +
                "5. delete [INDEX] [/to END_INDEX]\n" +
                "   - Deletes the transaction at the specified INDEX\n" +
                "    - If no INDEX is specified, last last amended transaction will be deleted by default\n" +
                "    - If /to flag is included: Deletes transactions INDEX to END_INDEX inclusive\n" +
                "    - Bonus: delete all - deletes all transactions\n" +
                "6. budget BUDGET\n" +
                "   - Set/modify your monthly budget\n" +
                "   - Delete set budget by setting BUDGET to 0\n" +
                "7. exit\n" +
                "   - Exits the program\n" +
                "8. help\n" +
                "   - Shows a list of all valid commands\n" +
                "--------------------------------------------\n";

        assertEquals(expectedOutput, output);
    }
}
