package wheresmymoney.command;

import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class LoadCommand extends Command {

    private String EXPENSES_FILE_PATH = "./expenses_data.csv";
    private String RECURRING_EXPENSES_FILE_PATH = "./recurring_expenses_data.csv"; 

    public LoadCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Function to implement abstract method of Command class. Do not use
     */
    @Override
    public void execute(ExpenseList expenseList) {}

    /**
     * Loads expenses and recurring expenses from csv
     * 
     * @param expenseList
     * @param recurringExpenseList
     * @throws WheresMyMoneyException
     */
    public void execute(ExpenseList expenseList, RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        expenseList.loadFromCsv(this.EXPENSES_FILE_PATH);
        recurringExpenseList.loadFromCsv(this.RECURRING_EXPENSES_FILE_PATH);
    }
}
