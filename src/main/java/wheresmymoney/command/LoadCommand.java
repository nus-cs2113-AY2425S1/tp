package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class LoadCommand extends Command {

    private static String EXPENSES_FILE_PATH = "./expenses_data.csv";
    private static String RECURRING_EXPENSES_FILE_PATH = "./recurring_expenses_data.csv"; 

    public LoadCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Loads expenses and recurring expenses from csv
     * 
     * @param expenseList
     * @param recurringExpenseList
     * @throws WheresMyMoneyException
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        try {
            expenseList.loadFromCsv(this.EXPENSES_FILE_PATH);
            recurringExpenseList.loadFromCsv(this.RECURRING_EXPENSES_FILE_PATH);
            categoryFacade.loadCategoryInfo(expenseList);
            categoryFacade.displayFilteredCategories();
        } catch (StorageException e) {
            throw new WheresMyMoneyException("Exception occurred when reading from file.");
        }
    }
    
}
