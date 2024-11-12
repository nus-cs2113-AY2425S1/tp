package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.utils.Parser;
import wheresmymoney.Storage;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

public class SaveCommand extends Command {
    public SaveCommand(ArgumentsMap argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Saves data of expenses and recurring expenses to csv
     * 
     * @param expenseList List of normal expenses
     * @param categoryFacade Category facade to perform operations using categories
     * @param recurringExpenseList List of recurring expenses
     * @throws WheresMyMoneyException
     */
    public void execute(ExpenseList expenseList,  CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        Storage.save(expenseList, categoryFacade, recurringExpenseList,
                argumentsMap.get(Parser.ARGUMENT_EXPENSE_LIST),
                argumentsMap.get(Parser.ARGUMENT_CATEGORY_INFO),
                argumentsMap.get(Parser.ARGUMENT_RECURRING_EXPENSE_LIST));
    }
    
}
