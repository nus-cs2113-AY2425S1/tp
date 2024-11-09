package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

public class DeleteCommand extends Command {

    public DeleteCommand(ArgumentsMap argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Deletes an expense or recurring expense given its index in the list
     * @param expenseList List of expenses
     * @param recurringExpenseList List of recurring expenses
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        int index = argumentsMap.getRequiredIndex();
        try {
            if (this.isRecur()) {
                recurringExpenseList.deleteRecurringExpense(index);
            } else {
                Expense expense = expenseList.getExpenseAtIndex(index);
                expenseList.deleteExpense(index);
                categoryFacade.deleteCategory(expense.getCategory(), expense.getPrice());
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments.");
        }
    }
    
}
