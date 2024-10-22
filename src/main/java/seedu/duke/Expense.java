package seedu.duke;

import java.text.DecimalFormat;

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

    public String formatAmount() {
        DecimalFormat wholeNumberFormat = new DecimalFormat("$#");
        DecimalFormat decimalFormat = new DecimalFormat("$#.00");
        if (amount % 1 == 0) {
            return wholeNumberFormat.format(amount);
        } else {
            return decimalFormat.format(amount);
        }
    }

    @Override
    public String toString() {
        return " Item: " + name + ", Amount: " + formatAmount() + ", Category: " + getCategory();
    }
}
