//@@author glenda-1506
package seedu.spendswift.command;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Format {
    public static String formatInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String formatAmount(double amount) {
        BigDecimal roundedAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
        DecimalFormat wholeNumberFormat = new DecimalFormat("$0");
        DecimalFormat decimalFormat = new DecimalFormat("$0.00");
        if (roundedAmount.stripTrailingZeros().scale() <= 0) {
            return wholeNumberFormat.format(roundedAmount);
        } else {
            return decimalFormat.format(roundedAmount);
        }
    }
}
