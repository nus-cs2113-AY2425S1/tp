package seedu.budget;

import seedu.message.ErrorMessages;
import seedu.transaction.Transaction;
import seedu.transaction.Expense;
import seedu.transaction.TransactionList;
import seedu.utils.DateTimeUtils;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The BudgetTracker class manages monthly budgets and tracks expenses in a specified month.
 * It supports setting budgets, calculating spending progress, and validating the budget for
 * current and past months.
 */
public class BudgetTracker {
    private static final Logger logger = Logger.getLogger("BudgetTracker");
    private Map<YearMonth, Double> monthlyBudgets;
    private TransactionList transactionList;

    /**
     * Constructs a BudgetTracker with the specified transaction list.
     *
     * @param transactionList The TransactionList instance used to track monthly expenses.
     */
    public BudgetTracker(TransactionList transactionList) {
        this.transactionList = transactionList;
        this.monthlyBudgets = new HashMap<>();
    }

    /**
     * Sets a budget amount for a given month if the month is current or in the future.
     *
     * @param monthStr        The YearMonth to set the budget for.
     * @param budgetAmount The budget amount for the specified month.
     */
    public void setBudget(String monthStr, double budgetAmount) throws IllegalArgumentException {
        YearMonth month;

        if (budgetAmount < 0) {
            throw new IllegalArgumentException("Budget amount cannot be negative");
        }

        try {
            month = DateTimeUtils.parseYearMonth(monthStr);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Invalid date format for budget month: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }

        if (month.isBefore(YearMonth.now())) {
            String message = ErrorMessages.MESSAGE_PAST_MONTH_BUDGET;
            logger.log(Level.WARNING, message);
            throw new IllegalArgumentException(message);
        }

        monthlyBudgets.put(month, budgetAmount);
        logger.log(Level.INFO, "Budget set for " + month + ": $" + budgetAmount);
    }

    public void setMonthlyBudgets(Map<YearMonth, Double> monthlyBudgets) {
        this.monthlyBudgets = monthlyBudgets;
        logger.log(Level.INFO, "Monthly budgets initialized.");
    }

    public Map<YearMonth, Double> getMonthlyBudgets() {
        return monthlyBudgets;
    }

    public Double getMonthlyBudget(YearMonth month) {
        return monthlyBudgets.get(month);
    }

    /**
     * Calculates the total expenses for a specified month.
     *
     * @param month The YearMonth to retrieve expenses for.
     * @return The total expense amount for the specified month.
     */
    public double getMonthlyExpense(YearMonth month) {
        double expense = transactionList.getTransactions().stream()
                .filter(transaction -> transaction instanceof Expense)
                .filter(transaction -> YearMonth.from(transaction.getDate()).equals(month))
                .mapToDouble(Transaction::getAmount)
                .sum();
        logger.log(Level.INFO, "Total expenses for " + month + ": $" + expense);
        return expense;
    }

    public String calculateCurrentProgress(YearMonth month) {
        double budget = monthlyBudgets.get(month);
        int currentDay = LocalDateTime.now().getDayOfMonth();
        int daysInMonth = month.lengthOfMonth();
        double currentMonthExpense = getMonthlyExpense(month);
        double projectedSpending = (currentMonthExpense / currentDay) * daysInMonth;

        if (currentMonthExpense > budget) {
            return String.format("Warning! You've already exceeded your budget for %s. Spent: $%.2f, Budget: $%.2f.",
                    month, currentMonthExpense, budget);
        }

        if (projectedSpending <= budget) {
            return String.format("Great job! You're on track. Spent so far: $%.2f, " +
                            "Projected spending: $%.2f (within the budget of $%.2f).",
                    currentMonthExpense, projectedSpending, budget);
        }

        return String.format("Caution! You're on track to exceed the budget. Spent so far: $%.2f, " +
                        "Projected spending: $%.2f, Budget: $%.2f.",
                currentMonthExpense, projectedSpending, budget);
    }

    public String calculatePastProgress(YearMonth month) {
        double budget = monthlyBudgets.get(month);
        double expense = getMonthlyExpense(month);

        if (expense > budget) {
            return String.format("Budget Exceeded! You spent $%.2f out of your budget of $%.2f." +
                            " Consider reviewing your expenses for better control in future months.",
                    expense, budget);
        }

        return String.format("Well Done! You stayed within your budget for %s. Spent: $%.2f," +
                        " Budget: $%.2f. Keep up the good work!",
                month, expense, budget);
    }

    public String checkBudgetProgress(YearMonth month) {
        if (month.isAfter(YearMonth.now())) {
            logger.log(Level.WARNING, "Cannot check progress for future months.");
            return ErrorMessages.MESSAGE_TRACK_FUTURE_MONTH_BUDGET;
        }

        if (!monthlyBudgets.containsKey(month)) {
            logger.log(Level.WARNING, "No budget found for " + month);
            return ErrorMessages.BUDGET_NOT_FOUND;
        }

        if (month.equals(YearMonth.now())) {
            return calculateCurrentProgress(month);
        }
        return calculatePastProgress(month);
    }
}
