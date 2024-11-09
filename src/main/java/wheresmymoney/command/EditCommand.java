package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.utils.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

public class EditCommand extends Command {
    private int index;
    private String newCategory;
    private String frequency;
    private String description;
    private String dateAdded;

    public EditCommand(ArgumentsMap argumentsMap) {
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
            String oldCategory;
            float oldPrice;
            if (!this.isRecur()) {
                oldCategory = expenseList.getExpenseAtIndex(index).getCategory();
                oldPrice = expenseList.getExpenseAtIndex(index).getPrice();
            } else {
                oldCategory = recurringExpenseList.getRecurringExpenseAtIndex(index).getCategory();
                oldPrice = recurringExpenseList.getRecurringExpenseAtIndex(index).getPrice();
            }

            String newCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
            float newPrice;
            if (argumentsMap.containsKey(Parser.ARGUMENT_PRICE)) {
                newPrice = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
            } else {
                newPrice = oldPrice;
            }
            if (argumentsMap.containsKey(Parser.ARGUMENT_PRICE)) {
                oldPrice = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_PRICE));
                if (oldPrice <= 0) {
                    throw new InvalidInputException("Price cannot be less than or equal to 0.");
                }
            }

            String description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
            String dateAdded = argumentsMap.get(Parser.ARGUMENT_DATE);
            if (this.isRecur()) {
                String frequency = argumentsMap.get(Parser.ARGUMENT_FREQUENCY);
                recurringExpenseList.editRecurringExpense(
                        index, oldPrice, description, newCategory, dateAdded, frequency);
            } else {
                expenseList.editExpense(index, oldPrice, description, newCategory, dateAdded);
                categoryFacade.editCategory(oldCategory, newCategory, oldPrice, newPrice);
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments.");
        }
    }
    
}
