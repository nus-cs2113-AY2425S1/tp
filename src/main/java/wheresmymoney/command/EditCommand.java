package wheresmymoney.command;

import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class EditCommand extends Command {

    public EditCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        int index = Integer.parseInt(argumentsMap.get(Parser.ARGUMENT_MAIN)) - 1;
        String category = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        float price = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
        String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
        expenseList.editExpense(index, price, description, category);
    }
}
