package seedu.duke.util;

public class Commons {
    public static final String LINE_SEPARATOR = "--------------------------------------------";
    public static final String ERROR_MESSAGE_INVALID_INDEX
            = "Invalid index. Please provide a valid integer less than or equal to 2147483647.";
    public static final String ERROR_MESSAGE_NON_NUMBER_AMOUNT = "Invalid amount. Please use a number (e.g. 13.00).";
    public static final String ERROR_MESSAGE_EMPTY_AMOUNT
            = "Invalid argument. Please do not leave compulsory arguments blank.";
    public static final String ERROR_MESSAGE_ARGUMENT_NULL = "Argument cannot be empty or blank.";
    public static final String ERROR_MESSAGE_AMOUNT_TOO_SMALL = "Invalid amount. Amount must be $0.01 or greater.";
    public static final String ERROR_MESSAGE_AMOUNT_TOO_LARGE = "Invalid amount. Amount must be $9999999.00 or less.";
    public static final String ERROR_MESSAGE_DATE_TOO_LATE = "Entered date cannot be after current date.";
    public static final String ERROR_MESSAGE_BLANK_DESCRIPTION = "Description cannot be blank.";
    public static final String ERROR_MESSAGE_OUT_OF_BOUNDS_INDEX = "There is no entry at that index :(";
    public static final String ERROR_MESSAGE_INVALID_ARGUMENT = "Invalid argument. Please provide a valid argument.";

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
}
