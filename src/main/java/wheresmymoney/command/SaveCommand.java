package wheresmymoney.command;

import wheresmymoney.Parser;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {
    private static String EXPENSES_FILE_PATH = "./expenses_data.csv";
    private static String RECURRING_EXPENSES_FILE_PATH = "./recurring_expenses_data.csv"; 

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
    public void execute(ExpenseList expenseList,  CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        String filePath = argumentsMap.get(Parser.ARGUMENT_MAIN);
        if (filePath.isEmpty()) {
            filePath = this.EXPENSES_FILE_PATH;
        }
        assert(filePath != "");
        try {
            expenseList.saveToCsv(filePath);
            recurringExpenseList.saveToCsv(this.RECURRING_EXPENSES_FILE_PATH);
            categoryFacade.saveCategoryInfo();
        } catch (StorageException e) {
            System.out.println(filePath);
            throw new WheresMyMoneyException("Exception occurred when saving to file.");
        }
    }
    
}
