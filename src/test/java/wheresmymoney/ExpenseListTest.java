package wheresmymoney;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ExpenseListTest {

    @Test
    public void getList_notNullInput_expenseListIsNotNull() {
        ExpenseList expenseList = new ExpenseList();
        assertNotNull(expenseList);
    }

    @Test
    public void getTotal_emptyExpenseList_totalIsZero() {
        ExpenseList expenseList = new ExpenseList();
        assertEquals(0, expenseList.getTotal());
    }

    @Test
    public void isEmpty_emptyExpenseList_returnTrue() {
        ExpenseList expenseList = new ExpenseList();
        assertEquals(true, expenseList.isEmpty());
    }

    @Test
    public void getExpenseAtIndex_indexIsOutOfBounds_throwsIndexOutOfBoundsException() {
        ExpenseList expenseList = new ExpenseList();
        assertThrows(IndexOutOfBoundsException.class,
                () -> expenseList.getExpenseAtIndex(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> expenseList.getExpenseAtIndex(0));
        assertThrows(IndexOutOfBoundsException.class,
                () -> expenseList.getExpenseAtIndex(1));
    }

    @Test
    public void getIndexOf_expenseNotInList_indexIsMinusOne() {
        ExpenseList expenseList = new ExpenseList();
        Expense expense = new Expense(0F, "", "");
        assertEquals(-1, expenseList.getIndexOf(expense));
    }

    @Test
    public void addExpense_normalExpense_success() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(1.00f, "Ice Cream", "Food");
        Expense expense = expenseList.getExpenseAtIndex(0);
        assertEquals(1.00f, expense.getPrice());
        assertEquals("Ice Cream", expense.getDescription());
        assertEquals("Food", expense.getCategory());
    }

    @Test
    public void addExpense_nullFieldsExpense_success() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(null, null, null);
        Expense expense = expenseList.getExpenseAtIndex(0);
        assertNull(expense.getPrice());
        assertNull(expense.getDescription());
        assertNull(expense.getCategory());
    }

    @Test
    public void editExpense_changePriceAndDescriptionAndCategory_success() throws WheresMyMoneyException {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(1.00f, "Ice Cream", "Food");
        expenseList.editExpense(0, 4.50f, "Taxi", "Transport");
        Expense expense = expenseList.getExpenseAtIndex(0);
        assertEquals(4.50f, expense.getPrice());
        assertEquals("Taxi", expense.getDescription());
        assertEquals("Transport", expense.getCategory());
    }

    @Test
    public void editExpense_changeAllToNull_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(1.00f, "Ice Cream", "Food");
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.editExpense(0, null, null, null));
    }

    @Test
    public void deleteExpense_normalList_reduceListSize() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(1.00f, "Ice Cream", "Food");
        assertEquals(1,  expenseList.getTotal());
        try {
            expenseList.deleteExpense(0);
        } catch (WheresMyMoneyException e) {
            fail();
        }
        assertEquals(0,  expenseList.getTotal());
    }

    @Test
    public void deleteExpense_emptyList_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.deleteExpense(1));
    }

    @Test
    public void listByCategory_normalListByCategory_success() {
        ExpenseList expenseList = new ExpenseList();
        Expense[] sampleArray = {};
        Expense expense = new Expense(1.00f, "Ice Cream", "Food");
        expenseList.addExpense(1.00f, "Ice Cream", "Food");
        expenseList.addExpense(4.50f, "Taxi", "Transport");
        assertEquals(1, expenseList.listByCategory("Food").size());
        assertEquals(expense.getDescription(), expenseList.listByCategory("Food").toArray(sampleArray)[0].getDescription());
    }

    @Test
    public void listByCategory_emptyListByCategory_success() {
        ExpenseList expenseList = new ExpenseList();
        ArrayList<Expense> testArrayList = new ArrayList<>();
        assertEquals(testArrayList, expenseList.listByCategory("Food"));
    }

    @Test
    public void listByCategory_noMatchForCategory_success() {
        ExpenseList expenseList = new ExpenseList();
        ArrayList<Expense> testArrayList = new ArrayList<>();
        expenseList.addExpense(1.00f, "Ice Cream", "Food");
        expenseList.addExpense(4.50f, "Taxi", "Transport");
        assertEquals(testArrayList, expenseList.listByCategory("Travel"));
    }

}
