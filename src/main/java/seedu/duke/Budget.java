package seedu.duke;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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

    public String formatLimit(double limit) {
        BigDecimal roundedLimit = BigDecimal.valueOf(limit).setScale(2, RoundingMode.HALF_UP);
        DecimalFormat wholeNumberFormat = new DecimalFormat("$#");
        DecimalFormat decimalFormat = new DecimalFormat("$#.00");

        if (roundedLimit.stripTrailingZeros().scale() <= 0) {
            return wholeNumberFormat.format(roundedLimit);
        } else {
            return decimalFormat.format(roundedLimit);
        }
    }

    @Override
    public String toString() {
        return "Budget for category '" + category + "' is " + formatLimit(limit);
    }
}
