package wheresmymoney.command;

import wheresmymoney.*;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class VisualizeCommand extends Command {

    public VisualizeCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Checks if given parameters are valid
     *
     * @author andrewnguyen4
     * @return true if [category is null and at least one of (from, to) is not null],
     *              or [category is not null and both (from, to) are null]
     */
    private boolean isValidParameters(String category, String from, String to) {
        boolean hasNullCategory = (category == null);
        boolean hasNullDate = (from == null && to == null);
        return (hasNullCategory && !hasNullDate) || (!hasNullCategory && hasNullDate);
    }

    /**
     * Get a list of expenses based on various filter metrics
     *
     * @author andrewnguyen4
     * @param expenseList ExpenseList to be filtered by category, a start date and an end date
     */
    private ArrayList<Expense> getExpensesToVisualize(ExpenseList expenseList) throws WheresMyMoneyException {
        String category = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        String from = argumentsMap.get(Parser.ARGUMENT_FROM);
        String to = argumentsMap.get(Parser.ARGUMENT_TO);
        if (!isValidParameters(category, from, to)) {
            throw new WheresMyMoneyException("Please provide either category or from/to date!");
        }
        return expenseList.listByFilter(category, from, to);
    }


    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade,
                        RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        ArrayList<Expense> expensesToVisualize = getExpensesToVisualize(expenseList);
        if (expensesToVisualize.isEmpty()) {
            Ui.displayMessage("No matching expenses were found!");
        }
        // Execute by calling visualizer
    }

}
