package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.utils.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

public class SetCommand extends Command {
    public SetCommand(ArgumentsMap argumentsMap) {
        super(argumentsMap);
    }
    
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpense) throws WheresMyMoneyException {
        try {
            String category = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
            float limit = Float.parseFloat(argumentsMap.get(Parser.ARGUMENT_LIMIT));
            categoryFacade.setCategorySpendingLimit(category, limit);
        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidInputException("Invalid Arguments");
        }
    }
    
}
