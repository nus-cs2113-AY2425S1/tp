package seedu.duke.util;

public class Commons {
    public static final String LINE_SEPARATOR = "--------------------------------------------";

    //Error messages
    public static final String ERROR_MESSAGE_INVALID_INDEX
            = "Invalid index. Please provide a valid integer less than or equal to 5000.";
    public static final String ERROR_MESSAGE_NON_NUMBER_AMOUNT = "Invalid amount. Please use a number (e.g. 13.00).";
    public static final String ERROR_MESSAGE_EMPTY_AMOUNT
            = "Invalid argument. Please do not leave compulsory arguments blank.";
    public static final String ERROR_MESSAGE_ARGUMENT_NULL = "Argument cannot be empty or blank.";
    public static final String ERROR_MESSAGE_AMOUNT_TOO_SMALL = "Invalid amount. Amount must be $0.01 or greater.";
    public static final String ERROR_MESSAGE_AMOUNT_TOO_LARGE = "Invalid amount. Amount must be $9999999.00 or less.";
    public static final String ERROR_MESSAGE_AMOUNT_RANGE
            = "Budget amount must be >= $0.01 and <= $9999999.00. Please enter a valid amount.";
    public static final String ERROR_MESSAGE_DATE_TOO_LATE = "Entered date cannot be after current date.";
    public static final String ERROR_MESSAGE_BLANK_DESCRIPTION = "Description cannot be blank.";
    public static final String ERROR_MESSAGE_OUT_OF_BOUNDS_INDEX = "There is no entry at index: ";
    public static final String ERROR_MESSAGE_INVALID_ARGUMENT = "Invalid argument. Please provide a valid argument.";
    public static final String ERROR_MESSAGE_MAX_CAPACITY_EXCEEDED
            = "Maximum number of transactions reached. Please delete some transactions before adding more.";
    public static final String ERROR_MESSAGE_NO_CHANGES_DETECTED = "All fields of this entry are still the same...";

    //Keys/flags for command arguments
    public static final String KEY_FIRST_ARGUMENT = "argument";
    public static final String FLAG_DESCRIPTION = "/des";
    public static final String FLAG_DATE = "/d";
    public static final String FLAG_AMOUNT = "/a";
    public static final String FLAG_START_POINT = "/from";
    public static final String FLAG_END_POINT = "/to";
    public static final String FLAG_CATEGORY = "/c";

    //Key Amounts
    public static final double MIN_AMOUNT = 0.01;
    public static final double MAX_AMOUNT = 9999999.00;
    public static final int MAX_NUM_OF_ENTRIES = 5000;
    /**
     * Prints a message enclosed in line separators.
     * Primarily used for error messages.
     *
     * @param message message to be printed.
     */
    public static void printSingleLineWithBars(String message){
        System.out.println(Commons.LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(Commons.LINE_SEPARATOR);
    }

    /**
     * Method to round off a double to 2 decimal places
     *
     * @param amount amount to be rounded off.
     * @return amount rounded off to 2 decimal places.
     */
    public static double roundToTwoDP(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
