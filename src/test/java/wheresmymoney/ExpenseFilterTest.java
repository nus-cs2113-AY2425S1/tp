package wheresmymoney;

import org.junit.jupiter.api.Test;

import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpenseFilterTest {

    private ExpenseList initExpenseList() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.addExpense(1.0F, "desc1", "category1", "01-01-2021");
        expenseList.addExpense(1.0F, "desc1", "category1", "02-02-2022");
        expenseList.addExpense(2.0F, "desc2", "category2", "03-03-2023");
        expenseList.addExpense(2.0F, "desc2", "category3", "04-04-2021");
        expenseList.addExpense(3.0F, "desc3", "category2", "05-05-2022");
        expenseList.addExpense(3.0F, "desc3", "category3", "06-06-2023");
        return expenseList;
    }

    @Test
    public void listByFilter_nullInput_returnAll() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter(null, null, null);
        assert (filteredExpenseList.size() == 6);
    }

    @Test
    public void listByFilter_wrongInputFormat_throwException() {
        ExpenseList expenseList = initExpenseList();
        assertThrows(WheresMyMoneyException.class,
                () -> expenseList.listByFilter(null, "wrong format", null));
    }

    @Test
    public void listByFilter_categoryOnly_returnNone() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter("abc category", null, null);
        assert(filteredExpenseList.isEmpty());
    }

    @Test
    public void listByFilter_categoryOnly_success() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter("category1", null, null);
        assert (filteredExpenseList.size() == 2);
        assert (filteredExpenseList.get(0).equals(expenseList.getExpenseAtIndex(0)));
        assert (filteredExpenseList.get(1).equals(expenseList.getExpenseAtIndex(1)));
    }

    @Test
    public void listByFilter_fromDateOnly_returnNone() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter(null, "12-12-2024", null);
        assert(filteredExpenseList.isEmpty());
    }

    @Test
    public void listByFilter_fromDateOnly_success() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter(null, "01-01-2023", null);
        assert (filteredExpenseList.size() == 2);
        assert (filteredExpenseList.get(0).equals(expenseList.getExpenseAtIndex(2)));
        assert (filteredExpenseList.get(1).equals(expenseList.getExpenseAtIndex(5)));
    }

    @Test
    public void listByFilter_toDateOnly_returnNone() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter(null, null, "01-01-0001");
        assert(filteredExpenseList.isEmpty());
    }

    @Test
    public void listByFilter_toDateOnly_success() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter(null, "04-04-2021", "05-05-2022");
        assert (filteredExpenseList.size() == 3);
        assert (filteredExpenseList.get(0).equals(expenseList.getExpenseAtIndex(1)));
        assert (filteredExpenseList.get(1).equals(expenseList.getExpenseAtIndex(3)));
        assert (filteredExpenseList.get(2).equals(expenseList.getExpenseAtIndex(4)));
    }

    @Test
    public void listByFilter_fromAndTo_returnNone() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter(null, "02-02-2023", "02-02-2022");
        assert(filteredExpenseList.isEmpty());
    }

    @Test
    public void listByFilter_fromAndTo_success() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter(null, "04-04-2021", "02-02-2022");
        assert (filteredExpenseList.size() == 2);
        assert (filteredExpenseList.get(0).equals(expenseList.getExpenseAtIndex(1)));
        assert (filteredExpenseList.get(1).equals(expenseList.getExpenseAtIndex(3)));
    }

    @Test
    public void listByFilter_categoryAndDate_returnNone() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter("category2", null, "02-02-2022");
        assert(filteredExpenseList.isEmpty());
    }

    @Test
    public void listByFilter_categoryAndDate_success() {
        ExpenseList expenseList = initExpenseList();
        ArrayList<Expense> filteredExpenseList = expenseList.listByFilter("category3", null, "02-02-2022");
        assert (filteredExpenseList.size() == 1);
        assert (filteredExpenseList.get(0).equals(expenseList.getExpenseAtIndex(3)));
    }
}
