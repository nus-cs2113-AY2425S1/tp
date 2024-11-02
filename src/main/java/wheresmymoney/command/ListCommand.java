package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.Ui;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCommand extends Command {

    public ListCommand(HashMap<String, String> argumentsMap) {
        super(argumentsMap);
    }

    /**
     * Get a list of expenses based on various filter metrics
     *
     * @author shyaamald
     * @param expenseList ExpenseList to be filtered by category, a start date and an end date
     */
    private ArrayList<Expense> getExpensesToDisplay(ExpenseList expenseList) throws WheresMyMoneyException {
        String listCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        String from = argumentsMap.get(Parser.ARGUMENT_FROM);
        String to = argumentsMap.get(Parser.ARGUMENT_TO);
        return expenseList.listByFilter(listCategory, from, to);
    }

    /**
     * Display the list of expenses passed to it
     *
     * @author shyaamald
     * @param expensesToDisplay List of expenses to be displayed
     * @param expenseList Main expense list to retrieve expense indices
     */
    private void displayExpenses(ArrayList<Expense> expensesToDisplay, ExpenseList expenseList)
            throws WheresMyMoneyException {
        if (expensesToDisplay.isEmpty()) {
            Ui.displayMessage("No matching expenses were found!");
            return;
        }
        for (Expense expense: expensesToDisplay) {
            displayExpense(expenseList, expense);
        }
    }

    /**
     * Display index, category, description, price, and date added of the expense passed to it
     *
     * @param expenseList Main expense list to retrieve expense indices
     * @param expense Expense to be displayed
     */
    private static void displayExpense(ExpenseList expenseList, Expense expense) throws WheresMyMoneyException {
        String index = expenseList.getIndexOf(expense) + 1 + ". ";
        String category = "CATEGORY: " + expense.getCategory();
        String description = "DESCRIPTION: " + expense.getDescription();
        String price = "PRICE: " + expense.getPrice();
        String dateAdded = "DATE ADDED: " + expense.getDateAdded();
        Ui.displayMessage(index + category + ", " + description + ", " + price + ", " + dateAdded);
    }

    /**
     * Display list expenses as requested by user
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade) throws WheresMyMoneyException {
        ArrayList<Expense> expensesToDisplay = getExpensesToDisplay(expenseList);
        displayExpenses(expensesToDisplay, expenseList);
    }
}
