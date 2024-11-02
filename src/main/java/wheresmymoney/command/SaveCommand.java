package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class SaveCommand extends Command {

    public SaveCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList,  CategoryFacade categoryFacade) throws WheresMyMoneyException {
        try {
            expenseList.saveToCsv("./data.csv");
            categoryFacade.saveCategoryInfo();
        } catch (StorageException e) {
            throw new WheresMyMoneyException("Exception occurred when saving to file.");
        }
    }
    
}
