package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.category.CategoryTracker;
import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade) throws WheresMyMoneyException {
        try {
            int index = Integer.parseInt(argumentsMap.get(Parser.ARGUMENT_MAIN)) - 1;
            Expense expense = expenseList.getExpenseAtIndex(index);
            expenseList.deleteExpense(index);
            
            categoryFacade.deleteCategory(expense.getCategory(), expense.getPrice());
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments.");
        }
    }
    
}
