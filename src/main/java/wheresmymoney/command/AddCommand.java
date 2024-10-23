package wheresmymoney.command;

import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.WheresMyMoneyException;

import java.util.HashMap;

public class AddCommand extends Command {

    public AddCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        float price = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
        String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
        String category = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        expenseList.addExpense(price, description, category);
    }

    @Override
    public String getHelp() {
        return "";
    }
}
