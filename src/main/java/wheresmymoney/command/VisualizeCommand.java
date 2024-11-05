package wheresmymoney.command;

import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.Ui;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.exception.WheresMyMoneyException;
import wheresmymoney.visualizer.Visualizer;

import java.util.ArrayList;
import java.util.HashMap;

public class VisualizeCommand extends Command {

    public VisualizeCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
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
            return;
        }
        assert (expensesToVisualize.size() > 0);

        // Execute by calling visualizer
        Visualizer visualizer = new Visualizer(expensesToVisualize);
        visualizer.visualize();
    }

    private boolean hasNullDate(String from, String to) {
        return (from == null && to == null);
    }

    private boolean hasNullCategory(String category) {
        return (category == null);
    }
    /**
     * Checks if given parameters are valid
     *
     * @author andrewnguyen4
     * @return true if exactly one of [category, (from/to)] is provided
     */
    private boolean isValidParameters(String category, String from, String to) {
        return (hasNullCategory(category) ^ hasNullDate(from, to));
    }
}
