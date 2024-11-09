package wheresmymoney.command;

import wheresmymoney.category.CategoryFacade;
import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.Parser;
import wheresmymoney.RecurringExpense;
import wheresmymoney.RecurringExpenseList;
import wheresmymoney.Ui;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCommand extends Command {
    private boolean isFiltered = true;
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
        if (listCategory == null && from == null && to == null) {
            isFiltered = false;
        }
        return expenseList.listByFilter(listCategory, from, to);
    }

    
    /**
     * Get a list of expenses based on various filter metrics
     * 
     * @param recurringExpenseList
     */
    private ArrayList<RecurringExpense> getRecurringExpensesToDisplay(RecurringExpenseList recurringExpenseList) {
        String listCategory = argumentsMap.get(Parser.ARGUMENT_CATEGORY);
        String from = argumentsMap.get(Parser.ARGUMENT_FROM);
        String to = argumentsMap.get(Parser.ARGUMENT_TO);
        if (listCategory == null && from == null && to == null) {
            isFiltered = false;
        }
        return recurringExpenseList.listRecurringByFilter(listCategory, from, to);
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
            Ui.displayMessage(isFiltered ? "No matching expenses were found!" : "No expenses were found!");
            return;
        }
        for (Expense expense: expensesToDisplay) {
            Ui.displayExpense(expenseList, expense);
        }
    }

    /**
     * Display the list of recurring expenses passed to it
     * 
     * @param expensesToDisplay
     * @param recurringExpenseList
     * @throws WheresMyMoneyException
     */
    private void displayRecurringExpenses(ArrayList<RecurringExpense> expensesToDisplay,
            RecurringExpenseList recurringExpenseList) {
        if (expensesToDisplay.isEmpty()) {
            Ui.displayMessage(isFiltered ? "No matching recurring expenses were found!" :
                    "No recurring expenses were found!");
            return;
        }
        for (RecurringExpense recurringExpense: expensesToDisplay) {
            Ui.displayRecurringExpense(recurringExpenseList, recurringExpense);
        }
    }

    /**
     * Display list expenses as requested by user
     */
    @Override
    public void execute(ExpenseList expenseList, CategoryFacade categoryFacade,
            RecurringExpenseList recurringExpenseList) throws WheresMyMoneyException {
        if (this.isRecur()) {
            ArrayList<RecurringExpense> expensesToDisplay = getRecurringExpensesToDisplay(recurringExpenseList);
            displayRecurringExpenses(expensesToDisplay, recurringExpenseList);
        } else {
            ArrayList<Expense> expensesToDisplay = getExpensesToDisplay(expenseList);
            displayExpenses(expensesToDisplay, expenseList);
        }
    }
}
