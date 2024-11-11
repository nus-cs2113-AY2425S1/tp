//@@author Bev-low

package storage;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import exceptions.StorageException;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents the FileManager system for saving and loading tasks.
 * The <code>FileManager</code> class handles reading from and writing to the file specified by the user.
 */
public class FileManager {
    private final String path;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Constructs a FileManager with a specified file path.
     *
     * @param path the path to the file for storing data
     */
    public FileManager(String path) {
        this.path = path;
    }

    /**
     * Loads the JSON object containing the programme list from the data loaded by the {@link #load()} method.
     * <p>
     * This method retrieves the "programmeList" section from the JSON data. If the "programmeList" section
     * is not found, returns an empty JSON object.
     *
     * @return the JSON object containing the programme list if available, or an empty JSON object if not found
     */
    public JsonObject loadProgrammeList() {
        try {
            JsonObject jsonObject = load();
            if (jsonObject == null || !jsonObject.has("programmeList")) {
                logger.log(Level.INFO, "No programme list found.");
                System.out.println("First Time here, Welcome to BuffBuddy!");
                return new JsonObject();
            }
            logger.log(Level.INFO, "Programme list Loaded");
            return jsonObject.getAsJsonObject("programmeList");
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Failed to load programme list: " + e.getMessage());
            return new JsonObject();
        }
    }

    /**
     * Loads the JSON object containing the history from the data loaded by the {@link #load()} method.
     * <p>
     * This method retrieves the "history" section from the JSON data. If the "history" section
     * is not found, returns an empty JSON object.
     *
     * @return the JSON object containing the history if available, or an empty JSON object if not found
     */
    public JsonObject loadHistory() {
        try {
            JsonObject jsonObject = load();
            if (jsonObject == null || !jsonObject.has("history")) {
                logger.log(Level.INFO, "No history found.");
                return new JsonObject();
            }
            logger.log(Level.INFO, "History Loaded");
            return jsonObject.getAsJsonObject("history");
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Failed to load history: " + e.getMessage());
            return new JsonObject();
        }
    }

    /**
     * Loads data from the file specified by the path and converts it to a JSON object.
     * <p>
     * This method attempts to read the file at the specified path and parse its contents as a JSON object.
     * If the file is empty or contains no data, an empty JSON object is returned. Any I/O errors encountered
     * during file reading will result in a {@link StorageException}.
     *
     * @return a JSON object containing the data loaded from the file, or an empty JSON object if the file is empty
     * @throws StorageException if an I/O error occurs during loading
     */
    private JsonObject load() {
        logger.info("Attempting to load data from file: " + path);
        try (FileReader reader = new FileReader(path)){
            JsonElement element = JsonParser.parseReader(reader);
            if(element == null || element.isJsonNull()) {
                logger.info("No data found");
                System.out.println("First Time here, empty data initialised.");
                return new JsonObject();
            }
            logger.info("Data successfully loaded from file");
            return element.getAsJsonObject();
        } catch(Exception e){
            logger.log(Level.WARNING, "Failed to load data from file: " + path, e);
            return new JsonObject();
        }
    }

    /**
     * Saves the specified JSON object to the file.
     * <p>
     * This method writes the contents of the provided JSON object to the file specified by the path.
     * If the directory or file does not exist, it attempts to create them before writing. The data
     * is saved in a pretty-printed JSON format for readability.
     *
     * @param data the JSON object containing the data to be saved
     * @throws IOException if an I/O error occurs during saving
     */
    public void save(JsonObject data) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        createDirIfNotExist();
        createFileIfNotExist();

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
            logger.info("Data successfully saved to file.");
        } catch (StorageException e) {
            logger.log(Level.WARNING, "Failed to save data to file: " + path, e);
            throw StorageException.unableToSave();
        }
    }

    /**
     * Creates the directory for the file if it does not exist.
     *
     * @throws IOException if the directory creation fails
     */
    private void createDirIfNotExist() throws IOException {
        File dir = new File(path).getParentFile();

        if (dir == null || dir.exists()){
            logger.log(Level.INFO, "Directory exists");
            return;
        }

        boolean isSuccess = dir.mkdirs();

        if (!isSuccess){
            logger.log(Level.WARNING, "Failed to create directory.");
            throw StorageException.unableToCreateDirectory();
        }
        logger.log(Level.INFO, "Directory created");
    }

    /**
     * Creates the file if it does not exist.
     *
     * @throws IOException if the file creation fails
     */
    private void createFileIfNotExist() throws IOException {
        File file = new File(path);
        if (file.exists()) {
            logger.log(Level.INFO, "File exists");
            return;
        }

        boolean isSuccess = file.createNewFile();

        if (!isSuccess) {
            logger.log(Level.WARNING, "Failed to create file.");
            throw StorageException.unableToCreateFile();
        }
        logger.log(Level.INFO, "File created");
    }
}
