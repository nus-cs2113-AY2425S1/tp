package seedu.utils;

import seedu.message.ErrorMessages;

/**
 * Utility class for validating and parsing monetary amounts.
 */
public class AmountUtils {

    private static final double MAX_AMOUNT = 1000000000;

    /**
     * Parses the amount from a string to a double and provides detailed error messages.
     *
     * @param amountString The amount in string format to parse.
     * @return The parsed amount as a double.
     * @throws IllegalArgumentException if the amount is invalid.
     */
    public static double parseAmount(String amountString) throws IllegalArgumentException {
        double amount;

        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_AMOUNT_FORMAT + amountString
                    + ErrorMessages.INVALID_AMOUNT_GUIDE);
        }

        if (amount < 0) {
            throw new IllegalArgumentException(ErrorMessages.NEGATIVE_AMOUNT + ": " + amountString);
        }

        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero.");
        }

        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("Amount exceeds maximum limit of " + MAX_AMOUNT);
        }

        return amount;
    }
}
