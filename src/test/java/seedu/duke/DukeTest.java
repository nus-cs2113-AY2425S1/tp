//@@author glenda-1506
package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
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

//@@author AdiMangalam
class CategoryManagerTest {
    @Test
    void addCategoryNewCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        categoryManager.addCategory(trackerData, "add-category Food");

        assertEquals(1, trackerData.getCategories().size());
        assertEquals("Food", trackerData.getCategories().get(0).getName());
    }

    @Test
    void addCategoryExistingCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        trackerData.getCategories().add(new Category("Food"));

        categoryManager.addCategory(trackerData, "add-category Food");

        assertEquals(1, trackerData.getCategories().size());
    }

    @Test
    void addCategory_doesNotAddIfNameIsEmpty() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();

        categoryManager.addCategory(trackerData, "add-category  ");

        assertTrue(trackerData.getCategories().isEmpty());
    }

    //@@glenda-1506
    @Test
    public void add2NonDuplicateCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TrackerData trackerData = new TrackerData();
        categoryManager.addCategory(trackerData, "add category Food");
        assertEquals(1, trackerData.getCategories().size(), "Should have 1 category");
        categoryManager.addCategory(trackerData, "add category Drinks");
        assertEquals(2, trackerData.getCategories().size(), "Should have 2 categories after adding a different category");
    }

    @Test
    public void formatNullInput() {
        CategoryManager categoryManager = new CategoryManager();
        String result = categoryManager.formatInput(null);
        assertNull(result, "Expected null for null input");
    }

    @Test
    public void formatEmptyInput() {
        CategoryManager categoryManager = new CategoryManager();
        String result = categoryManager.formatInput("");
        assertEquals("", result, "Expected empty string for empty input");
    }
}

//@@author AdiMangalam
class ExpenseManagerTest {
    @Test
    public void addExpenseNewCategory() {
        ExpenseManager expenseManager = new ExpenseManager();
        TrackerData trackerData = new TrackerData();

        expenseManager.addExpense(trackerData,"Coffee", 2.0, "Food");

        assertEquals(1, trackerData.getExpenses().size());
        assertEquals("Coffee", trackerData.getExpenses().get(0).getName());
        assertEquals(2.00, trackerData.getExpenses().get(0).getAmount());
        assertEquals("Food", trackerData.getExpenses().get(0).getCategory().getName());

        assertEquals(1, trackerData.getCategories().size());
        assertEquals("Food", trackerData.getCategories().get(0).getName());
    }

    @Test
    void addExpenseExistingCategory() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        Category existingCategory = new Category("Food");
        trackerData.getCategories().add(existingCategory);

        expenseManager.addExpense(trackerData, "Dinner", 20.00, "Food");

        assertEquals(1, trackerData.getCategories().size());
        assertEquals("Food", trackerData.getExpenses().get(0).getCategory().getName());
    }

    @Test
    void deleteExpenseValidIndex() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addExpense(trackerData, "Coffee", 3.5, "Food");
        expenseManager.addExpense(trackerData, "Bus", 1.2, "Transport");

        assertEquals(2, trackerData.getCategories().size());

        expenseManager.deleteExpense(trackerData, 0);
        assertEquals(1, trackerData.getExpenses().size());
        assertEquals("Bus", trackerData.getExpenses().get(0).getName());
        assertEquals(1.2, trackerData.getExpenses().get(0).getAmount());
        assertEquals("Transport", trackerData.getExpenses().get(0).getCategory().getName());

        expenseManager.deleteExpense(trackerData, 0);
        assertTrue(trackerData.getExpenses().isEmpty());
    }

    @Test
    void deleteExpenseInvalidIndex() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        expenseManager.addExpense(trackerData, "Chocolates", 2.5, "Snacks");

        expenseManager.deleteExpense(trackerData, 1);

        assertEquals(1, trackerData.getExpenses().size());
        assertEquals("Chocolates", trackerData.getExpenses().get(0).getName());
    }

    @Test
    void tagExpenseValidInput() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();


        expenseManager.addExpense(trackerData, "Train", 4, "Transport");

        Category existingCategory = new Category("Travel");
        trackerData.getCategories().add(existingCategory);

        expenseManager.tagExpense(trackerData, "e/1 c/Travel");

        assertEquals("Travel", trackerData.getExpenses().get(0).getCategory().getName());
        assertEquals(2,trackerData.getCategories().size());
    }

    @Test
    void tagExpenseInvalidIndex() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        Category category = new Category("Food");
        trackerData.getCategories().add(category);

        expenseManager.addExpense(trackerData, "Waffle", 11.5, "Food");
        expenseManager.tagExpense(trackerData, "e/2 c/Snack");

        assertEquals("Food", trackerData.getExpenses().get(0).getCategory().getName());
    }

    @Test
    void tagExpenseInvalidCategory() {
        TrackerData trackerData = new TrackerData();
        ExpenseManager expenseManager = new ExpenseManager();

        Expense expense = new Expense("Bus Fare", 2.50, new Category("Transport"));
        trackerData.getExpenses().add(expense);

        expenseManager.addExpense(trackerData,"Bus", 1.25, "Transport");
        expenseManager.tagExpense(trackerData, "e/1 c/Travel");

        assertEquals("Transport", trackerData.getExpenses().get(0).getCategory().getName());
    }
}