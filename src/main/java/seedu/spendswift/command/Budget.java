package seedu.spendswift.command;

//@@author MayFairMI6
public class Budget {
    private String homeCurrency;
    private Category category; // Private to prevent unauthorized access or changes
    private double limit; // Private to control modifications to the budget
    private TrackerData trackerData;
    public Budget(Category category, double limit) {
        this.category = category;
        this.limit = limit;
        this.trackerData= trackerData;
    }

    public Category getCategory() {
        return category;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getRemainingLimit() {
        double totalExpenses = trackerData.getExpenses().stream()
            .filter(e -> e.getCategory().equals(category))
            .mapToDouble(Expense::getAmount)
            .sum();
        return limit - totalExpenses;
    }

    @Override
    public String toString() {
        return "Budget for category '" + category + "' is " + Format.formatAmount(limit);
    }
}
