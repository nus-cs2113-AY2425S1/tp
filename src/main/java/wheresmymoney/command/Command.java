package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.utils.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

public abstract class Command {
    protected ArgumentsMap argumentsMap;

    /**
     * Constructs a Command object.
     *
     * @param argumentsMap Arguments passed into the command
     */
    public Command(ArgumentsMap argumentsMap) {
        this.argumentsMap = argumentsMap;
    }
    

    /**
     * Executes the command. To be implemented by the various commands
     *
     * @param expenseList Current ExpenseList
     * @throws WheresMyMoneyException On any error executing the command
     */
    public abstract void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException;

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
