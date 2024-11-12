package seedu.budgetbuddy.transaction.saving;

import java.time.YearMonth;

/**
 * Represents a monthly saving with an amount and a YearMonth attribute.
 * Monthly savings is calculated based on income - expense.
 */
public class Saving {
    private YearMonth yearMonth;
    private double savings;

    /**
     * Creates a Saving object with the specified YearMonth and savings.
     * @param yearMonth YearMonth of the saving
     * @param savings amount saved that month
     */
    public Saving(YearMonth yearMonth, double savings){
        this.yearMonth = yearMonth;
        this.savings = savings;
    }

    /**
     * Retrieves YearMonth of the object.
     * @return A YearMonth representing the month of the saving
     */
    public YearMonth getYearMonth() {
        return yearMonth;
    }

    /**
     * Retrieves the amount of savings for the month.
     * @return A double representing the amount saved that month
     */
    public double getSavings() {
        return savings;
    }

    /**
     * Adds an earned income specified by the user to the savings of that month.
     * @param income Earned income to be added.
     */
    public void addIncome(double income){
        savings += income;
    }

    /**
     * Deducts an expense specified by the user from the savings of that month.
     * @param expense Expense to be deducted from savings.
     */
    public void deductExpense(double expense){
        savings -= expense;
    }
}
