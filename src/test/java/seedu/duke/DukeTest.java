package seedu.duke;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


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


public class ExpenseTrackerTest {
    private static final String AUTOMATIC_RESET_ON = "Automatic budget reset is now ON";
    private static final String AUTOMATIC_RESET_OFF = "Automatic budget reset is now OFF";
    private static final double TEST_BUDGET_LIMIT = 100.00;
    private static final double TEST_EXPENSE_AMOUNT = 15.50;
    private static final String TEST_CATEGORY_NAME = "Food";
    private static final String TEST_EXPENSE_DESCRIPTION = "Dinner at restaurant";
    private static final double TEST_MOVIE_PRICE = 12.00;
    private static final String TEST_MOVIE_TICKET_DESCRIPTION = "Movie ticket";
    private static final String TEST_MISC_BUDGET = "Misc";
    private static final double TEST_MISC_BUDGET_LIMIT = 50.00;
    private static final String TEST_GROCERIES = "Groceries";
    private static final double TEST_GROCERIES_AMOUNT = 60.00;
    private static final String TEST_GROCERIES_SHOPPING = "Weekly groceries";
    private static final String TEST_GROCERIES_TAG = "Sunday shopping";

    private ExpenseTracker tracker;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        tracker = new ExpenseTracker();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testToggleAutoReset() {
        tracker.toggleAutoReset();
        assertTrue(outContent.toString().contains(AUTOMATIC_RESET_ON));
        outContent.reset();
        tracker.toggleAutoReset();
        assertTrue(outContent.toString().contains(AUTOMATIC_RESET_OFF));
    }

    @Test
    public void testAddCategory() {
        Category newCategory = new Category(TEST_CATEGORY_NAME);
        tracker.addCategory(newCategory);
        tracker.viewBudget();  
        assertTrue(outContent.toString().contains(TEST_CATEGORY_NAME));
    }

    @Test
    public void testAddExpense() {
        Category food = new Category(TEST_CATEGORY_NAME);
        tracker.addCategory(food);
        Expense dinner = new Expense(food, TEST_EXPENSE_AMOUNT, TEST_EXPENSE_DESCRIPTION);
        tracker.addExpense(dinner);
        tracker.viewExpensesByCategory();
        assertTrue(outContent.toString().contains(TEST_EXPENSE_DESCRIPTION));
        assertTrue(outContent.toString().contains(String.valueOf(TEST_EXPENSE_AMOUNT)));
    }

    @Test
    public void testDeleteExpense() {
        Category food = new Category(TEST_CATEGORY_NAME);
        tracker.addCategory(food);
        Expense dinner = new Expense(food, TEST_EXPENSE_AMOUNT, "Dinner");
        tracker.addExpense(dinner);
        tracker.deleteExpense(dinner);
        tracker.viewExpensesByCategory();
        assertFalse(outContent.toString().contains("Dinner"));
    }

    @Test
    public void testSetBudgetLimit() {
        Category transport = new Category("Transport");
        tracker.addCategory(transport);
        tracker.setBudgetLimit(transport, TEST_BUDGET_LIMIT);
        tracker.viewBudget();
        assertTrue(outContent.toString().contains("Transport"));
        assertTrue(outContent.toString().contains(String.valueOf(TEST_BUDGET_LIMIT)));
    }

    @Test
    public void testViewBudget() {
        Category misc = new Category(TEST_MISC_BUDGET);
        tracker.addCategory(misc);
        tracker.setBudgetLimit(misc, TEST_MISC_BUDGET_LIMIT);
        tracker.viewBudget();
        assertTrue(outContent.toString().contains(TEST_MISC_BUDGET));
        assertTrue(outContent.toString().contains(String.valueOf(TEST_MISC_BUDGET_LIMIT)));
    }

    @Test
    public void testViewExpensesByCategory() {
        Category entertainment = new Category("Entertainment");
        tracker.addCategory(entertainment);
        Expense movie = new Expense(entertainment, TEST_MOVIE_PRICE, TEST_MOVIE_TICKET_DESCRIPTION);
        tracker.addExpense(movie);
        tracker.viewExpensesByCategory();
        assertTrue(outContent.toString().contains(TEST_MOVIE_TICKET_DESCRIPTION));
        assertTrue(outContent.toString().contains(String.valueOf(TEST_MOVIE_PRICE)));
    }

    @Test
    public void testCheckAndResetBudgets() {
        tracker.toggleAutoReset();
        tracker.checkAndResetBudgets();
        outContent.reset();
        tracker.checkAndResetBudgets();
        assertTrue(outContent.toString().isEmpty());
    }

    @Test
    public void testManageMonthlyReset() {
        tracker.manageMonthlyReset();
        assertTrue(outContent.toString().contains("Budgets have been reset"));
    }

    @Test
    public void testTagExpense() {
        Category groceries = new Category(TEST_GROCERIES);
        tracker.addCategory(groceries);
        Expense shopping = new Expense(groceries, TEST_GROCERIES_AMOUNT, TEST_GROCERIES_SHOPPING);
        tracker.addExpense(shopping);
        tracker.tagExpense(shopping, TEST_GROCERIES_TAG);
        tracker.viewExpensesByCategory();
        assertTrue(outContent.toString().contains(TEST_GROCERIES_TAG));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
