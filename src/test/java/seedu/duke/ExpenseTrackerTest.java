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
        tracker.addCategory("Groceries");
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
        tracker.addCategory("Food");
        tracker.setBudgetLimit("Food", 9000);
        Category foodCategory = new Category("food");


        assertNotNull(tracker.getBudgets().get(foodCategory));
        assertEquals(9000, tracker.getBudgets().get(foodCategory));
    }

    @Test
    public void addCategoryExists() {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.addCategory("Bro");
        tracker.addCategory("Bro");

        assertEquals(1, tracker.getCategories().size());


    }


}
