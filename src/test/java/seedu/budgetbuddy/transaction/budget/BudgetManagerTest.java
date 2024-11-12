package seedu.budgetbuddy.transaction.budget;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BudgetManagerTest {

    @BeforeEach
    public void setUp() {
        // Reset the static state of BudgetManager before each test
        BudgetManager.reset();
    }

    @Test
    public void testAddBudget_inputIsBudget_incrementsNumberOfBudgets() {
        Budget budget = new Budget(YearMonth.of(2024, 1));
        BudgetManager.addBudget(budget);
        assertEquals(1, BudgetManager.getNumberOfBudgets());
    }

    @Test
    public void testAddBudget_inputIsBudget_addsBudgetToList() {
        Budget budget = new Budget(YearMonth.of(2024, 7));
        BudgetManager.addBudget(budget);
        assertEquals(budget, BudgetManager.getBudgets().get(0));
    }

    @Test
    public void testDeleteBudget_inputIsBudget_decrementsNumberOfBudgets() {
        Budget budget = new Budget(YearMonth.of(2024, 5));
        BudgetManager.addBudget(budget);
        BudgetManager.deleteBudget(budget);
        assertEquals(0, BudgetManager.getNumberOfBudgets());
    }

    @Test
    public void testDeleteBudget_inputIsBudget_deleteBudgetFromList() {
        Budget budget = new Budget(YearMonth.of(2024, 3));
        BudgetManager.addBudget(budget);
        BudgetManager.deleteBudget(budget);
        assertTrue(BudgetManager.getBudgets().isEmpty());
    }

    @Test
    public void testGetBudget_returnsCorrectBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 8));
        BudgetManager.addBudget(budget);
        Budget result = BudgetManager.getBudget(YearMonth.of(2024, 8));
        assertEquals(budget, result);
    }

    @Test
    public void testGetBudget_returnsNullForNonExistentBudget() {
        Budget result = BudgetManager.getBudget(YearMonth.of(2024, 12));
        assertNull(result);
    }

    @Test
    public void addBudget_inputIsNullBudget_assertionErrorThrown() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            BudgetManager.addBudget(null);
        });
        assertEquals("Budget to be added cannot be null", exception.getMessage());
    }

    @Test
    public void deleteBudget_inputIsNullBudget_assertionErrorThrown() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            BudgetManager.deleteBudget(null);
        });
        assertEquals("Budget to be deleted cannot be null", exception.getMessage());
    }

    @Test
    public void getBudget_multipleBudgets_returnsCorrectBudget() {
        Budget budget1 = new Budget(YearMonth.of(2024, 9));
        Budget budget2 = new Budget(YearMonth.of(2024, 8));
        BudgetManager.addBudget(budget1);
        BudgetManager.addBudget(budget2);
        Budget result = BudgetManager.getBudget(YearMonth.of(2024, 9));
        assertEquals(budget1, result);
    }

    @Test
    public void reset_clearsBudgets() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        BudgetManager.addBudget(budget);
        BudgetManager.reset();
        assertEquals(0, BudgetManager.getNumberOfBudgets());
        assertTrue(BudgetManager.getBudgets().isEmpty());
    }
}
