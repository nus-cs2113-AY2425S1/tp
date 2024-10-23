package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class ByeCommand extends Command {
    public ByeCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList) throws WheresMyMoneyException { }

    @Override
    public boolean isExit() {
        return true;
    }
}
