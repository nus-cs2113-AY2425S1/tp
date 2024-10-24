package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {

    private String FILE_PATH = "./data.csv"; 

    public SaveCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Function to implement abstract method of Command class. Do not use
     */
    public void execute(ExpenseList expenseList) {}

    /**
     * Saves data of expenses and recurring expenses to csv
     * 
     * @param expenseList
     * @param recurringExpenseList
     * @throws WheresMyMoneyException
     */
    public void execute(ExpenseList expenseList, RecurringExpenseList recurringExpenseList) 
            throws WheresMyMoneyException {
        expenseList.saveToCsv(this.FILE_PATH);
        recurringExpenseList.saveToCsv(this.FILE_PATH);
    }
}
