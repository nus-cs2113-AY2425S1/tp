package seedu.duke.storage.exception;

/**
 * Signals that an operation in storage has failed.
 * This exception is thrown when there is an error in saving or loading data.
 */
public class StorageOperationException extends Exception {
    public StorageOperationException(String message) {
        super(message);
    }
}
