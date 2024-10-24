package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {

    private String EXPENSES_FILE_PATH = "./expenses_data.csv";
    private String RECURRING_EXPENSES_FILE_PATH = "./recurring_expenses_data.csv"; 

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
        expenseList.saveToCsv(this.EXPENSES_FILE_PATH);
        recurringExpenseList.saveToCsv(this.RECURRING_EXPENSES_FILE_PATH);
    }
}
