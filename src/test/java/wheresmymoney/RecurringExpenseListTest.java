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

    // RecurringExpense recurringExpense1 = 
    // new RecurringExpense(1.00F, "desc1", "cat1", JAN_START_OF_MONTH, "daily");
    // RecurringExpense recurringExpense2 = 
    // new RecurringExpense(2.50F, "desc2", "cat2", JAN_MIDDLE_OF_MONTH, "weekly");
    // RecurringExpense recurringExpense3 = new RecurringExpense(3.99F, "desc3", "cat3", JAN_END_OF_MONTH, "monthly");
    // RecurringExpense recurringExpense4 = 
    // new RecurringExpense(4.00F, "desc4", "cat4", JUNE_START_OF_MONTH, "daily");
    // RecurringExpense recurringExpense5 = 
    // new RecurringExpense(5.50F, "desc5", "cat5", JUNE_MIDDLE_OF_MONTH, "weekly");
    // RecurringExpense recurringExpense6 = 
    // new RecurringExpense(6.99F, "desc6", "cat6", JUNE_END_OF_MONTH, "monthly");

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
            recurringExpenseList.addRecurringExpense(0.0F, "desc", "category", "daily");
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

    // @Test
    // public void addExpense_invalidExpenseParameters_throwsWheresMyMoneyException() {
    //     ExpenseList expenseList = new ExpenseList();
    //     assertThrows(WheresMyMoneyException.class,
    //             () -> expenseList.addExpense(null, "desc", "category", "25-10-2024"));
    //     assertThrows(WheresMyMoneyException.class,
    //             () -> expenseList.addExpense(0.0F, null, "category", "25-10-2024"));
    //     assertThrows(WheresMyMoneyException.class,
    //             () -> expenseList.addExpense(0.0F, "desc", null, "25-10-2024"));
    //     assertThrows(WheresMyMoneyException.class,
    //             () -> expenseList.addExpense(0.0F, "desc", "category", null));
    // }

    // @Test
    // public void editExpense_validEditOfExpenseParameters_success() {
    //     ExpenseList expenseList = new ExpenseList();
    //     try {
    //         expenseList.addExpense(0.01F, "desc1", "cat1", "25-10-2024");
    //         expenseList.editExpense(0, 0.02F, "desc2", "cat2", "26-10-2024");
    //         Expense expense = expenseList.getExpenseAtIndex(0);
    //         assertEquals(0.02F, expense.getPrice());
    //         assertEquals("desc2", expense.getDescription());
    //         assertEquals("cat2", expense.getCategory());
    //         assertEquals("26-10-2024", DateUtils.dateFormatToString(expense.getDateAdded()));
    //     } catch (WheresMyMoneyException e) {
    //         fail("Exception thrown when edit parameters are valid.");
    //     }
    // }
    // @Test
    // public void editExpense_editPriceToNull_throwsWheresMyMoneyException() {
    //     ExpenseList expenseList = new ExpenseList();
    //     try {
    //         expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
    //         expenseList.editExpense(0, null, "desc2", "cat2", "02-01-2024");
    //         fail("Exception not thrown when setting price to null");
    //     } catch (WheresMyMoneyException e) {
    //         assertThrows(WheresMyMoneyException.class,
    //                 () -> expenseList.editExpense(0, null, "desc2", "cat2", "02-01-2024"));
    //     }
    // }
    // @Test
    // public void editExpense_editDescriptionToNull_throwsWheresMyMoneyException() {
    //     ExpenseList expenseList = new ExpenseList();
    //     try {
    //         expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
    //         expenseList.editExpense(0, 0.02F, null, "cat2", "02-01-2024");
    //         fail("Exception not thrown when setting description to null");
    //     } catch (WheresMyMoneyException e) {
    //         assertThrows(WheresMyMoneyException.class,
    //                 () -> expenseList.editExpense(0, 0.02F, null, "cat2", "02-01-2024"));
    //     }
    // }
    // @Test
    // public void editExpense_editCategoryToNull_throwsWheresMyMoneyException() {
    //     ExpenseList expenseList = new ExpenseList();
    //     try {
    //         expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
    //         expenseList.editExpense(0, 0.02F, "desc2", null, "02-01-2024");
    //         fail("Exception not thrown when setting price to null");
    //     } catch (WheresMyMoneyException e) {
    //         assertThrows(WheresMyMoneyException.class,
    //                 () -> expenseList.editExpense(0, 0.02F, "desc2", null, "02-01-2024"));
    //     }
    // }
    // @Test
    // public void editExpense_editDateAddedToNull_throwsWheresMyMoneyException() {
    //     ExpenseList expenseList = new ExpenseList();
    //     try {
    //         expenseList.addExpense(0.01F, "desc", "cat", "01-01-2024");
    //         expenseList.editExpense(0, 0.02F, "desc2", "cat2", null);
    //         fail("Exception not thrown when setting price to null");
    //     } catch (WheresMyMoneyException e) {
    //         assertThrows(WheresMyMoneyException.class,
    //                 () -> expenseList.editExpense(0, 0.02F, "desc2", "cat2", null));
    //     }
    // }

    // @Test
    // public void deleteExpense_nonemptyList_reduceListSize() {
    //     ExpenseList expenseList = new ExpenseList();
    //     try {
    //         expenseList.addExpense(0.01F, "desc", "cat");
    //         assertEquals(1,  expenseList.getTotal());
    //         expenseList.deleteExpense(0);
    //         assertEquals(0, expenseList.getTotal());
    //     } catch (WheresMyMoneyException e) {
    //         fail("Exception thrown when Expense parameters and list index are valid.");
    //     }
    // }

    // /**
    //  * Tests the invalid deletion of an expense from an empty expense list.
    //  */
    // @Test
    // public void deleteExpense_emptyList_throwsWheresMyMoneyException() {
    //     ExpenseList expenseList = new ExpenseList();
    //     assertThrows(WheresMyMoneyException.class,
    //             () -> expenseList.deleteExpense(1));
    // }

    // @Test
    // public void listByCategory_validListByCategory_success() {
    //     ExpenseList expenseList = new ExpenseList();
    //     try {
    //         expenseList.addExpense(0.01F, "desc1", "cat1");
    //         expenseList.addExpense(0.02F, "desc2", "cat2");
    //         assertEquals(1, expenseList.listByCategory("cat1").size());
    //         Expense expenseToFindInList = new Expense(0.03F, "desc1", "cat1");
    //         Expense expenseOfSameCategory = expenseList.listByCategory("cat1").get(0);
    //         assertEquals(expenseToFindInList.getDescription(), expenseOfSameCategory.getDescription());
    //     } catch (WheresMyMoneyException e) {
    //         fail("Exception thrown when Expenses' parameters are valid.");
    //     }
    // }
    // @Test
    // public void listByCategory_emptyListByCategory_success() {
    //     ExpenseList expenseList = new ExpenseList();
    //     ArrayList<Expense> testArrayList = new ArrayList<>();
    //     assertEquals(testArrayList, expenseList.listByCategory("cat"));
    // }
    // @Test
    // public void listByCategory_noMatchForCategory_success() {
    //     ExpenseList expenseList = new ExpenseList();
    //     ArrayList<Expense> testArrayList = new ArrayList<>();
    //     try {
    //         expenseList.addExpense(0.01F, "desc1", "cat1");
    //         expenseList.addExpense(0.02F, "desc2", "cat2");
    //         assertEquals(testArrayList, expenseList.listByCategory("cat"));
    //     } catch (WheresMyMoneyException e) {
    //         fail("Exception thrown when Expenses' parameters are valid.");
    //     }
    // }

}
