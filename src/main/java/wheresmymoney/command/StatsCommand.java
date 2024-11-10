package wheresmymoney.command;

import wheresmymoney.utils.ArgumentsMap;
import wheresmymoney.category.CategoryFacade;
import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.utils.Parser;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.Ui;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;

/**
 * @author shyaamald
 */
public class StatsCommand extends Command {

    public StatsCommand(ArgumentsMap argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Get the list of expenses based on various filter metrics
     *
     * @param expenseList ExpenseList to be filtered by category, a start date and an end date
     */
    private ArrayList<Expense> getList(ExpenseList expenseList) throws WheresMyMoneyException {
        String statsCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        String from = argumentsMap.get(Parser.ARGUMENT_FROM);
        String to = argumentsMap.get(Parser.ARGUMENT_TO);
        return expenseList.listByFilter(statsCategory, from, to);
    }

    /**
     * Display the highest expense, lowest expense and mean price of the expense list passed to it
     *
     * @param filteredExpenses List of expenses to be displayed
     * @param expenseList Main expense list to retrieve expense indices
     */
    private void displayStats(ArrayList<Expense> filteredExpenses, ExpenseList expenseList)
            throws WheresMyMoneyException {
        if (filteredExpenses.isEmpty()) {
            Ui.displayMessage("No matching expenses were found!");
            return;
        }
        Expense init = filteredExpenses.get(0);
        float sum = 0;
        Expense highest = init;
        Expense lowest = init;
        for (Expense expense : filteredExpenses) {
            float price = expense.getPrice();
            sum += price;
            if (price > highest.getPrice()) {
                highest = expense;
            }
            if (price < lowest.getPrice()) {
                lowest = expense;
            }
        }
        float mean = sum/filteredExpenses.size();
        double truncatedMean = (Math.floor(mean * 100) / 100);
        Ui.displayMessage("HIGHEST EXPENSE:" + highest.getPrice());
        Ui.displayExpense(expenseList, highest);
        Ui.displayMessage("LOWEST EXPENSE:" + lowest.getPrice());
        Ui.displayExpense(expenseList, lowest);
        Ui.displayMessage("TOTAL EXPENSES: " + sum);
        Ui.displayMessage("AVERAGE PRICE: " + truncatedMean);
    }

    /**
     * Display stats of expenses as requested by user
     *
     * @param expenseList Main list of expenses
     * @param categoryFacade Main category facade to perform operations using categories
     * @param recurringExpenseList Main recurring expense list
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade,
                        RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        ArrayList<Expense> listOfExpenses = getList(expenseList);
        displayStats(listOfExpenses, expenseList);
    }
}
