package seedu.duke;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ExpenseTest {
    @Test
    public void expenseConstructor() {
        Category category = new Category("Food");
        Expense expense = new Expense("Lunch", 12.50, category);
        assertEquals("Lunch", expense.getName());
        assertEquals(12.50, expense.getAmount());
        assertEquals(category, expense.getCategory());
    }

    @Test
    public void formatAmountWholeNumber() {
        Category category = new Category("Groceries");
        Expense expense = new Expense("Apple", 3.0, category);
        assertEquals("$3", expense.formatAmount());
    }

    @Test
    public void formatAmountDecimalNumber() {
        Category category = new Category("Groceries");
        Expense expense = new Expense("Banana", 2.75, category);
        assertEquals("$2.75", expense.formatAmount());
    }

    @Test
    public void inputWithCategory() {
        Category category = new Category("Entertainment");
        Expense expense = new Expense("Movie", 12.5, category);
        assertEquals(" Item: Movie, Amount: $12.50, Category: Entertainment", expense.toString());
    }

    @Test
    public void inputWithoutCategory() {
        Expense expense = new Expense("Movie", 12.5, null);
        assertEquals(" Item: Movie, Amount: $12.50, Category: null", expense.toString());
    }

    @Test
    public void setCategory() {
        Category category = new Category("Entertainment");
        Expense expense = new Expense("Movie", 12.5, null);
        expense.setCategory(category);
        assertEquals("Entertainment", expense.getCategory().getName());
    }
}

class CategoryTest {
    @Test
    public void categoryConstructor() {
        Category category = new Category("Entertainment");
        assertEquals("Entertainment", category.getName());
    }

    @Test
    public void categoryName() {
        Category category = new Category("Groceries");
        assertEquals("Groceries", category.toString());
    }

    @Test
    public void getName() {
        Category category = new Category("Transport");
        assertNotNull(category.getName());
        assertEquals("Transport", category.getName());
    }
}

class BudgetTest {
    @Test
    public void budgetConstructor() {
        Category category = new Category("Food");
        Budget budget = new Budget(category, 100);
        assertEquals(100, budget.getLimit());
        assertEquals(category, budget.getCategory());
    }

    @Test
    public void setPositiveLimit() {
        Category category = new Category("Transport");
        Budget budget = new Budget(category, 50);
        budget.setLimit(75);
        assertEquals(75, budget.getLimit());
    }

    @Test
    public void setNegativeLimit() {
        Category category = new Category("Utilities");
        Budget budget = new Budget(category, 100);
        assertThrows(IllegalArgumentException.class, () -> budget.setLimit(-50));
    }

    @Test
    public void formatWholeNumberLimit() {
        Category category = new Category("Groceries");
        Budget budget = new Budget(category, 100);
        assertEquals("$100", budget.formatLimit(100));
    }

    @Test
    public void formatDecimalLimit() {
        Category category = new Category("Groceries");
        Budget budget = new Budget(category, 99.99);
        assertEquals("$99.99", budget.formatLimit(99.99));
    }

    @Test
    public void formatMoreThan2DPLimit() {
        Category category = new Category("Groceries");
        Budget budget = new Budget(category, 55.555);
        assertEquals("$55.56", budget.formatLimit(55.555));
    }

    @Test
    public void budgetOutput() {
        Category category = new Category("Entertainment");
        Budget budget = new Budget(category, 200);
        assertEquals("Budget for category 'Entertainment' is $200", budget.toString());
    }
}

class ExpenseTrackerTest {

    @Test
    public void testSetBudgetLimit_NewCategory() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.setBudgetLimit("groceries", 200.0);

        Map<String, Budget> budgets = tracker.getBudgets();
        Budget groceriesBudget = budgets.get("groceries");

        assertNotNull(groceriesBudget, 
                "The budget for groceries should not be null.");
        assertEquals(200.0, groceriesBudget.getLimit(), 
                "The budget limit for groceries should be 200.0.");
    }

    @Test
    public void testSetBudgetLimit_UpdateCategory() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.setBudgetLimit("groceries", 200.0);
        tracker.setBudgetLimit("groceries", 300.0);

        Map<String, Budget> budgets = tracker.getBudgets();
        Budget groceriesBudget = budgets.get("groceries");

        assertNotNull(groceriesBudget, 
                "The budget for groceries should not be null.");
        assertEquals(300.0, groceriesBudget.getLimit(), 
                "The updated budget limit for groceries should be 300.0.");
    }

    @Test
    public void testAddExpense() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.setBudgetLimit("groceries", 200.0);
        tracker.addExpense("Milk", 50.0, "groceries");

        List<Expense> expenses = tracker.getExpenses();

        assertEquals(1, expenses.size(), "There should be 1 expense recorded.");
        Expense milkExpense = expenses.get(0);
        assertEquals("Milk", milkExpense.getName(), 
                "The expense name should be Milk.");
        assertEquals(50.0, milkExpense.getAmount(), 
                "The expense amount should be 50.0.");
        assertEquals("groceries", milkExpense.getCategory(), 
                "The expense category should be groceries.");
    }

    @Test
    public void testExceedBudgetWarning() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.setBudgetLimit("groceries", 100.0);
        tracker.addExpense("Eggs", 50.0, "groceries");
        tracker.addExpense("Cheese", 60.0, "groceries");

        double totalExpenses = tracker.getTotalExpenseForCategory("groceries");
        Budget groceriesBudget = tracker.getBudgets().get("groceries");

        assertTrue(totalExpenses > groceriesBudget.getLimit(), 
                "The total expenses for groceries should exceed the budget limit.");
    }
}
