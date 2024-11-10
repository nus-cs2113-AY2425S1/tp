package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Deletes an expense or recurring expense given its index in the list
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
            if (this.isRecur()) {
                recurringExpenseList.deleteExpense(index);
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
