package wheresmymoney;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {
    private List<Expense> expenses;


    public ExpenseList() {
        expenses = new ArrayList<>();
    }


    public List<Expense> getList() {
        return expenses;
    }
    public int getTotal() {
        return expenses.size();
    }
    public boolean isEmpty() {
        return expenses.isEmpty();
    }
    public Expense getExpenseAtIndex(int i) {
        return expenses.get(i);
    }
    public int getIndexOf(Expense expense) {
        return expenses.indexOf(expense);
    }


    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    public void deleteExpense(int i) {
        expenses.remove(i);
    }

}
