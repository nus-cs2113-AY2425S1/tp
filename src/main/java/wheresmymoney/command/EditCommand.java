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
     * @param expenseList List of normal expenses
     * @param categoryFacade Category facade to perform operations using categories
     * @param recurringExpenseList List of recurring expenses
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        try {
            int index = Integer.parseInt(argumentsMap.get(Parser.ARGUMENT_MAIN)) - 1;
            String oldCategory;
            float price;
            if (!this.isRecur()) {
                oldCategory = expenseList.getExpenseAtIndex(index).getCategory();
                price = expenseList.getExpenseAtIndex(index).getPrice();
            } else {
                oldCategory = recurringExpenseList.getRecurringExpenseAtIndex(index).getCategory();
                price = recurringExpenseList.getRecurringExpenseAtIndex(index).getPrice();
            }
            
            String newCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
            if (newCategory == null) {
                newCategory = oldCategory;
            }
            if (argumentsMap.containsKey(Parser.ARGUMENT_PRICE)) {
                price = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
                if (price <= 0) {
                    throw new InvalidInputException("Price cannot be less than or equals to 0");
                }
            }
            String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
            String dateAdded = argumentsMap.get(Parser.ARGUMENT_DATE);
            if (this.isRecur()) {
                String frequency = argumentsMap.get(Parser.ARGUMENT_FREQUENCY);
                recurringExpenseList.editRecurringExpense(index, price, description, newCategory, dateAdded, frequency);
            } else {
                expenseList.editExpense(index, price, description, newCategory, dateAdded);
                categoryFacade.editCategory(oldCategory, newCategory, price);
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments.");
        }
    }
    
}
