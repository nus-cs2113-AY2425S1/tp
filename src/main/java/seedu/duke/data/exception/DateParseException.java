package seedu.duke.data.exception;

public class DateParseException extends Exception {

    /**
     * Constructs a new DateParseException with a specific message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DateParseException(String message) {
        super(message);
    }
}
