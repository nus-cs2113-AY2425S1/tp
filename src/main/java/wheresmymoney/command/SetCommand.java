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
        String category = argumentsMap.getRequired(Parser.ARGUMENT_CATEGORY);
        Float limit = argumentsMap.getRequiredLimit();
        categoryFacade.setCategorySpendingLimit(category, limit);
    }
    
}
