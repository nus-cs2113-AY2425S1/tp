package wheresmymoney.command;

import wheresmymoney.Parser;
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
        String filePath = argumentsMap.get(Parser.ARGUMENT_MAIN);
        if (filePath.isEmpty()) {
            filePath = "./data.csv";
        }
        assert(filePath != "");
        try {
            expenseList.loadFromCsv(filePath);
            categoryFacade.loadCategoryInfo(expenseList);
            categoryFacade.displayFilteredCategories();
        } catch (StorageException e) {
            System.out.println(filePath);
            throw new WheresMyMoneyException("Exception occurred when reading from file.");
        }
    }
    
}
