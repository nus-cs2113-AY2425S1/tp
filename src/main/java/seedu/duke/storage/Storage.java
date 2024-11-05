package seedu.duke.storage;

import seedu.duke.storage.exception.StorageOperationException;

/**
 * API of the Storage component.
 */
public interface Storage<T> {

    String getFilePath();

    void checkFileFound(String filePath) throws StorageOperationException;

    void save(T objectToSave) throws StorageOperationException;

    T load() throws StorageOperationException;

    @Override
    public String toString();
}
