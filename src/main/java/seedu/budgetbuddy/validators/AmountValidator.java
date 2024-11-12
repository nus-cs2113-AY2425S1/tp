package seedu.budgetbuddy.validators;

/**
 * Validates and converts amount from String to double.
 */
public class AmountValidator {
    /**
     * Converts amount from String to double.
     *
     * @param part The String containing the amount.
     * @return The parsed amount or -1 if invalid.
     */
    public static double validateAmount(String part) {
        for (int i = 2; i < part.length(); i++) {
            if (!(Character.isDigit(part.charAt(i)) || part.charAt(i) == '.'|| part.charAt(i) == '-')) {
                return -1;
            }
        }
        try {
            double amount = Double.parseDouble(part.substring(2));
            if (amount * 100 % 1 != 0) {
                return -1;
            }
            return amount;
        } catch (NumberFormatException e) {
            return -1;  // Indicates invalid amount
        }
    }
}
