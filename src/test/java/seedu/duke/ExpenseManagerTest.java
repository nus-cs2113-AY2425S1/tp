package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseManagerTest {

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

