package wheresmymoney.command;

import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.WheresMyMoneyException;

import java.util.HashMap;

public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> argumentsList) {
        super(argumentsList);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        int index = Integer.parseInt(argumentsList.get(Parser.ARGUMENT_MAIN)) - 1;
        expenseList.deleteExpense(index);
    }

    @Override
    public String getHelp() {
        return "";
    }
}
