package wheresmymoney.command;

import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.WheresMyMoneyException;

import java.util.HashMap;

public class EditCommand extends Command {

    public EditCommand(HashMap<String, String> argumentsList) {
        super(argumentsList);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        int index = Integer.parseInt(argumentsList.get(Parser.ARGUMENT_MAIN)) - 1;
        String category = argumentsList.get(Parser.ARGUMENT_CATEGORY);
        float price = Float.parseFloat(argumentsList.get(Parser.ARGUMENT_PRICE));
        String description = argumentsList.get(Parser.ARGUMENT_DESCRIPTION);
        expenseList.editExpense(index, price, description, category);
    }

    @Override
    public String getHelp() {
        return "";
    }
}
