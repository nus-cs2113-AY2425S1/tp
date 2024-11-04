package exceptions;

/**
 * Custom exception for handling index out-of-bounds errors in BuffBuddy.
 */
public class IndexOutOfBoundsBuffBuddyException extends BuffBuddyException {
    public IndexOutOfBoundsBuffBuddyException(int index, String entityType) {
        super("Index " + index + " is out of bounds for " + entityType + ".");
    }

    // New constructor to allow a custom message only
    public IndexOutOfBoundsBuffBuddyException(String message) {
        super(message);
    }
}

