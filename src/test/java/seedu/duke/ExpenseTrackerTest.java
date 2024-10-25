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
    public void addCategoryExists() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addCategory("add-category Bro");
        tracker.addCategory("add-category Bro");

        assertEquals(1, tracker.getCategories().size());


    }


}
