package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.ExpenseList;
import wheresmymoney.utils.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.exception.WheresMyMoneyException;

public class AddCommand extends Command {

    public AddCommand(ArgumentsMap argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Add an expense or recurring expense after parsing through arguments
     *
     * @param expenseList List of normal expenses
     * @param recurringExpenseList List of recurring expenses
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade, 
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        float price = argumentsMap.getRequiredPrice();
        String description = argumentsMap.getRequired(Parser.ARGUMENT_DESCRIPTION);
        String category = argumentsMap.getRequired(Parser.ARGUMENT_CATEGORY);

        boolean isContainDateKey = argumentsMap.containsKey(Parser.ARGUMENT_DATE);
        if (!this.isRecur() && isContainDateKey) {
            String dateAdded = argumentsMap.get(Parser.ARGUMENT_DATE);
            expenseList.addExpense(price, description, category, dateAdded);
            categoryFacade.addCategory(category, price);
        } else if (!this.isRecur() && !isContainDateKey) {
            expenseList.addExpense(price, description, category);
            categoryFacade.addCategory(category, price);
        } else if (this.isRecur() && isContainDateKey) {
            String lastAddedDate = argumentsMap.get(Parser.ARGUMENT_DATE);
            String frequency = argumentsMap.getRequired(Parser.ARGUMENT_FREQUENCY);
            recurringExpenseList.addRecurringExpense(price, description, category, lastAddedDate, frequency);
        } else {
            String frequency = argumentsMap.getRequired(Parser.ARGUMENT_FREQUENCY);
            recurringExpenseList.addRecurringExpense(price, description, category, frequency);
        }
    }
    
}
