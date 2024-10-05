package wheresmymoney;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Add an expense to the end of the list
     * @param argumentsList The list of arguments passed by Parser
     */
    public void addExpense(HashMap<String, String> argumentsList) {
        float price = Float.parseFloat(argumentsList.get(Parser.ARGUMENT_PRICE));
        String description = argumentsList.get(Parser.ARGUMENT_DESCRIPTION);
        String category = argumentsList.get(Parser.ARGUMENT_CATEGORY);
        Expense expense = new Expense(price, description, category);
        addExpense(expense);
    }

    /**
     * Edit the details of an expense given its position in the list
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

    public void deleteExpense(int i) {
        expenses.remove(i);
    }

}
