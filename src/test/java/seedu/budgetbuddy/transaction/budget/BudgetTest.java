package seedu.budgetbuddy.transaction.budget;

import org.junit.jupiter.api.Test;
import seedu.budgetbuddy.transaction.Category;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BudgetTest {

    @Test
    public void testAddAmount_inputIsDouble_increasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 50.0);
        assertEquals(50.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testDeductAmount_inputIsDouble_decreasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.OTHERS, 100);
        budget.deductAmount(Category.OTHERS, 30.0);
        assertEquals(70.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testAddAmount_inputIsInt_increasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 2));
        budget.addAmount(Category.ENTERTAINMENT, 150);
        assertEquals(150.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testDeductAmount_inputIsInt_decreasesBudgetTotalMonthlyBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 2));
        budget.addAmount(Category.TRANSPORT, 100);
        budget.deductAmount(Category.TRANSPORT, 70);
        assertEquals(30.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testToString_returnsCorrectString() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 100);
        String result = budget.toString();
        assertEquals("Total Monthly Budget: 100.00  Date: 2024-10\n Category: {FOOD=100.00}", result);
    }

    @Test
    public void testGetAmount_returnsCorrectTotalMonthlyBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 9));
        budget.addAmount(Category.FOOD, 250);
        assertEquals(250.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void testGetDate_returnsCorrectDate() {
        Budget budget = new Budget(YearMonth.of(2024, 9));
        assertEquals(YearMonth.of(2024, 9), budget.getDate());
    }

    @Test
    public void addAmount_inputIsValidCategory_updatesTotalCorrectly() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 50.0);
        assertEquals(50.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void deductAmount_inputIsValidCategory_decreasesTotalMonthlyBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.OTHERS, 100);
        budget.deductAmount(Category.OTHERS, 30.0);
        assertEquals(70.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void addMultipleCategories_updatesTotalCorrectly() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 50);
        budget.addAmount(Category.ENTERTAINMENT, 150);
        budget.addAmount(Category.TRANSPORT, 100);
        assertEquals(300.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void deductBudget_inputIsExceedingAmount_removesCategory() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 50);
        budget.deductAmount(Category.FOOD, 50);
        assertTrue(budget.getCategoryBudgets().isEmpty());
    }

    @Test
    public void deductBudget_inputIsNonExistentCategory_doesNotChangeBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.deductAmount(Category.FOOD, 10);
        assertEquals(0.0, budget.getTotalMonthlyBudget());
        assertTrue(budget.getCategoryBudgets().isEmpty());
    }

    @Test
    public void addBudget_inputIsZeroAmount_doesNotChangeBudget() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 0);
        assertEquals(0.0, budget.getTotalMonthlyBudget());
    }

    @Test
    public void deepCopyConstructor_copiesBudgetCorrectly() {
        Budget original = new Budget(YearMonth.of(2024, 10));
        original.addAmount(Category.FOOD, 100);
        Budget copy = new Budget(original);
        original.deductAmount(Category.FOOD, 50); // Change the original

        assertEquals(50.0, original.getTotalMonthlyBudget()); // Original should be updated
        assertEquals(100.0, copy.getTotalMonthlyBudget()); // Copy should remain unchanged
    }

    @Test
    public void toString_returnsCorrectStringRepresentation() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 100);
        String result = budget.toString();
        assertEquals("Total Monthly Budget: 100.00  Date: 2024-10\n Category: {FOOD=100.00}", result);
    }

    @Test
    public void getDate_returnsCorrectDate() {
        Budget budget = new Budget(YearMonth.of(2024, 9));
        assertEquals(YearMonth.of(2024, 9), budget.getDate());
    }

    @Test
    public void addAmount_inputIsExistingCategory_updatesAmount() {
        Budget budget = new Budget(YearMonth.of(2024, 10));
        budget.addAmount(Category.FOOD, 100);
        budget.addAmount(Category.FOOD, 50); // Adding to existing category
        assertEquals(150.0, budget.getTotalMonthlyBudget());
    }
}
