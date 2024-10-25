package wheresmymoney;

import org.junit.jupiter.api.Test;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ExpenseListTest {

    @Test
    public void getList_notNullInput_expenseListIsNotNull() {
        ExpenseList expenseList = new ExpenseList();
        assertNotNull(expenseList);
    }
    @Test
    public void getTotal_expenseList_correctTotal() {
        try {
            ExpenseList expenseList = new ExpenseList();
            assertEquals(0, expenseList.getTotal());
            expenseList.addExpense(0.0F, "desc", "category", "25-10-2024");
            assertEquals(1, expenseList.getTotal());
            expenseList.deleteExpense(0);
            assertEquals(0, expenseList.getTotal());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when Expense parameters and list index are valid.");
        }
    }
    @Test
    public void isEmpty_expenseList_correctBoolean() {
        try {
            ExpenseList expenseList = new ExpenseList();
            assertTrue(expenseList.isEmpty());
            expenseList.addExpense(0.0F, "desc", "category", "25-10-2024");
            assertFalse(expenseList.isEmpty());
            expenseList.deleteExpense(0);
            assertTrue(expenseList.isEmpty());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when Expense parameters and list index are valid.");
        }
    }

    @Test
    public void getExpenseAtIndex_indexIsOutOfBounds_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.getExpenseAtIndex(-1));
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.getExpenseAtIndex(0));
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.getExpenseAtIndex(1));
    }
    @Test
    public void getIndexOf_expenseNotInList_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.getIndexOf(
                        new Expense(0.0F, "desc", "category", "25-10-2024")));
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
    public void editExpense_validEditOfExpenseParameters_success() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense(0.01F, "desc1", "cat1", "25-10-2024");
            expenseList.editExpense(0, 0.02F, "desc2", "cat2", "26-10-2024");
            Expense expense = expenseList.getExpenseAtIndex(0);
            assertEquals(0.02F, expense.getPrice());
            assertEquals("desc2", expense.getDescription());
            assertEquals("cat2", expense.getCategory());
            assertEquals("26-10-2024", expense.getDateAdded());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when edit parameters are valid.");
        }
    }
    @Test
    public void editExpense_editPriceToNull_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
            expenseList.editExpense(0, null, "desc2", "cat2", "02-01-2024");
            fail("Exception not thrown when setting price to null");
        } catch (WheresMyMoneyException e) {
            assertThrows(WheresMyMoneyException.class,
                    () -> expenseList.editExpense(0, null, "desc2", "cat2", "02-01-2024"));
        }
    }
    @Test
    public void editExpense_editDescriptionToNull_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
            expenseList.editExpense(0, 0.02F, null, "cat2", "02-01-2024");
            fail("Exception not thrown when setting description to null");
        } catch (WheresMyMoneyException e) {
            assertThrows(WheresMyMoneyException.class,
                    () -> expenseList.editExpense(0, 0.02F, null, "cat2", "02-01-2024"));
        }
    }
    @Test
    public void editExpense_editCategoryToNull_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
            expenseList.editExpense(0, 0.02F, "desc2", null, "02-01-2024");
            fail("Exception not thrown when setting price to null");
        } catch (WheresMyMoneyException e) {
            assertThrows(WheresMyMoneyException.class,
                    () -> expenseList.editExpense(0, 0.02F, "desc2", null, "02-01-2024"));
        }
    }
    @Test
    public void editExpense_editDateAddedToNull_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
            expenseList.editExpense(0, 0.02F, "desc2", "cat2", null);
            fail("Exception not thrown when setting price to null");
        } catch (WheresMyMoneyException e) {
            assertThrows(WheresMyMoneyException.class,
                    () -> expenseList.editExpense(0, 0.02F, "desc2", "cat2", null));
        }
    }

    @Test
    public void deleteExpense_nonemptyList_reduceListSize() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense(0.01F, "desc", "cat");
            assertEquals(1,  expenseList.getTotal());
            expenseList.deleteExpense(0);
            assertEquals(0, expenseList.getTotal());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when Expense parameters and list index are valid.");
        }
    }
    @Test
    public void deleteExpense_emptyList_throwsWheresMyMoneyException() {
        ExpenseList expenseList = new ExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.deleteExpense(1));
    }

    @Test
    public void listByCategory_validListByCategory_success() {
        ExpenseList expenseList = new ExpenseList();
        try {
            expenseList.addExpense(0.01F, "desc1", "cat1");
            expenseList.addExpense(0.02F, "desc2", "cat2");
            assertEquals(1, expenseList.listByCategory("cat1").size());
            Expense expenseToFindInList = new Expense(0.03F, "desc1", "cat1");
            Expense expenseOfSameCategory = expenseList.listByCategory("cat1").get(0);
            assertEquals(expenseToFindInList.getDescription(), expenseOfSameCategory.getDescription());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when Expenses' parameters are valid.");
        }
    }
    @Test
    public void listByCategory_emptyListByCategory_success() {
        ExpenseList expenseList = new ExpenseList();
        ArrayList<Expense> testArrayList = new ArrayList<>();
        assertEquals(testArrayList, expenseList.listByCategory("cat"));
    }
    @Test
    public void listByCategory_noMatchForCategory_success() {
        ExpenseList expenseList = new ExpenseList();
        ArrayList<Expense> testArrayList = new ArrayList<>();
        try {
            expenseList.addExpense(0.01F, "desc1", "cat1");
            expenseList.addExpense(0.02F, "desc2", "cat2");
            assertEquals(testArrayList, expenseList.listByCategory("cat"));
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when Expenses' parameters are valid.");
        }
    }

}
