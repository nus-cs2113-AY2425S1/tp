package wheresmymoney;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {
    private static List<Expense> expenses;

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


    /**
     * Add an expense to the end of the list
     *
     * @param price New price of expense
     * @param description New description of expense
     * @param category New category of expense
     */
    public void addExpense(Float price, String description, String category) {
        Expense expense = new Expense(price, description, category);
        expenses.add(expense);
    }

    /**
     * Edit the details of an expense given its position in the list
     *
     * @param index index of Expense in the list that is to be edited
     * @param category New category of expense
     * @param price New price of expense
     * @param description New description of expense
     */
    public void editExpense(int index, String category, float price, String description) {
        Expense expense = expenses.get(index);
        expense.setCategory(category);
        expense.setPrice(price);
        expense.setDescription(description);
    }

    public void deleteExpense(int index) throws WheresMyMoneyException {
        if (index < 0 || index >= expenses.size()) {
            throw new WheresMyMoneyException("Index out of range!");
        }
        expenses.remove(index);
    }

    /**
     * Returns the list of all expenses from the specified category
     *
     * @param category Category of expense
     */
    public static Expense[] listByCategory(String category) {
        Expense[] expenseArray = new Expense[0];
        ArrayList<Expense> returnList = new ArrayList<>();
        for (Expense expense: expenses) {
            if (expense.category.equals(category)) {
                returnList.add(expense);
            }
        }
        return returnList.toArray(expenseArray);
    }
}
