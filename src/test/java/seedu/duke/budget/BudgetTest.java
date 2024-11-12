package seedu.duke.budget;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link Budget} class.
 */
class BudgetTest {
    private Budget budget;

    /**
     * Sets up a new {@link Budget} instance before each test.
     */
    @BeforeEach
    void setUp() {
        budget = new Budget();
    }

    /**
     * Tests the initial values of a newly created {@link Budget} instance.
     * Ensures the budget amount, balance, and budget status are correctly initialized.
     */
    @Test
    void testInitialBudgetValues() {
        assertEquals(0, budget.getBudgetAmount());
        assertEquals(0, budget.getBalance());
        assertFalse(budget.isBudgetSet());
        assertNull(budget.budgetSetDate);
    }

    /**
     * Tests setting a new budget amount. Verifies that the budget amount,
     * balance, and budget status are updated correctly.
     */
    @Test
    void testSetBudgetAmount() {
        double newAmount = 1000;
        budget.setBudgetAmount(newAmount);

        assertEquals(newAmount, budget.getBudgetAmount());
        assertEquals(newAmount, budget.getBalance());
        assertTrue(budget.isBudgetSet());
        assertEquals(LocalDate.now(), budget.budgetSetDate);
    }

    /**
     * Tests updating the balance. Ensures the balance reflects the new value.
     */
    @Test
    void testUpdateBalance() {
        budget.setBudgetAmount(1000);
        double newBalance = 500;
        budget.updateBalance(newBalance);

        assertEquals(newBalance, budget.getBalance());
    }
}
