package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class EditCommand extends Command {

    public EditCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Edits an expense or recurring expense
     * 
     * @param expenseList List of expenses
     * @param recurringExpenseList List of recurring expenses
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        try {
            int index = Integer.parseInt(argumentsMap.get(Parser.ARGUMENT_MAIN)) - 1;
            if (this.isRecur()) {
                String oldCategory = recurringExpenseList.getRecurringExpenseAtIndex(index).getCategory();
                Float oldPrice = recurringExpenseList.getRecurringExpenseAtIndex(index).getPrice();
                String newCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
                
                String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
                String dateAdded = argumentsMap.get(Parser.ARGUMENT_DATE);
                String frequency = argumentsMap.get(Parser.ARGUMENT_FREQUENCY);
                
                recurringExpenseList.editRecurringExpense(
                        index, oldPrice, description, newCategory, dateAdded, frequency);
            } else {
                String oldCategory = expenseList.getExpenseAtIndex(index).getCategory();
                Float oldPrice = expenseList.getExpenseAtIndex(index).getPrice();
                String newCategory = getNewCategory(oldCategory);
                Float newPrice = getNewPrice(oldPrice);
                
                String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
                String dateAdded = argumentsMap.get(Parser.ARGUMENT_DATE);
            
                expenseList.editExpense(
                        index, oldPrice, description, newCategory, dateAdded);
                categoryFacade.editCategory(
                        oldCategory, newCategory, oldPrice, newPrice);
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments." + e.getMessage());
        }
    }
    
    private String getNewCategory(String oldCategory) {
        if (argumentsMap.containsKey(Parser.ARGUMENT_CATEGORY)) {
            return argumentsMap.get(Parser.ARGUMENT_PRICE);
        } else {
            return oldCategory;
        }
    }
    private Float getNewPrice(Float oldPrice) throws InvalidInputException {
        if (argumentsMap.containsKey(Parser.ARGUMENT_PRICE)) {
            Float newPrice = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
            if (newPrice <= 0) {
                throw new InvalidInputException("Price cannot be less than or equal to 0.");
            }
            return newPrice;
        } else {
            return oldPrice;
        }
    }
    
}
