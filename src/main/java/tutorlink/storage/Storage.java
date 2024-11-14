package tutorlink.storage;

import tutorlink.exceptions.StorageOperationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    protected static final String READ_DELIMITER = " \\| ";
    protected static final String WRITE_DELIMITER = " | ";

    protected final Path path;
    protected ArrayList<String> discardedEntries;

    /**
     * Initializes a Storage object with the specified file path.
     * Creates the necessary directories and file if they do not exist.
     *
     * @param filePath The path to the storage file.
     * @throws StorageOperationException If an error occurs while creating the file or directories.
     */
    public Storage(String filePath) throws StorageOperationException {
        path = Paths.get(filePath);
        discardedEntries = new ArrayList<>();
        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new StorageOperationException("Error while creating file: " + path);
        }
    }

    public ArrayList<String> getDiscardedEntries() {
        return discardedEntries;
    }
}
