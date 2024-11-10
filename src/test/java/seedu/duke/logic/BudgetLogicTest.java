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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        budgetLogic.setBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
        assertTrue(ui.wasSetBudgetMessageDisplayed());
    }

    @Test
    void testSetBudgetInvalidAmountCorrected() throws FinanceBuddyException {
        ui.setInputs("yes", "invalid", "1000");
        budgetLogic.setBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
        assertEquals(1000, budget.getBalance());
    }

    @Test
    void testSetBudgetZeroAmount() throws FinanceBuddyException {
        ui.setInputs("yes", "0", "1000");
        budgetLogic.setBudget(financialList);

        assertTrue(budget.isBudgetSet());
        assertEquals(1000, budget.getBudgetAmount());
    }

    @Test
    void testSetBudgetSkippedByUser() throws FinanceBuddyException {
        ui.setInputs("no");
        budgetLogic.setBudget(financialList);

        assertFalse(budget.isBudgetSet());
        assertEquals(0, budget.getBudgetAmount());
    }

    @Test
    void testModifyBalancePositiveAmount() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(500);

        assertEquals(1500, budget.getBalance());
    }

    @Test
    void testModifyBalanceNegativeAmount() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-300);

        assertEquals(700, budget.getBalance());
    }

    @Test
    void testHasExceededBudget() {
        budget.setBudgetAmount(500);
        budgetLogic.modifyBalance(-600);

        assertTrue(budgetLogic.hasExceededBudget());
    }

    @Test
    void testGetBudgetAndBalanceWhenNotSet() {
        budgetLogic.getBudgetAndBalance();

        assertEquals("No budget has been set." + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testGetBudgetAndBalanceWhenSet() {
        budget.setBudgetAmount(2000);
        budgetLogic.modifyBalance(-400);
        budgetLogic.getBudgetAndBalance();

        assertEquals("Your current budget is: $ 2000.00" + System.lineSeparator() +
                "Your current monthly balance is: $ 1600.00" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator(), outContent.toString());
    }

    @Test
    void testChangeBalanceFromExpenseInCurrentMonth() {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-50, LocalDate.now());

        assertEquals(950, budget.getBalance());
    }

    @Test
    void testChangeBalanceFromExpenseInPastMonth() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);

        String pastDate = "27/11/2023";
        budgetLogic.changeBalanceFromExpenseString(19, pastDate);

        assertEquals(1000, budget.getBalance());

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests decreases in the budget balance from multiple expenses recorded in the current month.
     */
    @Test
    void changeBalanceFromExpenses_multipleExpensesExceedBudget_printDecrease() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-230, LocalDate.now());
        budgetLogic.changeBalanceFromExpenseString(-50, "27/10/2024");
        budgetLogic.changeBalanceFromExpenseString(-90, "27/11/2023");
        budgetLogic.changeBalanceFromExpense(20, LocalDate.now());
        budgetLogic.changeBalanceFromExpense(-900, LocalDate.now());

        assertEquals(-110, budget.getBalance());

        String expectedOutput = "Your current monthly balance is: $ 770.00" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "Your current monthly balance is: $ 790.00" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator() +
                "You have exceeded your monthly budget of: $ 1000.00!" + System.lineSeparator() +
                "Your current monthly balance is: $ -110.00" + System.lineSeparator() +
                "--------------------------------------------" + System.lineSeparator();
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
