//@@author Bev-low

package exceptions;

import java.io.IOException;

/**
 * Represents exceptions related to storage operations in the application.
 */
public class StorageExceptions extends IOException {

    /**
     * Constructs a new {@code StorageExceptions} with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public StorageExceptions(String message) {
        super(message);
    }

    /**
     * Returns a StorageExceptions indicating that the storage file could not be saved.
     *
     * @return A {@code StorageExceptions} with a message indicating that the storage file
     *         could not be saved.
     */
    public static StorageExceptions unableToSave() {
        return new StorageExceptions("Could not save storage file");
    }

    /**
     * Returns a StorageExceptions}indicating that a directory could not be created.
     *
     * @return A {@code StorageExceptions} with a message indicating that the directory
     *         could not be created.
     */
    public static StorageExceptions unableToCreateDirectory() {
        return new StorageExceptions("Could not create directory");
    }

    /**
     * Returns a StorageExceptions indicating that a file could not be created.
     *
     * @return A {@code StorageExceptions} with a message indicating that the file
     *         could not be created.
     */
    public static StorageExceptions unableToCreateFile() {
        return new StorageExceptions("Could not create file");
    }
}
