package wheresmymoney.visualizer;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import wheresmymoney.DateUtils;
import wheresmymoney.Expense;
import wheresmymoney.ExpenseList;
import wheresmymoney.exception.StorageException;
import wheresmymoney.exception.WheresMyMoneyException;

import javax.swing.*;

import static java.util.stream.Collectors.groupingBy;

public class Visualizer {
    private ArrayList<Expense> expenses;
    private LocalDate beginDate;
    private LocalDate endDate;
    private int dateRange;
    private List<String> timeSeries = new ArrayList<>();
    private final int DAY_NUMBER_LIMIT = 900;

    public Visualizer(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    private void getBeginDate() {
        beginDate = expenses.get(0).getDateAdded();
        for (Expense expense : expenses) {
            if (expense.getDateAdded().isBefore(beginDate)) {
                beginDate = expense.getDateAdded();
            }
        }
    }

    private void getEndDate(){
        endDate = expenses.get(0).getDateAdded();
        for (Expense expense : expenses) {
            if (expense.getDateAdded().isAfter(endDate)) {
                endDate = expense.getDateAdded();
            }
        }
    }

    private void getTimeRange() {
        assert (beginDate != null && endDate != null);
        dateRange = (int) ChronoUnit.DAYS.between(beginDate, endDate);
    }

    private List<String> createDateList () {
        List<LocalDate> localDateList = beginDate.datesUntil(endDate.plusDays(1)).toList();
        timeSeries = localDateList.stream()
                .map(DateUtils::dateFormatToString).toList();
        for (Expense e : expenses) {
            assert (timeSeries.contains(DateUtils.dateFormatToString(e.getDateAdded())));
        }
        return timeSeries;
    }

    private List<String> createMonthList () { // Fix implementation!!
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");

        LocalDate current = beginDate.withDayOfMonth(1);
        LocalDate end = endDate.withDayOfMonth(1);

        while (!current.isAfter(end)) {
            timeSeries.add(current.format(formatter));
            current = current.plusMonths(1);
        }
        return timeSeries;
    }

    private HashMap<String, Float> groupPriceByDay () {
        HashMap<String, Float> dateToExpenseMap = new HashMap<>();

        // Initialize to give all dates a starting expenditure of 0
        for (String date : timeSeries) {
            dateToExpenseMap.put(date, 0.0f);
        }

        for (Expense e : expenses) {
            String dateKey = DateUtils.dateFormatToString(e.getDateAdded());

            // Increment the spending
            dateToExpenseMap.put(dateKey, dateToExpenseMap.getOrDefault(dateKey, 0.0f) + e.getPrice());
        }
        return dateToExpenseMap;
    }

    private HashMap<String, Float> groupPriceByMonth () {
        HashMap<String, Float> dateToExpenseMap = new HashMap<>();

        for (String month : timeSeries) {
            dateToExpenseMap.put(month, 0.0f);
        }
        for (Expense e : expenses) {
            String dateKey = e.getDateAdded().getMonthValue() + "-" + e.getDateAdded().getYear();
            if (e.getDateAdded().getMonthValue() < 10) { // One-digit month
                dateKey = "0" + dateKey;
            }

            dateToExpenseMap.put(dateKey, dateToExpenseMap.getOrDefault(dateKey, 0.0f) + e.getPrice());
        }
        return dateToExpenseMap;
    }


    public void drawChart() throws WheresMyMoneyException {
        // Calculate the time span in days
        getBeginDate();
        getEndDate();
        getTimeRange();

        if (dateRange > DAY_NUMBER_LIMIT) {
            throw new WheresMyMoneyException("This date range is too large to display!");
        }

        // Group expenses by day or month based on time range
        Map<String, Float> dateToExpenseMap;

        if (dateRange <= 30) {  // For short durations (less than one month)
            timeSeries = createDateList();
            dateToExpenseMap = groupPriceByDay();
        } else {
            timeSeries = createMonthList();
            dateToExpenseMap = groupPriceByMonth();
        }

        // Sort by date for clear plotting
        List<Float> totalExpenses = timeSeries.stream().map(dateToExpenseMap::get).collect(Collectors.toList());

        // Create chart
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Spending Chart")
                .xAxisTitle(dateRange <= 30 ? "Day" : "Month")
                .yAxisTitle("Total Spending")
                .build();

        // Customize chart
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setPlotGridLinesVisible(true);
        // chart.getStyler().setDefaultSeriesRenderStyle(CategoryChart.SeriesRenderStyle.Bar);

        // Add data to chart
        chart.addSeries("Spending", timeSeries, totalExpenses);

        // Display chart
        SwingWrapper<CategoryChart> swingWrapper = new SwingWrapper<>(chart);
        JFrame chartFrame = swingWrapper.displayChart();

        // Set the default close operation to HIDE instead of EXIT
        chartFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}