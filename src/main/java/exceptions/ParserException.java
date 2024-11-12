//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to parsing operations in the application.
 */
public class ParserException extends BuffBuddyException {

    /**
     * Constructs a new ParserExceptions with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public ParserException(String message) {
        super(message);
    }

    /**
     * Returns a ParserExceptions indicating that an invalid float value was provided.
     *
     * @param no The invalid float value.
     * @return A {@code ParserExceptions} with a message indicating that the provided float is invalid.
     */
    public static ParserException invalidFloat(float no) {
        return new ParserException("Number " + no + " is not a valid, it should be more than 0");
    }

    /**
     * Returns a ParserExceptions indicating that an invalid integer value was provided.
     *
     * @param no The invalid integer value.
     * @return A {@code ParserExceptions} with a message indicating that the provided integer is invalid.
     */
    public static ParserException invalidInt(int no) {
        return new ParserException("Number " + no + " is not a valid, it should be more than 0");
    }

    /**
     * Returns a ParserExceptions indicating that an invalid date format was provided.
     *
     * @param date The invalid date.
     * @return A {@code ParserExceptions} with a message indicating that the provided date is invalid.
     */
    public static ParserException invalidDate(String date) {
        return new ParserException("Invalid Date: " + date + ", Provide Date in format dd-MM-yyyy.");
    }

    /**
     * Returns a ParserExceptions indicating that an invalid date was previously recorded.
     *
     * @return A {@code ParserExceptions} with a message indicating that the provided date is invalid.
     */
    public static ParserException invalidDate() {
        return new ParserException("Invalid Date recorded");
    }

    /**
     * Returns a ParserExceptions indicating that a command is missing.
     *
     * @return A {@code ParserExceptions} with a message indicating that a command is missing.
     */
    public static ParserException missingCommand() {
        return new ParserException("Missing Command, please refer to User Guide");
    }

    /**
     * Returns a ParserExceptions indicating that arguments are missing after a command.
     *
     * @return A {@code ParserExceptions} with a message indicating that arguments are missing.
     */
    public static ParserException missingArguments() {
        return new ParserException("Missing Arguments, please refer to User Guide");
    }

    public static ParserException infinityFloat(float number) {
        return new ParserException("Number " + number + " is too large, please key in a smaller number.");
    }

    public static ParserException infinityInt(String trimmedIntString) {
        return new ParserException("Number too large, please key in a smaller number.");
    }

    public static ParserException invalidString(String string) {
        return new ParserException("String: " + string + " is invalid");
    }
}
