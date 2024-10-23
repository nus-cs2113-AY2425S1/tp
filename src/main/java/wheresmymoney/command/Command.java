package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.WheresMyMoneyException;

import java.util.HashMap;

public abstract class Command {
    protected HashMap<String, String> argumentsList;

    public Command(HashMap<String, String> argumentsList) {
        this.argumentsList = argumentsList;
    }
    public abstract void execute(ExpenseList expenseList) throws WheresMyMoneyException;
    public abstract String getHelp();
}
