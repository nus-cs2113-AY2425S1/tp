package wheresmymoney.command;

import wheresmymoney.Parser;
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
        String filePath = argumentsMap.get(Parser.ARGUMENT_MAIN);
        if (filePath.isEmpty()) {
            filePath = this.EXPENSES_FILE_PATH;
        }
        assert(filePath != "");
        try {
            expenseList.loadFromCsv(filePath);
            recurringExpenseList.loadFromCsv(this.RECURRING_EXPENSES_FILE_PATH);
            categoryFacade.loadCategoryInfo(expenseList);
            categoryFacade.displayFilteredCategories();
        } catch (StorageException e) {
            System.out.println(filePath);
            throw new WheresMyMoneyException("Exception occurred when reading from file.");
        }
    }
    
}
