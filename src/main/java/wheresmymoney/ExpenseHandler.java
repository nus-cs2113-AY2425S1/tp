package wheresmymoney;

import java.util.ArrayList;
import java.util.List;

public class ExpenseHandler {
    private List<Expense> expenses;

    // Constructor
    public ExpenseHandler() {
        expenses = new ArrayList<>();
    }

    // Getters
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

    // CRUD
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    public void deleteExpense(int i) {
        expenses.remove(i);
    }

}
