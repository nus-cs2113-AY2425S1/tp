package seedu.duke;
public class Budget {
    private Category category; // Private to prevent unauthorized access or changes
    private double limit;// Private to control modifications to the budget
    public Budget(Category category, double limit) {
        this.category = category;
        this.limit = limit;
    }
    public Category getCategory() {
        return category;
    }
    public double getLimit() {
        return limit;
    }
    public void setLimit(double limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Budget limit cannot be negative.");
        }
        this.limit = limit;
    }
    @Override
    public String toString() {
        return "Budget for category '" + category + "' is $" + limit;
    }
}
