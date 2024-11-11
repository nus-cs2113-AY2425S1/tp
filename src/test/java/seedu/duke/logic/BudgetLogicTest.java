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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link BudgetLogic} class.
 */
class BudgetLogicTest {
    private Budget budget;
    private TestAppUi ui;
    private BudgetLogic budgetLogic;
    private FinancialList financialList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        budget = new Budget();
        ui = new TestAppUi();
        budgetLogic = new BudgetLogic(budget, ui);
        financialList = new FinancialList();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testSetBudgetValidAmount() throws FinanceBuddyException {
        ui.setInputs("yes", "1000");
        budgetLogic.promptUserToSetBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
        assertTrue(ui.wasSetBudgetMessageDisplayed());
    }

    @Test
    void testSetBudgetInvalidAmountCorrected() throws FinanceBuddyException {
        ui.setInputs("yes", "invalid", "1000");
        budgetLogic.promptUserToSetBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
    }

    @Test
    void handleSetBudget_amountOutOfRange_printWarningMessage() throws FinanceBuddyException {
        ui.setInputs("yes", "0", "1000000000", "1000");
        budgetLogic.promptUserToSetBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
        assertTrue(ui.wasSetBudgetMessageDisplayed());

        String expectedOutput = "--------------------------------------------" + System.lineSeparator() +
                "Please set your budget amount:" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Please set your budget amount:" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Please set your budget amount:" + System.lineSeparator() +
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
    void testSetBudgetSkippedByUser() throws FinanceBuddyException {
        ui.setInputs("no");
        budgetLogic.promptUserToSetBudget(financialList);

        assertFalse(budget.isBudgetSet());
        assertEquals(0, budget.getBudgetAmount());
    }

    @Test
    void testModifyBalancePositiveAmount() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(500);

        assertEquals(1500, budget.getBalance());
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

    @Test
    void printBudgetAndBalance_budgetSet_printBudgetAndBalance() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-600);
        budgetLogic.printBudgetAndBalance();

        assertEquals(400, budget.getBalance());
    }

    @Test
    void testHasExceededBudget() {
        budget.setBudgetAmount(500);
        budgetLogic.modifyBalance(-600);

        assertTrue(budgetLogic.hasExceededBudget());
    }

    @Test
    void testChangeBalanceFromExpenseInCurrentMonth() {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-50, LocalDate.now());

        assertEquals(950, budget.getBalance());
    }

    @Test
    void changeBalanceFromExpenses_oneExpenseCurrentMonth_expectDecrease() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);

        String pastDate = "27/11/2024";
        budgetLogic.changeBalanceFromExpenseString(-20, pastDate);
        
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

    @Test
    void testRecalculateBalanceNoBudgetSet() throws FinanceBuddyException {
        FinancialEntry expense = new Expense(50, "Food", LocalDate.now(), null);
        financialList.addEntry(expense);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(0, budget.getBalance());
    }

    @Test
    void testRecalculateBalanceWithExpenses() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(50, "Food", LocalDate.now(), null);
        FinancialEntry expense2 = new Expense(20, "Transport", LocalDate.now(), null);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);

        budgetLogic.recalculateBalance(financialList);

        assertEquals(930, budget.getBalance());
    }

    @Test
    void testRecalculateBalanceWithMixedEntries() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense = new Expense(50, "Food", LocalDate.now(), null);
        FinancialEntry income = new Income(20, "Bonus", LocalDate.now(), null);
        financialList.addEntry(expense);
        financialList.addEntry(income);

        budgetLogic.recalculateBalance(financialList);

        assertEquals(950, budget.getBalance());
    }

    private static class TestAppUi extends AppUi {
        private String[] inputs;
        private int inputIndex;
        private boolean setBudgetMessageDisplayed;

        void setInputs(String... inputs) {
            this.inputs = inputs;
            this.inputIndex = 0;
        }

        @Override
        public String getUserInput() {
            return inputs[inputIndex++];
        }

        @Override
        public void displaySetBudgetMessage() {
            setBudgetMessageDisplayed = true;
        }

        boolean wasSetBudgetMessageDisplayed() {
            return setBudgetMessageDisplayed;
        }
    }
}
