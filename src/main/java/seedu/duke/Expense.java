package seedu.duke;

import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Expense {
    private String name;
    private double amount;
    private Category category;

    public Expense(String name, double amount, Category category) {
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

    public Category getCategory() {
        return category != null ? category : null;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String formatAmount() {
        BigDecimal roundedAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
        DecimalFormat wholeNumberFormat = new DecimalFormat("$#");
        DecimalFormat decimalFormat = new DecimalFormat("$#.00");
        if (roundedAmount.stripTrailingZeros().scale() <= 0) {
            return wholeNumberFormat.format(roundedAmount);
        } else {
            return decimalFormat.format(roundedAmount);
        }
    }

    @Override
    public String toString() {
        return " Item: " + name + ", Amount: " + formatAmount() + ", Category: " + getCategory();
    }
}
