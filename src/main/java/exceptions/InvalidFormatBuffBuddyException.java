package exceptions;

/**
 * InvalidFormatBuffBuddyException is thrown when a value does not match the expected format.
 */
public class InvalidFormatBuffBuddyException extends BuffBuddyException {

    /**
     * Constructor that accepts the expected format for the invalid input.
     * @param expectedFormat The expected format for the input value.
     */
    public InvalidFormatBuffBuddyException(String expectedFormat) {
        super("Invalid format. Expected format: " + expectedFormat);
    }
}
