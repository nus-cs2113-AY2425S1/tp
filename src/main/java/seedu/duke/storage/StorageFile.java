package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.hospital.Hospital;
import seedu.duke.storage.exception.StorageOperationException;
import seedu.duke.ui.Ui;

/**
 * Represents a storage file to manage Hospital.
 */
public class StorageFile implements Storage<Hospital> {
    private static final String DEFAULT_STORAGE_FILEPATH = "data/hospital_data.json";
    private static final Logger logger = Logger.getLogger("StorageFile");

    private Ui ui;
    private JsonUtil jsonUtil;

    static {
        logger.setLevel(Level.SEVERE);
    }

    /** The file path of the storage file. */
    private final String filePath;

    /**
     * Constructs a StorageFile with the default file path.
     */
    public StorageFile() {
        this(DEFAULT_STORAGE_FILEPATH);

        ui = new Ui();
        jsonUtil = new JsonUtil();
    }

    /**
     * Constructs a StorageFile with the specified file path.
     *
     * @param filePath The String file path of the storage file.
     */
    public StorageFile(String filePath) {
        this.filePath = filePath;
        assert filePath != null : "File path cannot be null";

        ui = new Ui();
        jsonUtil = new JsonUtil();

        checkFileFound(filePath);
    }

    /**
     * Returns the file path of the storage file.
     *
     * @return The String file path of the storage file.
     */
    @Override
    public String getFilePath() {
        assert filePath != null : "File path cannot be null";
        return filePath;
    }

    /**
     * Checks if the file exists. If the file does not exist, a new file will be created.
     *
     * @param filePath The String file path of the storage file.
     */
    @Override
    public void checkFileFound(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            logger.log(Level.INFO, "File not found, creating new file: {0}", filePath);
            try {
                // Create the file if it does not exist
                f.getParentFile().mkdirs();
                f.createNewFile();
                assert f.exists() : "File should exist after creation";

                jsonUtil.saveToFile(filePath); // Save an initial hospital to the file

                logger.log(Level.INFO, "File created successfully: {0}", filePath);
            } catch (IOException e) {
                ui.showToUserException("Error creating file: " + e.getMessage());
                logger.log(Level.WARNING, "Error creating file: {0}", e.getMessage());
            } catch (StorageOperationException e) {
                ui.showToUserException(e.getMessage());
            }
        }
    }

    /**
     * Saves the hospital data {@code Hospital} to the storage file.
     *
     * @param hospital The hospital data to save.
     */
    @Override
    public void save(Hospital hospital) {
        assert hospital != null : "Hospital cannot be null";
        logger.log(Level.INFO, "Going to save data to file: {0}", filePath);
        try {
            jsonUtil.saveToFile(hospital, filePath);
        } catch (StorageOperationException e) {
            ui.showToUserException(e.getMessage());

        }
    }

    /**
     * Loads the hospital data {@code Hospital} from the storage file.
     *
     * @return The hospital data loaded from the storage file.
     */
    @Override
    public Hospital load() {
        logger.log(Level.INFO, "Going to load data from file: {0}", filePath);
        try {
            return jsonUtil.loadFromFile(getFilePath());
        } catch (StorageOperationException e) {
            ui.showToUserException("File is Corrupted! " + e.getMessage());
            StorageBackup.createBackupFile(filePath); // Create a backup file
            return new Hospital(); // Return an empty hospital
        }
    }

    /**
     * Returns the string representation of the storage file.
     *
     * @return The string representation of the storage file.
     */
    @Override
    public String toString() {
        return "File Path: " + filePath;
    }
}
