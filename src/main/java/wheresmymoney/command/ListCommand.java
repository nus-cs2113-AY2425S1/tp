package wheresmymoney.command;

import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.Ui;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCommand extends Command {

    public ListCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    private ArrayList<Expense> getExpensesToDisplay(ExpenseList expenseList) {
        String listCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        if (listCategory == null) {
            return expenseList.getList();
        } else {
            return expenseList.listByCategory(listCategory);
        }
    }

    private void displayExpenses(ArrayList<Expense> expensesToDisplay, ExpenseList expenseList) {
        for (Expense expense: expensesToDisplay) {
            String index = expenseList.getIndexOf(expense) + 1 + ". ";
            String category = "CATEGORY: " + expense.getCategory();
            String description = "   DESCRIPTION: " + expense.getDescription();
            String price = "   PRICE: " + expense.getPrice();
            Ui.displayMessage(index + category + description + price);
        }
    }

    /**
     * Displays list expenses as requested by user
     */
    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        ArrayList<Expense> expensesToDisplay = getExpensesToDisplay(expenseList);
        displayExpenses(expensesToDisplay, expenseList);
    }
}
