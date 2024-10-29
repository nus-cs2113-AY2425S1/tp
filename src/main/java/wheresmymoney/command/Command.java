package wheresmymoney.command;

import wheresmymoney.ExpenseList;
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
    public abstract void execute(ExpenseList expenseList) throws WheresMyMoneyException;

    /**
     * Returns a boolean representing if the entire program should exit.
     *
     * @return Whether the program should exit
     */
    public boolean isExit(){
        return false;
    }
}
