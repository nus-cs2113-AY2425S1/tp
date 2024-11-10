//@@author Bev-low

package exceptions;

/**
 * Represents exceptions related to parsing operations in the application.
 */
public class ParserExceptions extends BuffBuddyExceptions {

    /**
     * Constructs a new ParserExceptions with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public ParserExceptions(String message) {
        super(message);
    }

    /**
     * Returns a ParserExceptions indicating that an invalid float value was provided.
     *
     * @param no The invalid float value.
     * @return A {@code ParserExceptions} with a message indicating that the provided float is invalid.
     */
    public static ParserExceptions invalidFloat(String no) {
        return new ParserExceptions("Float is not a valid, it should be more than 0");
    }

    /**
     * Returns a ParserExceptions indicating that an invalid integer value was provided.
     *
     * @param no The invalid integer value.
     * @return A {@code ParserExceptions} with a message indicating that the provided integer is invalid.
     */
    public static ParserExceptions invalidInt(String no) {
        return new ParserExceptions("Index is not a valid, it should be more than 0");
    }

    /**
     * Returns a ParserExceptions indicating that an invalid date format was provided.
     *
     * @param date The invalid date.
     * @return A {@code ParserExceptions} with a message indicating that the provided date is invalid.
     */
    public static ParserExceptions invalidDate(String date) {
        return new ParserExceptions("Invalid Date: " + date + ", Date in format dd-MM-yyyy.");
    }

    /**
     * Returns a ParserExceptions indicating that a command is missing.
     *
     * @return A {@code ParserExceptions} with a message indicating that a command is missing.
     */
    public static ParserExceptions missingCommand() {
        return new ParserExceptions("Missing Command, please refer to User Guide");
    }

    /**
     * Returns a ParserExceptions indicating that arguments are missing after a command.
     *
     * @return A {@code ParserExceptions} with a message indicating that arguments are missing.
     */
    public static ParserExceptions missingArguments() {
        return new ParserExceptions("Missing Arguments, please refer to User Guide");
    }

    /**
     * Returns a ParserExceptions indicating that the specified index is out of bounds.
     *
     * @param indexString The out-of-bounds index.
     * @return A {@code ParserExceptions} with a message indicating that the provided index is
     *         out of bounds.
     */
    public static ParserExceptions indexOutOfBounds(String indexString) {
        return new ParserExceptions("Index is not a valid, it should be more than 0");
    }
}
