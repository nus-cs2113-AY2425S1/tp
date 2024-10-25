package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ExpenseTrackerTest {

    @Test
    public void addExpenseWithNewCategory() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addExpense("Lunch", 10.5, "Food");
        assertEquals(1, tracker.getCategories().size());
        assertEquals(1, tracker.getExpenses().size());
        assertEquals("Food", tracker.getCategories().get(0).getName());
    }

    @Test
    public void addExpenseWithExistingCategory() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addCategory("add-category Groceries");
        tracker.addExpense("Apple", 2.0, "Groceries");
        tracker.addExpense("Banana", 1.5, "Groceries");

        assertEquals(1, tracker.getCategories().size());
        assertEquals(2, tracker.getExpenses().size());
    }

    @Test
    public void deleteExpenseValid() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addExpense("Lunch", 10.5, "Food");
        tracker.addExpense("Dinner", 15.0, "Food");

        tracker.deleteExpense(0);
        assertEquals(1, tracker.getExpenses().size());
        assertEquals("Dinner", tracker.getExpenses().get(0).getName());
    }

    @Test
    public void deleteExpenseInvalid() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addExpense("Lunch", 18, "Food");
        tracker.deleteExpense(5);
        assertEquals(1, tracker.getExpenses().size());

        tracker.deleteExpense(7);
        assertEquals(1, tracker.getExpenses().size());
    }

    @Test
    public void viewExpensesByCategory() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addExpense("Lunch", 37, "Food");
        tracker.addExpense("Movie", 20000, "Entertainment");
        tracker.addExpense("Apple", 2.0, "Groceries");

        tracker.viewExpensesByCategory();
    }

    @Test
    public void addCategoryExists() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addCategory("add-category Bro");
        tracker.addCategory("add-category Bro");

        assertEquals(1, tracker.getCategories().size());
    }

    @Test
    public void retrieveBudget() {
        ExpenseTracker tracker = new ExpenseTracker();

        tracker.addCategory("add-category Food");

        tracker.setBudgetLimit("Food", 9000);

        Category foodCategory = tracker.getCategories().get(0);

        Budget foodBudget = tracker.getBudgets().get(foodCategory);
        assertNotNull(foodBudget, "Budget should not be null for 'Food' category.");
        assertEquals(9000, foodBudget.getLimit(), 0.01);
    }

    @Test
    public void removeAllExpenses() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addExpense("Bus", 2.5, "Transport");
        tracker.addExpense("Dinner", 20.0, "Food");

        tracker.deleteExpense(1);
        tracker.deleteExpense(0);

        assertEquals(0, tracker.getExpenses().size(), "All expenses should be removed.");
    }

    @Test
    public void updateBudget() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addCategory("add-category food");
        tracker.setBudgetLimit("food", 500);

        Category foodCategory = tracker.getCategories().get(0);
        Budget foodBudget = tracker.getBudgets().get(foodCategory);
        assertEquals(500, foodBudget.getLimit(), 0.01, "Initial budget should be 500.");

        tracker.setBudgetLimit("Food", 800);
        Budget updatedFoodBudget = tracker.getBudgets().get(foodCategory);
        assertEquals(800, updatedFoodBudget.getLimit(),0.001, "Updated budget should be 800");
    }

    @Test
    public void tagExpenseValid() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addExpense("Lunch", 10.5, "Food");
        tracker.addCategory("add-category Groceries");

        tracker.tagExpense("e/1 c/Groceries");

        Expense taggedExpense = tracker.getExpenses().get(0);

        assertEquals("Groceries", taggedExpense.getCategory().getName(), "Expense should be tagged to Groceries.");
    }

    @Test
    public void addMultipleExpensesDifferentCategories() {
        ExpenseTracker tracker = new ExpenseTracker();

        tracker.addExpense("Lunch", 10.5, "Food");
        tracker.addExpense("Dinner", 15.0, "Food");
        tracker.addExpense("Movie", 12.0, "Entertainment");
        tracker.addExpense("Bus", 3,"Transport");

        assertEquals(4, tracker.getExpenses().size(), "Should have 4 expenses recorded.");
        assertEquals(3, tracker.getCategories().size(), "Should have 3 categories.");
    }

    @Test
    public void addEmptyCategory() {
        ExpenseTracker tracker = new ExpenseTracker();

        tracker.addCategory("add-category ");

        assertEquals(0, tracker.getCategories().size(), "Should not add an empty category.");
    }

}

