package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {

    private String EXPENSES_FILE_PATH = "./expenses_data.csv";
    private String RECURRING_EXPENSES_FILE_PATH = "./recurring_expenses_data.csv"; 

    public SaveCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Saves data of expenses and recurring expenses to csv
     * 
     * @param expenseList
     * @param recurringExpenseList
     * @throws WheresMyMoneyException
     */
    public void execute(ExpenseList expenseList,  CategoryFacade categoryFacade, RecurringExpenseList recurringExpenseList) 
            throws WheresMyMoneyException {
        try {
            expenseList.saveToCsv(this.EXPENSES_FILE_PATH);
            recurringExpenseList.saveToCsv(this.RECURRING_EXPENSES_FILE_PATH);
            categoryFacade.saveCategoryInfo();
        } catch (StorageException e) {
            throw new WheresMyMoneyException("Exception occurred when saving to file.");
        }
    }
    
}
