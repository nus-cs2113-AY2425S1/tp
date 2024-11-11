package wheresmymoney;

import org.junit.jupiter.api.Test;
import wheresmymoney.exception.WheresMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class RecurringExpenseListTest {
        
    private static String JAN_START_OF_MONTH = "1-1-2024";
    private static String JAN_MIDDLE_OF_MONTH = "15-1-2024";
    private static String JAN_END_OF_MONTH = "31-1-2024";
    private static String JUNE_START_OF_MONTH = "1-6-2024";
    private static String JUNE_MIDDLE_OF_MONTH = "15-6-2024";
    private static String JUNE_END_OF_MONTH = "30-6-2024";

    private static String RECURRING_EXPENSES_FILE_PATH = "./recurring_expenses_data.csv";

    private RecurringExpenseList initRecurringExpenseList() {
        
        ExpenseList expenseList = new ExpenseList();
        RecurringExpenseList recurringExpenseList = new RecurringExpenseList(expenseList);
        
        try {
            recurringExpenseList.addRecurringExpense(1.00F, "desc1", "cat1", JAN_START_OF_MONTH, "daily");
            recurringExpenseList.addRecurringExpense(2.50F, "desc2", "cat2", JAN_MIDDLE_OF_MONTH, "weekly");
            recurringExpenseList.addRecurringExpense(3.99F, "desc3", "cat3", JAN_END_OF_MONTH, "monthly");
            recurringExpenseList.addRecurringExpense(4.00F, "desc4", "cat4", JUNE_START_OF_MONTH, "daily");
            recurringExpenseList.addRecurringExpense(5.50F, "desc5", "cat5", JUNE_MIDDLE_OF_MONTH, "weekly");
            recurringExpenseList.addRecurringExpense(6.99F, "desc6", "cat6", JUNE_END_OF_MONTH, "monthly");
            
            recurringExpenseList.saveToCsv(RECURRING_EXPENSES_FILE_PATH);
        } catch (WheresMyMoneyException e) {
            Ui.displayMessage(e.getMessage());
        }
        return recurringExpenseList;
    }

    @Test
    public void getList_notNullInput_recurringExpenseListIsNotNull() {
        RecurringExpenseList recurringExpenseList = initRecurringExpenseList();
        assertNotNull(recurringExpenseList);
    }

    @Test
    public void isEmpty_recurringExpenseList_correctBoolean() {
        try {
            ExpenseList expenseList = new ExpenseList();
            RecurringExpenseList recurringExpenseList = new RecurringExpenseList(expenseList);
            assertTrue(recurringExpenseList.isEmpty());
            recurringExpenseList.addRecurringExpense(10.0F, "desc", "category", "daily");
            assertFalse(recurringExpenseList.isEmpty());
            recurringExpenseList.deleteRecurringExpense(0);
            assertTrue(recurringExpenseList.isEmpty());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when Expense parameters and list index are valid.");
        }
    }

    @Test
    public void getRecurringExpenseAtIndex_indexIsOutOfBounds_throwsWheresMyMoneyException() {
        RecurringExpenseList recurringExpenseList = initRecurringExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> recurringExpenseList.getExpenseAtIndex(-1));
        assertThrows(WheresMyMoneyException.class,
                () -> recurringExpenseList.getExpenseAtIndex(0));
        assertThrows(WheresMyMoneyException.class,
                () -> recurringExpenseList.getExpenseAtIndex(1));
    }

    @Test
    public void getRecurringExpenseAtIndex_recurringExpenseInList_correctRecurringExpenseReturned() {
        RecurringExpenseList recurringExpenseList = initRecurringExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> recurringExpenseList.getExpenseAtIndex(-1));
        assertThrows(WheresMyMoneyException.class,
                () -> recurringExpenseList.getExpenseAtIndex(0));
        assertThrows(WheresMyMoneyException.class,
                () -> recurringExpenseList.getExpenseAtIndex(1));
    }

    @Test
    public void getIndexOf_recurringExpenseNotInList_throwsWheresMyMoneyException() {
        RecurringExpenseList recurringExpenseList = initRecurringExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> recurringExpenseList.getIndexOf(
                        new RecurringExpense(0.0F, "desc", "category", "25-10-2024")));
    }

    @Test
    public void addRecurringExpense_validExpenseWithDateSpecified_success() {
        ExpenseList expenseList = new ExpenseList();
        RecurringExpenseList recurringExpenseList = new RecurringExpenseList(expenseList);
        try {
            recurringExpenseList.addRecurringExpense(0.01F, "desc", "cat", "05-11-2024", "daily");
            RecurringExpense recurringExpense = recurringExpenseList.getRecurringExpenseAtIndex(0);
            assertEquals(0.01F, recurringExpense.getPrice());
            assertEquals("desc", recurringExpense.getDescription());
            assertEquals("cat", recurringExpense.getCategory());
            assertEquals("05-11-2024", DateUtils.dateFormatToString(recurringExpense.getDateAdded()));
            assertEquals("05-11-2024", recurringExpense.getLastAddedDate());
            assertEquals("daily", recurringExpense.getFrequency());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when all expense parameters are valid.");
        }
    }

    @Test
    public void addRecurringExpense_validExpenseWithDateUnspecified_success() {
        ExpenseList expenseList = new ExpenseList();
        RecurringExpenseList recurringExpenseList = new RecurringExpenseList(expenseList);
        try {
            recurringExpenseList.addRecurringExpense(0.01F, "desc", "cat", "daily");
            RecurringExpense recurringExpense = recurringExpenseList.getRecurringExpenseAtIndex(0);
            assertEquals(0.01F, recurringExpense.getPrice());
            assertEquals("desc", recurringExpense.getDescription());
            assertEquals("cat", recurringExpense.getCategory());
            assertEquals(DateUtils.dateFormatToString(DateUtils.getCurrentDate()), 
                    DateUtils.dateFormatToString(recurringExpense.getDateAdded()));
            assertEquals(DateUtils.dateFormatToString(DateUtils.getCurrentDate()), 
                    recurringExpense.getLastAddedDate());
            assertEquals("daily", recurringExpense.getFrequency());
        } catch (WheresMyMoneyException e) {
            fail("Exception thrown when all expense parameters are valid.");
        }
    }

}
