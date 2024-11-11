package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.utils.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

public class SetCommand extends Command {
    public SetCommand(ArgumentsMap argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Set spending limit for a category
     *
     * @param expenseList List of normal expenses
     * @param categoryFacade Category facade to perform operations using categories
     * @param recurringExpenseList List of recurring expenses
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        String category = argumentsMap.getRequired(Parser.ARGUMENT_CATEGORY);
        Float limit = argumentsMap.getRequiredLimit();
        categoryFacade.setCategorySpendingLimit(category, limit);
    }
    
}
