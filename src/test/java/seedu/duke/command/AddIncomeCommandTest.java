package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the AddIncomeCommand.
 * This class contains unit tests to verify the functionality of the AddIncomeCommand
 * when adding a new expense to the financial list.
 */
class AddIncomeCommandTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private FinancialList financialList;
    private AddIncomeCommand addIncomeCommand;

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
     * Test the execute method of AddIncomeCommand.
     * Verifies that the income is added to the financial list and that the correct output is printed
     * when a specific date is provided.
     *
     * @throws FinanceBuddyException if the date is invalid or other issues occur while adding the income
     */
    @Test
    void execute_addIncome_expectAddedToFinancialList() throws FinanceBuddyException {
        String specificDate = "14/10/24";
        addIncomeCommand = new AddIncomeCommand(500.0, "allowance", specificDate);
        addIncomeCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                "--------------------------------------------" + System.lineSeparator() +
                "Got it! I've added this income:" + System.lineSeparator() +
                "[Income] - allowance $ 500.00 (on "+ specificDate + ")" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(1, financialList.getEntryCount());  // Verify the entry count
        Income addedIncome = (Income) financialList.getEntry(0);
        assertEquals(500.0, addedIncome.getAmount());
        assertEquals("allowance", addedIncome.getDescription());
        assertEquals(LocalDate.of(2024, 10, 14), addedIncome.getDate());
        assertEquals(expectedOutput, output);  // Verify the printed output
    }


    /**
     * Test adding multiple incomes to the financial list.
     * Verifies that all incomes are added correctly, both with and without specific dates,
     * and that the output is printed for each.
     *
     * @throws FinanceBuddyException if any issues occur while adding the incomes
     */
    @Test
    void execute_addMultipleIncome_expectAllAddedToFinancialList() throws FinanceBuddyException {
        String specificDate = "21/12/24";
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        addIncomeCommand = new AddIncomeCommand(400, "Cost of Living payment", specificDate);
        addIncomeCommand.execute(financialList);

        addIncomeCommand = new AddIncomeCommand(10.50, "friend return money", null);
        addIncomeCommand.execute(financialList);

        String output = outputStream.toString();
        String expectedOutput =
                "--------------------------------------------" + System.lineSeparator() +
                "Got it! I've added this income:" + System.lineSeparator() +
                "[Income] - Cost of Living payment $ 400.00 (on "+ specificDate + ")" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Got it! I've added this income:" + System.lineSeparator() +
                "[Income] - friend return money $ 10.50 (on " + currentDate + ")" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();

        assertEquals(2, financialList.getEntryCount());  // Verify the entry count
        Income firstIncome = (Income) financialList.getEntry(0); //Assert first income index
        assertEquals(400.0, firstIncome.getAmount());
        assertEquals("Cost of Living payment", firstIncome.getDescription());
        assertEquals(LocalDate.of(2024, 12, 21), firstIncome.getDate());
        Income secondIncome = (Income) financialList.getEntry(1); //Assert second income index
        assertEquals(10.50, secondIncome.getAmount());
        assertEquals("friend return money", secondIncome.getDescription());
        assertEquals(LocalDate.now(), secondIncome.getDate());

        assertEquals(expectedOutput, output);  // Verify the printed output for both
    }

}

