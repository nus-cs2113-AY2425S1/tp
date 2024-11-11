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
    public static ParserException invalidFloat(String no) {
        return new ParserException("Float is not a valid, it should be more than 0");
    }

    /**
     * Returns a ParserExceptions indicating that an invalid integer value was provided.
     *
     * @param no The invalid integer value.
     * @return A {@code ParserExceptions} with a message indicating that the provided integer is invalid.
     */
    public static ParserException invalidInt(String no) {
        return new ParserException("Index is not a valid, it should be more than 0");
    }

    /**
     * Returns a ParserExceptions indicating that an invalid date format was provided.
     *
     * @param date The invalid date.
     * @return A {@code ParserExceptions} with a message indicating that the provided date is invalid.
     */
    public static ParserException invalidDate(String date) {
        return new ParserException("Invalid Date: " + date + ", Date in format dd-MM-yyyy.");
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

    /**
     * Returns a ParserExceptions indicating that the specified index is out of bounds.
     *
     * @param indexString The out-of-bounds index.
     * @return A {@code ParserExceptions} with a message indicating that the provided index is
     *         out of bounds.
     */
    public static ParserException indexOutOfBounds(String indexString) {
        return new ParserException("Index is not a valid, it should be more than 0");
    }

    public static ParserException infinityFloat(String trimmedFloatString) {
        return new ParserException("Float is too large, please key in a smaller number.");
    }

    public static ParserException infinityInt(String trimmedIntString) {
        return new ParserException("Integer is too large, please key in a smaller number.");
    }
}
