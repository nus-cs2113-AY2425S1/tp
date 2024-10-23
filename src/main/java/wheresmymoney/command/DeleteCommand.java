package wheresmymoney.command;

import wheresmymoney.Parser;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException {
        int index = Integer.parseInt(argumentsMap.get(Parser.ARGUMENT_MAIN)) - 1;
        expenseList.deleteExpense(index);
    }
}
