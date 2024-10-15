package seedu.duke;

public class Expense {
    private String name;

    private double amount;

    private String category;

    public Expense(String name, double amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category != null ? category : "null";
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return " Item: " + name + ", Amount: $" + amount + ", Category: " + getCategory();
    }
}
