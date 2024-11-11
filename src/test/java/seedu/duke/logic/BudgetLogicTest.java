package seedu.duke.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.budget.Budget;
import seedu.duke.exception.FinanceBuddyException;
import seedu.duke.financial.Expense;
import seedu.duke.financial.FinancialEntry;
import seedu.duke.financial.FinancialList;
import seedu.duke.financial.Income;
import seedu.duke.ui.AppUi;
import seedu.duke.util.Commons;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link BudgetLogic} class.
 */
class BudgetLogicTest {
    private Budget budget;
    private TestAppUi ui;
    private BudgetLogic budgetLogic;
    private FinancialList financialList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * Sets up a new {@link Budget}, {@link TestAppUi}, and {@link BudgetLogic} instance before each test.
     */
    @BeforeEach
    void setUp() {
        budget = new Budget();
        ui = new TestAppUi();
        budgetLogic = new BudgetLogic(budget, ui);
        financialList = new FinancialList();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Tests setting the budget when it is not already set. Verifies that the budget amount,
     * balance, and budget status are updated correctly, and that the budget message was displayed.
     */
    @Test
    void testSetBudgetWhenBudgetNotSet() throws FinanceBuddyException {
        ui.setInputs("yes", "1000");

        budgetLogic.setBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
        assertTrue(ui.wasSetBudgetMessageDisplayed());
    }

    /**
     * Tests setting the budget when it is already set. Verifies that the budget amount
     * and balance are updated to the new value.
     */
    @Test
    void testSetBudgetWhenBudgetAlreadySet() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        ui.setInputs("yes", "1500");

        budgetLogic.setBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1500, budget.getBudgetAmount());
        assertEquals(1500, budget.getBalance());
    }

    /**
     * Tests handling of invalid input during budget setting. Ensures the budget is set
     * correctly after the invalid input is corrected.
     */
    @Test
    void handleSetBudget_invalidInput_printWarningMessage() throws FinanceBuddyException {
        ui.setInputs("yes", "invalid");

        Exception exception = assertThrows(FinanceBuddyException.class, () -> {
            budgetLogic.handleSetBudget(financialList);
        });

        // Verify the error message
        assertEquals(Commons.ERROR_MESSAGE_NON_NUMBER_AMOUNT, exception.getMessage());
    }

    /**
     * Tests handling of a zero budget amount and ensures the prompt for a valid input.
     */
    @Test
    void handleSetBudget_amountOutOfRange_printWarningMessage() throws FinanceBuddyException {
        ui.setInputs("yes", "0", "1000000000", "1000");

        budgetLogic.setBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
        assertTrue(ui.wasSetBudgetMessageDisplayed());

        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Please set your budget amount:" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Budget amount must be >= $0.01 and <= $9999999.00. Please enter a valid amount." +
                System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Budget amount must be >= $0.01 and <= $9999999.00. Please enter a valid amount." +
                System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Your budget has successfully been set to: $ 1000.00" + System.lineSeparator() +
                "Your current monthly balance is: $ 1000.00" + System.lineSeparator() +
                "--------------------------------------------" +
                System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests prompting the user to set a budget when no budget has been set.
     * Verifies prompt displayed.
     */
    @Test
    void promptUserToSetBudget_budgetNotSet_setBudget() throws FinanceBuddyException {
        ui.setInputs("yes", "1000");
        budgetLogic.promptUserToSetBudget(financialList);

        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Please set your budget amount:" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Your budget has successfully been set to: $ 1000.00" + System.lineSeparator() +
                "Your current monthly balance is: $ 1000.00" + System.lineSeparator() +
                "--------------------------------------------" +
                System.lineSeparator();;
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests the prompt for setting a budget when the budget was set in a previous month.
     * Verifies prompt displayed.
     */
    @Test
    void promptUserToSetBudget_budgetNotSetInCurrentMonth_printPrompt() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        budget.setBudgetSetDate(LocalDate.of(LocalDate.now().getYear() - 1, Month.JANUARY, 1));

        ui.setInputs("yes", "1000");
        budgetLogic.promptUserToSetBudget(financialList);

        String expectedOutput = "Your budget was set in a previous month." + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Please set your budget amount:" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Your budget has successfully been set to: $ 1000.00" + System.lineSeparator() +
                "Your current monthly balance is: $ 1000.00" + System.lineSeparator() +
                "--------------------------------------------" +
                System.lineSeparator();;
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests prompting the user to set a budget when the budget is set in the current month.
     * The test expects no additional prompting or output, as the budget is up to date.
     */
    @Test
    void promptUserToSetBudget_budgetSetInCurrentMonth_expectNothing() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);

        budgetLogic.promptUserToSetBudget(financialList);
        assertEquals("", outContent.toString());
    }

    /**
     * Tests modifying the balance to a higher amount. Ensures the balance reflects the new amount.
     */
    @Test
    void testModifyBalance() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(200);

        assertEquals(1200, budget.getBalance());
    }

    /**
     * Tests modifying the balance to a lower amount. Ensures the balance reflects the decreased amount.
     */
    @Test
    void testModifyBalanceNegative() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-200);

        assertEquals(800, budget.getBalance());
    }

    /**
     * Tests retrieving the budget and balance when no budget is set.
     * Verifies that the appropriate message is printed.
     */
    @Test
    void printBudgetAndBalance_noBudgetSet_printNoBudgetSetMessage() {
        budgetLogic.printBudgetAndBalance();

        String expectedOutput =
                "No budget has been set." + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests retrieving the budget and current balance when budget is set.
     * Verifies that the appropriate message is printed.
     */
    @Test
    void printBudgetAndBalance_budgetSet_printBudgetAndBalance() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-600);
        budgetLogic.printBudgetAndBalance();

        String expectedOutput = "Your current budget is: $ 1000.00" + System.lineSeparator() +
                        "Your current monthly balance is: $ 400.00" + System.lineSeparator() +
                        "--------------------------------------------" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests if the budget is exceeded when expenses exceed the budgeted amount.
     */
    @Test
    void hasExceededBudget_expensesGreaterThanBudget_expectTrue() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-2000);

        assertTrue(budgetLogic.hasExceededBudget());
    }

    /**
     * Tests if the budget is not exceeded when expenses are within the budgeted amount.
     */
    @Test
    void hasExceededBudget_expensesSmallerThanBudget_expectFalse() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-200);

        assertFalse(budgetLogic.hasExceededBudget());
    }

    /**
     * Tests whether the date given is in the current month and year.
     */
    @Test
    void isCurrentMonth_currentYearAndMonth_expectTrue() {
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        assertTrue(budgetLogic.isCurrentMonth(date));
    }

    /**
     * Tests whether the date from the current year but month is identified as not current.
     */
    @Test
    void isCurrentMonth_currentYearLastMonth_expectFalse() {
        int year;
        if (LocalDate.now().getMonth().equals(Month.JANUARY)) {
            year = LocalDate.now().getYear() - 1;
        } else {
            year = LocalDate.now().getYear();
        }
        LocalDate date = LocalDate.of(year, LocalDate.now().getMonth().getValue() - 1, 1);
        assertFalse(budgetLogic.isCurrentMonth(date));
    }

    /**
     * Tests whether the date from the current month but year is identified as not current.
     */
    @Test
    void isCurrentMonth_lastYearCurrentMonth_expectFalse() {
        LocalDate date = LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonth(), 1);
        assertFalse(budgetLogic.isCurrentMonth(date));
    }

    /**
     * Tests that no balance changes occur from expenses when no budget is set.
     */
    @Test
    void changeBalanceFromExpenses_noBudgetSet_expectNothing() {
        budgetLogic.changeBalanceFromExpense(-50, LocalDate.now());

        assertEquals(0, budget.getBalance());

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests that expenses recorded in the current month decrease the balance correctly.
     */
    @Test
    void changeBalanceFromExpenses_oneExpenseCurrentMonth_expectDecrease() {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-20, LocalDate.now());

        assertEquals(980, budget.getBalance());
    }

    /**
     * Tests that expenses recorded in months other than the current do not affect the balance.
     */
    @Test
    void changeBalanceFromExpenses_oneExpenseNotCurrentMonth_expectNothing() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpenseString(19, "27/11/2023");

        assertEquals(1000, budget.getBalance());
    }

    /**
     * Tests decreases in the budget balance from multiple expenses recorded in the current month.
     */
    @Test
    void changeBalanceFromExpenses_multipleExpensesExceedBudget_expectDecrease() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-230, LocalDate.now());
        budgetLogic.changeBalanceFromExpenseString(-50, "27/10/2024");
        budgetLogic.changeBalanceFromExpenseString(-90, "27/11/2023");
        budgetLogic.changeBalanceFromExpense(20, LocalDate.now());
        budgetLogic.changeBalanceFromExpense(-900, LocalDate.now());

        assertEquals(-110, budget.getBalance());

        String expectedOutput = "You have exceeded your monthly budget of: $ 1000.00!" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests recalculation of balance when no budget is set, leaving the balance unchanged.
     */
    @Test
    void recalculateBalance_noBudgetSet_expectNothing() throws FinanceBuddyException {
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(), null);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(), null);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(0, budget.getBalance());

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests recalculation of budget balance including only current month expenses.
     */
    @Test
    void recalculateBalance_currentMonthExpenses_printNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(), null);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(), null);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(985, budget.getBalance());
    }

    /**
     * Tests recalculation of balance when there are no expenses recorded for the current month.
     */
    @Test
    void recalculateBalance_currentMonthNoExpenses_printSameBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry income1 = new Income(10.0, "bonus", LocalDate.now(), null);
        FinancialEntry income2 = new Income(15.5, "salary", LocalDate.now(), null);
        financialList.addEntry(income1);
        financialList.addEntry(income2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(1000, budget.getBalance());
    }

    /**
     * Tests recalculation of balance for mixed entries of income and expense in the current month.
     */
    @Test
    void recalculateBalance_currentMonthMixedEntries_printNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(), null);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(), null);
        FinancialEntry expense3 = new Expense(10.0, "table", LocalDate.now(), null);
        FinancialEntry income1 = new Income(10.0, "bonus", LocalDate.now(), null);
        FinancialEntry income2 = new Income(15.5, "salary", LocalDate.now(), null);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);
        financialList.addEntry(expense3);
        financialList.addEntry(income1);
        financialList.addEntry(income2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(975, budget.getBalance());
    }

    /**
     * Tests recalculation of balance considering only current month expenses.
     */
    @Test
    void recalculateBalance_mixedMonthsExpensesExceedBudget_printNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food",
                LocalDate.of(2024, 10, 22), null);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(), null);
        FinancialEntry expense3 = new Expense(15.5, "snacks",
                LocalDate.of(2024, 10, 20), null);
        FinancialEntry expense4 = new Expense(1000.0, "table",
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1), null);
        FinancialEntry expense5 = new Expense(7.0, "shampoo", LocalDate.now(), null);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);
        financialList.addEntry(expense3);
        financialList.addEntry(expense4);
        financialList.addEntry(expense5);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(-12, budget.getBalance());

        String expectedOutput = "You have exceeded your monthly budget of: $ 1000.00!" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests recalculation of balance with mixed entries and dates, expecting updates based on current month.
     */
    @Test
    void recalculateBalance_mixedMonthsMixedEntries_printNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(), null);
        FinancialEntry expense2 = new Expense(5.0, "transport",
                LocalDate.of(2024, 10, 22), null);
        FinancialEntry expense3 = new Expense(10.0, "table",
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1), null);
        FinancialEntry income1 = new Income(10.0, "bonus", LocalDate.now(), null);
        FinancialEntry income2 = new Income(15.5, "salary",
                LocalDate.of(2024, 10, 22), null);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);
        financialList.addEntry(expense3);
        financialList.addEntry(income1);
        financialList.addEntry(income2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(980, budget.getBalance());
    }

    /**
     * A simple implementation of {@link AppUi} for testing purposes.
     * It provides a list of predefined inputs and tracks if a message was displayed.
     */
    private static class TestAppUi extends AppUi {
        private String[] inputs;
        private int inputIndex = 0;
        private boolean setBudgetMessageDisplayed = false;

        /**
         * Sets the sequence of inputs to simulate user interaction.
         *
         * @param inputs the inputs to be returned by {@code getUserInput}.
         */
        void setInputs(String... inputs) {
            this.inputs = inputs;
            this.inputIndex = 0;
        }

        /**
         * Returns the next user input in the predefined sequence.
         *
         * @return the next input string.
         */
        @Override
        public String getUserInput() {
            return inputs[inputIndex++];
        }

        /**
         * Simulates displaying the set budget message.
         */
        @Override
        public void displaySetBudgetMessage() {
            setBudgetMessageDisplayed = true;
        }

        /**
         * Checks if the set budget message was displayed.
         *
         * @return true if the message was displayed, false otherwise.
         */
        boolean wasSetBudgetMessageDisplayed() {
            return setBudgetMessageDisplayed;
        }
    }
}
