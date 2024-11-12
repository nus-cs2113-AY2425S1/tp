package seedu.budgetbuddy.transaction.saving;

import seedu.budgetbuddy.transaction.expense.Expense;
import seedu.budgetbuddy.transaction.expense.ExpenseManager;
import seedu.budgetbuddy.transaction.income.Income;
import seedu.budgetbuddy.transaction.income.IncomeManager;
import seedu.budgetbuddy.util.LoggerSetup;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Displays the savings of the user.
 * The user can specify if they wish to see their total savings by month or in total.
 */
public class SavingsManager {
    private static final Logger LOGGER = LoggerSetup.getLogger();

    /**
     * Displays the total savings of the user based on all their incomes and expenses.
     * Total Savings = Total Income - Total Expenses
     */
    public static String displayTotalSavings(){
        double savings = 0;
        double totalIncome = 0;
        double totalExpense = 0;

        String result = "";
        if (IncomeManager.getIncomes().size() > 0){
            for (Income income: IncomeManager.getIncomes()){
                totalIncome += income.getAmount();
            }
        }

        if (ExpenseManager.getExpenses().size() > 0){
            for (Expense expense: ExpenseManager.getExpenses()){
                totalExpense += expense.getAmount();
            }
        }
        savings = totalIncome - totalExpense;

        result += "Total Savings: " + String.format("%.2f", savings);
        result += "\n" + "Total Income: " + String.format("%.2f", totalIncome);
        result += "\n" + "Total Expense: " + String.format("%.2f", totalExpense);

        LOGGER.info("Listing total savings");
        return result;
    }

    /**
     * Displays the savings by month in chronological order based on their monthly expenses and incomes.
     * Monthly savings = Monthly Income - Monthly Expense
     */
    public static String displayTotalSavingsByMonth(){
        String result = "";
        ArrayList<YearMonth> listYearMonths = new ArrayList<>();
        ArrayList<Saving> savings = new ArrayList<>();
        if (IncomeManager.getIncomes().size() <= 0 && ExpenseManager.getExpenses().size() <= 0){
            result = "Total savings: 0.00";
            return result;
        }

        for (Income income: IncomeManager.getIncomes()){
            YearMonth incomeYearMonth = IncomeManager.getYearMonthFromDate(income.getDate());
            int indexOfSaving = findYearMonthFromArray(listYearMonths, incomeYearMonth);
            if (indexOfSaving == -1){
                listYearMonths.add(incomeYearMonth);
                savings.add(new Saving(incomeYearMonth, income.getAmount()));
            } else{
                savings.get(indexOfSaving).addIncome(income.getAmount());
            }
        }


        for (Expense expense : ExpenseManager.getExpenses()) {
            YearMonth expenseYearMonth = ExpenseManager.getYearMonthFromDate(expense.getDate());
            int indexOfSaving = findYearMonthFromArray(listYearMonths, expenseYearMonth);
            if (indexOfSaving == -1) {
                listYearMonths.add(expenseYearMonth);
                savings.add(new Saving(expenseYearMonth, -expense.getAmount()));
            } else {
                savings.get(indexOfSaving).deductExpense(expense.getAmount());
            }
        }


        savings.sort(Comparator.comparing(Saving::getYearMonth));
        for (Saving saving: savings){
            result += "Savings in " + saving.getYearMonth() +": " + String.format("%.2f", saving.getSavings());
            if (saving != savings.get(savings.size()-1)){
                result += "\n";
            }
        }
        LOGGER.info("Displaying savings by month");
        return result;
    }

    /**
     * Helper method to get the index of a specified YearMonth in the ArrayList to update savings.
     * @param listYearMonth ArrayList containing all YearMonths of Saving objects.
     * @param yearMonth Specific YearMonth to find in the ArrayList
     * @return index of the YearMonth in the ArrayList, otherwise -1.
     */
    private static int findYearMonthFromArray(ArrayList<YearMonth> listYearMonth, YearMonth yearMonth){
        if (!listYearMonth.contains(yearMonth)){
            return -1;
        } else{
            return listYearMonth.indexOf(yearMonth);
        }
    }
}
