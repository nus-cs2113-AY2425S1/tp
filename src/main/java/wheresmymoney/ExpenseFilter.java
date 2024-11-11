package wheresmymoney;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

import wheresmymoney.exception.WheresMyMoneyException;

public class ExpenseFilter {

    public static boolean isBeforeEndDate(Expense expense, LocalDate endDate){
        return (!expense.getDateAdded().isAfter(endDate));
    }

    public static boolean isAfterStartDate(Expense expense, LocalDate startDate){
        return (!expense.getDateAdded().isBefore(startDate));
    }

    public static boolean isInCategory(Expense expense, String category) {
        return (expense.getCategory().equals(category));
    }

    /**
     * Check if an expense is a match with the given filter criteria
     * If a criteria is null, that field is considered a match
     *
     * @author andrewnguyen4
     * @param expense the expense under check
     * @param category The filter category
     * @param startDate Expense's dateAdded must not be before this date
     * @param endDate Expense's dateAdded must not be after this date
     *
     * @return true if all criteria are either null or matched
     */
    public static boolean isFiltered(Expense expense, String category, String startDate, String endDate)
            throws WheresMyMoneyException {
        if (category != null && !isInCategory(expense, category)) {
            return false;
        }
        if (startDate != null && !isAfterStartDate(expense, DateUtils.stringToDate(startDate))) {
            return false;
        }
        if (endDate != null && !isBeforeEndDate(expense, DateUtils.stringToDate(endDate))) {
            return false;
        }
        return true;
    }

    //@@author shyaamald
    /**
     * Filter a list of expenses using the given criteria
     *
     * @param expenses List of expenses to be filtered
     * @param category The filter's category
     * @param from The filter's start date
     * @param to The filter's start date
     *
     * @return List of filtered expenses
     */
    public static ArrayList<Expense> filterExpenses(ArrayList<Expense> expenses, String category,
                                                    String from, String to) throws WheresMyMoneyException {
        ArrayList<Expense> expenseByFilter = new ArrayList<>();
        for (Expense expense : expenses) {
            if (isFiltered(expense, category, from, to)) {
                Logging.log(Level.INFO, "Found matching expense: " + expense.getDescription());
                expenseByFilter.add(expense);
            }
        }
        return expenseByFilter;
    }

    public static ArrayList<RecurringExpense> filterRecurringExpenses(ArrayList<RecurringExpense> recurringExpenses, 
            String category, String from, String to) throws WheresMyMoneyException {
        ArrayList<RecurringExpense> expenseByFilter = new ArrayList<>();
        for (RecurringExpense recurringExpense : recurringExpenses) {
            if (isFiltered(recurringExpense, category, from, to)) {
                Logging.log(Level.INFO, "Found matching expense: " + recurringExpense.getDescription());
                expenseByFilter.add(recurringExpense);
            }
        }
        return expenseByFilter;
    }
}
