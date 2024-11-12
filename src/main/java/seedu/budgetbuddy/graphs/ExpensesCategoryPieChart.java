package seedu.budgetbuddy.graphs;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import seedu.budgetbuddy.transaction.Category;
import seedu.budgetbuddy.transaction.expense.Expense;

import javax.swing.JFrame;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static seedu.budgetbuddy.transaction.expense.ExpenseManager.getExpenses;
import static seedu.budgetbuddy.transaction.expense.ExpenseManager.getYearMonthFromDate;

/**
 * Generates and displays a pie chart of expenses sliced by category.
 */
public class ExpensesCategoryPieChart {

    /**
     * Builds a map of total expenses grouped by category for a given month.
     *
     * @param yearMonth The year and month for which to calculate expenses.
     * @return A map where the keys are categories and the values are the total expenses for that category.
     */
    public static Map<Category, Double> expensesByCategoryMapBuilder(YearMonth yearMonth) {
        Map<Category, Double> expensesByCategoryMap = new HashMap<>();

        for (Category category : Category.values()) {
            double categoryTotal = getTotalExpensesForMonthWithCategories(yearMonth, category);
            expensesByCategoryMap.put(category, categoryTotal);
        }

        return expensesByCategoryMap;
    }

    /**
     * Displays a pie chart of expenses by category for a specific month.
     *
     * @param yearMonth The year and month for which to display the expenses.
     * @param expensesByCategoryMap A map containing the total expenses for each category.
     */
    public static void displayExpenseByCategoryPieChart(YearMonth yearMonth, Map<Category, Double>
            expensesByCategoryMap) {
        // Initialize the pie chart
        PieChart pieChart = new PieChartBuilder()
                .width(800)
                .height(600)
                .title("Expenses by Category for " + yearMonth)
                .build();

        // Set visibility of the legend and labels
        pieChart.getStyler().setLegendVisible(true);
        pieChart.getStyler().setLabelsVisible(true);

        // Add each category to the chart with the expense amount shown on each slice
        expensesByCategoryMap.forEach((category, expense) -> {
            String label = category.name() + " " + expense.toString();
            pieChart.addSeries(label, expense);
        });

        //Display the chart in window
        SwingWrapper<PieChart> swingWrapper = new SwingWrapper<>(pieChart);
        JFrame frame = swingWrapper.displayChart();
        frame.setTitle("BudgetBuddy");

        // Ensure that closing the window does not end the program
        javax.swing.SwingUtilities.invokeLater(() -> frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE));
    }

    /**
     * Calculates the total expenses for a specific month and category.
     *
     * @param yearMonth The year and month for which to calculate expenses.
     * @param category The category for which to calculate the total expenses.
     * @return The total amount of expenses for the specified month and category.
     */
    public static double getTotalExpensesForMonthWithCategories(YearMonth yearMonth, Category category) {
        ArrayList<Expense> expensesOverMonthArray = getExpenses();
        double totalAmount = 0.0;

        for (Expense expense : expensesOverMonthArray) {
            YearMonth expenseYearMonth = getYearMonthFromDate(expense.getDate());

            if (expenseYearMonth.equals(yearMonth) && category.equals(expense.getCategory())) {
                totalAmount += expense.getAmount();
            }
        }
        return totalAmount;
    }
}
