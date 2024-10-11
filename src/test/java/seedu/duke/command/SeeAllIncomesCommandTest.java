package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeeAllIncomesCommandTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private FinancialList financialList;
    private SeeAllIncomesCommand testCommand;

    @BeforeEach
    void setUp() {
        financialList = new FinancialList();
        testCommand = new SeeAllIncomesCommand();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void execute_mixedList_expectPrintedIncomes() {

        financialList.addEntry(new Expense(3.50, "lunch"));
        financialList.addEntry(new Income(3000.00, "salary"));
        financialList.addEntry(new Expense(4.50, "dinner"));
        financialList.addEntry(new Expense(20.00, "movie ticket"));
        financialList.addEntry(new Income(100.00, "allowance"));
        financialList.addEntry(new Income(15.00, "ang pow money"));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "Here's a list of all recorded incomes:" + System.lineSeparator() +
                "1. [Income] - salary $ 3000.00" + System.lineSeparator() +
                "2. [Income] - allowance $ 100.00" + System.lineSeparator() +
                "3. [Income] - ang pow money $ 15.00" + System.lineSeparator() ;

        assertEquals(expectedOutput, output);
    }

    @Test
    void execute_onlyExpenseList_expectNothing() {

        financialList.addEntry(new Expense(3.50, "lunch"));
        financialList.addEntry(new Expense(4.50, "dinner"));
        financialList.addEntry(new Expense(20.00, "movie ticket"));

        testCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput = "No recorded incomes found." + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }
}
