//@@author Bev-low

package exceptions;

import java.io.IOException;

/**
 * Represents exceptions related to storage operations in the application.
 */
public class StorageException extends IOException {

    /**
     * Constructs a new {@code StorageExceptions} with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * Returns a StorageExceptions indicating that the storage file could not be saved.
     *
     * @return A {@code StorageExceptions} with a message indicating that the storage file
     *         could not be saved.
     */
    public static StorageException unableToSave() {
        return new StorageException("Could not save storage file");
    }

    /**
     * Returns a StorageExceptions}indicating that a directory could not be created.
     *
     * @return A {@code StorageExceptions} with a message indicating that the directory
     *         could not be created.
     */
    public static StorageException unableToCreateDirectory() {
        return new StorageException("Could not create directory");
    }

    /**
     * Returns a StorageExceptions indicating that a file could not be created.
     *
     * @return A {@code StorageExceptions} with a message indicating that the file
     *         could not be created.
     */
    public static StorageException unableToCreateFile() {
        return new StorageException("Could not create file");
    }

    public static StorageException corruptedFile(String type) {
        return new StorageException("Corrupted file, initialising new " + type);
    }
}
