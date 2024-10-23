package wheresmymoney.command;

import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.WheresMyMoneyException;

import java.util.HashMap;

public class AddCommand extends Command {

    public AddCommand(HashMap<String, String> argumentsList) {
        super(argumentsList);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        float price = Float.parseFloat(argumentsList.get(Parser.ARGUMENT_PRICE));
        String description = argumentsList.get(Parser.ARGUMENT_DESCRIPTION);
        String category = argumentsList.get(Parser.ARGUMENT_CATEGORY);
        expenseList.addExpense(price, description, category);
    }

    @Override
    public String getHelp() {
        return "";
    }
}
