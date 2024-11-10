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
    private String oldCategory;
    private Float oldPrice;
    private String newCategory;
    private Float newPrice;
    private String frequency;
    private String description;
    private String dateAdded;

    public EditCommand(ArgumentsMap argumentsMap) {
        super(argumentsMap);
    }

    private void getOldData(ExpenseList expenseList, RecurringExpenseList recurringExpenseList){
        if (!this.isRecur()) {
            oldCategory = expenseList.getExpenseAtIndex(index).getCategory();
            oldPrice = expenseList.getExpenseAtIndex(index).getPrice();
        } else {
            oldCategory = recurringExpenseList.getRecurringExpenseAtIndex(index).getCategory();
            oldPrice = recurringExpenseList.getRecurringExpenseAtIndex(index).getPrice();
        }
    }
    private void retrieveArgumentData() {
        // Retrieving new data from arguments
        if (argumentsMap.containsKey(Parser.ARGUMENT_CATEGORY)) {
            newCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        } else {
            newCategory = oldCategory;
        }

        if (argumentsMap.containsKey(Parser.ARGUMENT_PRICE)) {
            newPrice = argumentsMap.getPrice();
        } else {
            newPrice = oldPrice;
        }
        description = argumentsMap.get(Parser.ARGUMENT_DESCRIPTION);
        dateAdded = argumentsMap.get(Parser.ARGUMENT_DATE);
        frequency = argumentsMap.get(Parser.ARGUMENT_FREQUENCY);
    }

    private void checkForAnyArguments() throws InvalidInputException {
        boolean hasNoArgumentToUpdate = (
                !argumentsMap.containsKey(Parser.ARGUMENT_CATEGORY) &&
                !argumentsMap.containsKey(Parser.ARGUMENT_PRICE) &&
                !argumentsMap.containsKey(Parser.ARGUMENT_DESCRIPTION) &&
                !argumentsMap.containsKey(Parser.ARGUMENT_DATE) &&
                ( (this.isRecur() && !argumentsMap.containsKey(Parser.ARGUMENT_FREQUENCY)) || !this.isRecur() )
        );
        if (hasNoArgumentToUpdate) {
            throw new InvalidInputException("No related arguments were given to update the expense.");
        }
    }

    private void editExpense(ExpenseList expenseList, CategoryFacade categoryFacade,
                             RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        if (this.isRecur()) {
            recurringExpenseList.editRecurringExpense(
                    index, newPrice, description, newCategory, dateAdded, frequency);
        } else {
            expenseList.editExpense(index, newPrice, description, newCategory, dateAdded);
            categoryFacade.editCategory(oldCategory, newCategory, oldPrice, newPrice);
        }
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
        index = argumentsMap.getRequiredIndex();
        getOldData(expenseList, recurringExpenseList);
        checkForAnyArguments();
        retrieveArgumentData();
        editExpense(expenseList, categoryFacade, recurringExpenseList);
    }

}
