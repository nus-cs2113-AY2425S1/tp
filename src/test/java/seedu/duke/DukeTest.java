package seedu.duke;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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
    void constructorTest() {
        ExpenseTracker tracker = new ExpenseTracker();
        assertNotNull(tracker);
        assertTrue(tracker.getCategories().isEmpty());
        assertTrue(tracker.getExpenses().isEmpty());
        assertFalse(tracker.isAutoResetEnabled());
    }

    @Test
    void toggleAutoResetTest() {
        ExpenseTracker tracker = new ExpenseTracker();
        assertFalse(tracker.isAutoResetEnabled());
        tracker.toggleAutoReset();
        assertTrue(tracker.isAutoResetEnabled());
        tracker.toggleAutoReset();
        assertFalse(tracker.isAutoResetEnabled());
    }

    @Test
    void addCategoryAndBudgetTest() {
        ExpenseTracker tracker = new ExpenseTracker();
        Category category = new Category("Utilities");
        tracker.addCategory(category);
        assertTrue(tracker.getCategories().contains(category));
        tracker.setBudget(category, 100);
        assertEquals(100, tracker.getBudget(category).getLimit());
    }

    @Test
    void addAndDeleteExpenseTest() {
        ExpenseTracker tracker = new ExpenseTracker();
        Category food = new Category("Food");
        Expense lunch = new Expense("Lunch", 15.50, food);
        tracker.addCategory(food);
        tracker.addExpense(lunch);
        assertTrue(tracker.getName().contains(lunch));
        tracker.deleteExpense(lunch);
        assertFalse(tracker.getName().contains(lunch));
    }

    @Test
    void checkAndResetBudgetsTest() {
        ExpenseTracker tracker = new ExpenseTracker();
        Category groceries = new Category("Groceries");
        tracker.addCategory(groceries);
        tracker.setBudget(groceries, 200);
        tracker.toggleAutoReset();
        tracker.checkAndResetBudgets(); 
        assertEquals(200, tracker.getBudgets(groceries).getLimit());
    }

    @Test
    void manageMonthlyResetTest() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.manageMonthlyReset(); 
    }
}
