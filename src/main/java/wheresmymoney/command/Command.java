package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public abstract class Command {
    protected HashMap<String, String> argumentsMap;

    public Command(HashMap<String, String> argumentsMap) {
        this.argumentsMap = argumentsMap;
    }
    
    public abstract void execute(ExpenseList expenseList, RecurringExpenseList recurringExpenseList) 
            throws WheresMyMoneyException;

    public boolean isExit() {
        return false;
    }

    public boolean isRecur() {
        if (argumentsMap.containsKey(Parser.ARGUMENT_RECUR)) {
            return true;
        }
        return false;
    }
}
