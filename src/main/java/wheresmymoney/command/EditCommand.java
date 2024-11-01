package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.category.CategoryTracker;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class EditCommand extends Command {

    public EditCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade) throws WheresMyMoneyException {
        try {
            int index = Integer.parseInt(argumentsMap.get(Parser.ARGUMENT_MAIN)) - 1;
            String oldCategory = expenseList.getExpenseAtIndex(index).getCategory();
            
            String newCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
            float price = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
            String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
            String dateAdded = argumentsMap.get(Parser.ARGUMENT_DATE_ADDED);
            
            expenseList.editExpense(index, price, description, newCategory, dateAdded);
            CategoryFacade.editCategory(categoryTracker, oldCategory, newCategory, price);
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments.");
        }
    }
    
}
