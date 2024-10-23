package wheresmymoney.command;

import wheresmymoney.Expense;
import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.Ui;
import wheresmymoney.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCommand extends Command {

    public ListCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        String category = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        ArrayList<Expense> expensesToDisplay;
        if (category == null) {
            expensesToDisplay = expenseList.getList();
        } else {
            expensesToDisplay = expenseList.listByCategory(category);
        }
        Ui.displayExpenseList(expensesToDisplay, expenseList);
    }

    @Override
    public String getHelp() {
        return "";
    }
}
