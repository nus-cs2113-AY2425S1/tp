package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public abstract class Command {
    protected HashMap<String, String> argumentsMap;

    /**
     * Constructs a Command object.
     *
     * @param argumentsMap Arguments passed into the command
     */
    public Command(HashMap<String, String> argumentsMap) {
        this.argumentsMap = argumentsMap;
    }
    

    /**
     * Executes the command. To be implemented by the various commands
     *
     * @param expenseList Current ExpenseList
     * @throws WheresMyMoneyException On any error executing the command
     */
    public abstract void execute(ExpenseList expenseList, RecurringExpenseList recurringExpenseList) 
            throws WheresMyMoneyException;


    /**
     * Returns a boolean representing if the entire program should exit.
     *
     * @return Whether the program should exit
     */
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
