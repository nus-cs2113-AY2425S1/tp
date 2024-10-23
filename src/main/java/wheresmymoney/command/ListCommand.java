package wheresmymoney.command;

import wheresmymoney.Expense;
import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.Ui;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCommand extends Command {

    public ListCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Displays list expenses as requested by user
     */
    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        String listCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        ArrayList<Expense> expensesToDisplay;
        if (listCategory == null) {
            expensesToDisplay = expenseList.getList();
        } else {
            expensesToDisplay = expenseList.listByCategory(listCategory);
        }

        for (Expense expense: expensesToDisplay) {
            String index = expenseList.getIndexOf(expense) + 1 + ". ";
            String category = "CATEGORY: " + expense.getCategory();
            String description = "   DESCRIPTION: " + expense.getDescription();
            String price = "   PRICE: " + expense.getPrice();
            Ui.displayMessage(index + category + description + price);
        }
    }
}
