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
            
            Float newPrice = null;
            if (argumentsMap.containsKey(Parser.ARGUMENT_PRICE)) {
                newPrice = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
                if (newPrice <= 0) {
                    throw new InvalidInputException("Price cannot be less than or equal to 0.");
                }
            }
            
            String newCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
            String newDesc = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
            String newDateAdded = argumentsMap.get(Parser.ARGUMENT_DATE);
            String frequency = argumentsMap.get(Parser.ARGUMENT_FREQUENCY);
            
            if (this.isRecur()) {
                recurringExpenseList.editRecurringExpense(
                        index, newPrice, newDesc, newCategory, newDateAdded, frequency);
            } else {
                Float oldPrice = expenseList.getExpenseAtIndex(index).getPrice();
                String oldCategory = expenseList.getExpenseAtIndex(index).getCategory();
                
                expenseList.editExpense(
                        index, newPrice, newDesc, newCategory, newDateAdded);
                
                if (newPrice == null) {
                    newPrice = oldPrice;
                }
                if (newCategory == null) {
                    newCategory = oldCategory;
                }

                categoryFacade.editCategory(
                        oldCategory, newCategory, oldPrice, newPrice);
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments. " + e.getMessage());
        }
    }
    
}
