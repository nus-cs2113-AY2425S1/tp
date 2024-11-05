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

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the {@link BudgetLogic} class.
 */
class BudgetLogicTest {
    private Budget budget;
    private TestAppUi ui;
    private BudgetLogic budgetLogic;
    private FinancialList financialList;

    /**
     * Sets up a new {@link Budget}, {@link TestAppUi}, and {@link BudgetLogic} instance before each test.
     */
    @BeforeEach
    void setUp() {
        budget = new Budget();
        ui = new TestAppUi();
        budgetLogic = new BudgetLogic(budget, ui);
        financialList = new FinancialList();
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
    void testHandleSetBudgetWithInvalidInput() throws FinanceBuddyException {
        ui.setInputs("yes", "invalid", "1000");

        budgetLogic.handleSetBudget(financialList);

        assertEquals(1000, budget.getBudgetAmount());
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

    @Test
    void hasExceededBudget_expensesGreaterThanBudget_expectTrue() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-2000);

        assertTrue(budgetLogic.hasExceededBudget());
    }

    @Test
    void hasExceededBudget_expensesSmallerThanBudget_expectFalse() {
        budget.setBudgetAmount(1000);
        budgetLogic.modifyBalance(-200);

        assertFalse(budgetLogic.hasExceededBudget());
    }

    @Test
    void isCurrentMonth_currentYearAndMonth_expectTrue() {
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        budgetLogic.isCurrentMonth(date);
    }

    @Test
    void isCurrentMonth_currentYearLastMonth_expectFalse() {
        int year;
        if (LocalDate.now().getMonth().equals(Month.JANUARY)) {
            year = LocalDate.now().getYear() - 1;
        } else {
            year = LocalDate.now().getYear();
        }
        LocalDate date = LocalDate.of(year, 1, 1);
        budgetLogic.isCurrentMonth(date);
    }

    @Test
    void isCurrentMonth_LastYearCurrentMonth_expectFalse() {
        LocalDate date = LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonth(), 1);
        budgetLogic.isCurrentMonth(date);
    }

    @Test
    void changeBalanceFromExpenses_noBudgetSet_expectNothing() throws FinanceBuddyException {
        budgetLogic.changeBalanceFromExpense(-20, LocalDate.now());

        assertEquals(0, budget.getBalance());
    }

    @Test
    void changeBalanceFromExpenses_oneExpenseCurrentMonth_expectDecrease() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-20, LocalDate.now());

        assertEquals(980, budget.getBalance());
    }

    @Test
    void changeBalanceFromExpenses_oneExpenseNotCurrentMonth_expectNoChange() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-20, "27/11/23");

        assertEquals(1000, budget.getBalance());
    }

    @Test
    void changeBalanceFromExpenses_multipleExpenses_expectDecrease() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        budgetLogic.changeBalanceFromExpense(-20, LocalDate.now());
        budgetLogic.changeBalanceFromExpense(-50, "27/10/24");
        budgetLogic.changeBalanceFromExpense(-90, "27/11/23");
        budgetLogic.changeBalanceFromExpense(-230, LocalDate.now());

        assertEquals(750, budget.getBalance());
    }

    @Test
    void recalculateBalance_noBudgetSet_expectNothing() throws FinanceBuddyException {
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(),
                Expense.Category.FOOD);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(),
                Expense.Category.TRANSPORT);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(0, budget.getBalance());
    }

    @Test
    void recalculateBalance_currentMonthExpenses_expectNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(),
                Expense.Category.FOOD);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(),
                Expense.Category.TRANSPORT);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(985, budget.getBalance());
    }

    @Test
    void recalculateBalance_currentMonthNoExpenses_expectNoChange() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry income1 = new Income(10.0, "bonus", LocalDate.now(),
                Income.Category.GIFT);
        FinancialEntry income2 = new Income(15.5, "salary", LocalDate.now(),
                Income.Category.SALARY);
        financialList.addEntry(income1);
        financialList.addEntry(income2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(1000, budget.getBalance());
    }

    @Test
    void recalculateBalance_currentMonthMixedEntries_expectNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(),
                Expense.Category.FOOD);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(),
                Expense.Category.TRANSPORT);
        FinancialEntry expense3 = new Expense(10.0, "table", LocalDate.now(),
                Expense.Category.OTHER);
        FinancialEntry income1 = new Income(10.0, "bonus", LocalDate.now(),
                Income.Category.GIFT);
        FinancialEntry income2 = new Income(15.5, "salary", LocalDate.now(),
                Income.Category.SALARY);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);
        financialList.addEntry(expense3);
        financialList.addEntry(income1);
        financialList.addEntry(income2);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(975, budget.getBalance());
    }

    @Test
    void recalculateBalance_mixedMonthsExpenses_expectNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.of(24, 10, 22),
                Expense.Category.FOOD);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.now(),
                Expense.Category.TRANSPORT);
        FinancialEntry expense3 = new Expense(15.5, "snacks", LocalDate.of(24, 10, 20),
                Expense.Category.FOOD);
        FinancialEntry expense4 = new Expense(10.0, "table",
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1),
                Expense.Category.OTHER);
        FinancialEntry expense5 = new Expense(7.0, "shampoo", LocalDate.now(),
                Expense.Category.UTILITIES);
        financialList.addEntry(expense1);
        financialList.addEntry(expense2);
        financialList.addEntry(expense3);
        financialList.addEntry(expense4);
        financialList.addEntry(expense5);

        budgetLogic.recalculateBalance(financialList);
        assertEquals(978, budget.getBalance());
    }

    @Test
    void recalculateBalance_mixedMonthsMixedEntries_expectNewBalance() throws FinanceBuddyException {
        budget.setBudgetAmount(1000);
        FinancialEntry expense1 = new Expense(10.0, "food", LocalDate.now(),
                Expense.Category.FOOD);
        FinancialEntry expense2 = new Expense(5.0, "transport", LocalDate.of(24, 10, 22),
                Expense.Category.TRANSPORT);
        FinancialEntry expense3 = new Expense(10.0, "table",
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1),
                Expense.Category.OTHER);
        FinancialEntry income1 = new Income(10.0, "bonus", LocalDate.now(),
                Income.Category.GIFT);
        FinancialEntry income2 = new Income(15.5, "salary", LocalDate.of(24, 10, 22),
                Income.Category.SALARY);
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
