package wheresmymoney.command;

import wheresmymoney.Parser;
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
        String filePath = argumentsMap.get(Parser.ARGUMENT_MAIN);
        if (filePath.isEmpty()) {
            filePath = "./data.csv";
        }
        assert(filePath != "");
        try {
            expenseList.saveToCsv(filePath);
            categoryFacade.saveCategoryInfo();
        } catch (StorageException e) {
            System.out.println(filePath);
            throw new WheresMyMoneyException("Exception occurred when saving to file.");
        }
    }
    
}
