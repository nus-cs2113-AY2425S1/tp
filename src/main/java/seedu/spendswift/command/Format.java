//@@author glenda-1506
package seedu.spendswift.command;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Utility class for formatting input strings and monetary amounts.
 */
public class Format {

    /**
     * Formatting a string to capitalize its first letter, and lower-casing the rest.
     *
     * @param input The string to format.
     * @return A formatted string.
     */
    public static String formatInput(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Formats a monetary amount to a string with a dollar sign and two decimal places.
     *
     * @param amount The monetary amount to format.
     * @return A formatted amount.
     */
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
