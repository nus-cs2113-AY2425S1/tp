package seedu.utils;

import seedu.message.ErrorMessages;

/**
 * Utility class for validating and parsing monetary amounts.
 */
public class AmountUtils {

    /**
     * Validates if the provided amount string is a valid positive decimal number.
     *
     * @param amountString The amount in string format to validate.
     * @return true if the amount is valid, false otherwise.
     */
    public static boolean isValidAmount(String amountString) {
        try {
            double amount = Double.parseDouble(amountString);
            return (amount > 0 && amount <= 1000000000);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses the amount from a string to a double.
     *
     * @param amountString The amount in string format to parse.
     * @return The parsed amount as a double.
     * @throws IllegalArgumentException if the amount is invalid.
     */
    public static double parseAmount(String amountString) throws IllegalArgumentException {
        if (!isValidAmount(amountString)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_AMOUNT_FORMAT + amountString
                    + ErrorMessages.INVALID_AMOUNT_GUIDE);
        }
        return Double.parseDouble(amountString);
    }
}
