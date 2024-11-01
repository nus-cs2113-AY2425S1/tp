package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class LoadCommand extends Command {

    public LoadCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade) throws WheresMyMoneyException {
        try {
            expenseList.loadFromCsv("./data.csv");
            categoryFacade.loadCategoryInfo(expenseList);
            categoryFacade.displayFilteredCategories();
        } catch (StorageException e) {
            throw new WheresMyMoneyException("Exception occurred when reading from file.");
        }
    }
    
}
