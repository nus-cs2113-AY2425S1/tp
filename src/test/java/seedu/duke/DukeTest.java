package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class ExpenseTrackerTest {

    private ExpenseTracker tracker;

    @BeforeEach
    public void setUp() {
        tracker = new ExpenseTracker();
    }

    @Test
    public void testSetBudgetLimit_NewCategory() {
        tracker.setBudgetLimit("groceries", 200.0);
        Map<String, Budget> budgets = tracker.getBudgets();
        Budget groceriesBudget = budgets.get("groceries");
        assertNotNull(groceriesBudget, "The budget for groceries should not be null.");
        assertEquals(200.0, groceriesBudget.getLimit(), 
                "The budget limit for groceries should be 200.0.");
    }

    @Test
    public void testSetBudgetLimit_UpdateCategory() {
        tracker.setBudgetLimit("groceries", 200.0);
        tracker.setBudgetLimit("groceries", 300.0);
        Map<String, Budget> budgets = tracker.getBudgets();
        Budget groceriesBudget = budgets.get("groceries");
        assertNotNull(groceriesBudget, "The budget for groceries should not be null.");
        assertEquals(300.0, groceriesBudget.getLimit(), 
                "The updated budget limit for groceries should be 300.0.");
    }

    @Test
    public void testAddExpense() {
        tracker.setBudgetLimit("groceries", 200.0);
        tracker.addExpense("Milk", 50.0, "groceries");
        List<Expense> expenses = tracker.getExpenses();
        assertEquals(1, expenses.size(), "There should be 1 expense recorded.");
        Expense milkExpense = expenses.get(0);
        assertEquals("Milk", milkExpense.getName(), "The expense name should be Milk.");
        assertEquals(50.0, milkExpense.getAmount(), "The expense amount should be 50.0.");
        assertEquals("groceries", milkExpense.getCategory(), 
                "The expense category should be groceries.");
    }

    @Test
    public void testExceedBudgetWarning() {
        tracker.setBudgetLimit("groceries", 100.0);
        tracker.addExpense("Eggs", 50.0, "groceries");
        tracker.addExpense("Cheese", 60.0, "groceries");
        double totalExpenses = tracker.getTotalExpenseForCategory("groceries");
        Budget groceriesBudget = tracker.getBudgets().get("groceries");
        assertTrue(totalExpenses > groceriesBudget.getLimit(),
                "The total expenses for groceries should exceed the budget limit.");
    }
}
