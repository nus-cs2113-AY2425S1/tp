//@@author Bev-low

package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import history.DailyRecord;
import history.History;
import programme.ProgrammeList;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    Storage acts as an adapter layer between the FileManager and BuffBuddy classes,
    translating between JSON and programmeList or History objects
*/
public class Storage {

    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private FileManager fileManager;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param path the path to the file used for storing data
     */
    public Storage(String path) {
        this.fileManager = new FileManager(path);
    }

    /**
     * Loads the programme list from the JSON object obtained via the FileManager.
     * <p>
     * This method retrieves the JSON data containing the programme list from the FileManager. If no
     * programme list data is found, it initializes an empty ProgrammeList.
     *
     * @return the ProgrammeList object containing programme data, or an empty ProgrammeList if not found
     */
    public ProgrammeList loadProgrammeList() throws IOException {
        try {
            JsonObject programmeListJson = fileManager.loadProgrammeList();
            if (programmeListJson == null) {
                return new ProgrammeList();
            }
            logger.info("Loading programmeList");
            return programmeListFromJson(programmeListJson);
        } catch (Exception e ) {
            logger.info("Programme list corrupted, empty list initialised");
            return new ProgrammeList();
        }
    }

    /**
     * Converts json Object containing history to  from the JSON object obtained via the FileManager.
     * <p>
     * This method retrieves the JSON data containing the history from the FileManager. If no
     * history data is found, it initializes an empty History.
     *
     * @return the history object containing programme data, or an empty history if not found
     */
    public History loadHistory() throws IOException{
        try {
            JsonObject historyJson = fileManager.loadHistory();
            if (historyJson == null) {
                return new History();
            }
            logger.info("Loading history");
            return historyFromJson(historyJson);
        } catch (Exception e) {
            logger.info("history corrupted, empty history initialised");
            return new History();
        }
    }

    /**
     * Saves the programme list and history data to the file.
     *
     * @param programmeList the ProgrammeList object to be saved
     * @param history       the History object to be saved
     */
    public void saveData(ProgrammeList programmeList, History history) {
        assert programmeList != null : "programmeList must not be null";
        assert history != null : "history must not be null";

        JsonObject jsonObject = createJSON(programmeList, history);
        logger.info("JsonObject containing programme list and history created.");

        try{
            fileManager.save(jsonObject);
        } catch (Exception e) {
            logger.info("Failed to save data");
        }
    }

    /**
     * Creates a JSON object containing the programme list and history data.
     *
     * @param programmeList the ProgrammeList object to be added to JSON
     * @param history       the History object to be added to JSON
     * @return a JSON object containing the programme list and history data
     */
    private JsonObject createJSON(ProgrammeList programmeList, History history) {
        JsonObject jsonObject = new JsonObject();

        assert programmeList != null : "programmeList must not be null";
        assert history != null : "history must not be null";

        jsonObject.add("programmeList", programmeListToJson(programmeList));
        logger.info("Programme list converted to JsonObject.");
        jsonObject.add("history", historyToJson(history));
        logger.info("History converted to JsonObject.");
        return jsonObject;
    }

    /**
     * Converts a ProgrammeList object to a JSON object.
     *
     * @param programmeList the ProgrammeList object to convert
     * @return a JSON object representing the programme list
     */
    private JsonObject programmeListToJson(ProgrammeList programmeList) {
        Gson gson = new Gson();
        logger.log(Level.INFO, "Programme list converted to Json for saving.");
        return gson.toJsonTree(programmeList).getAsJsonObject();
    }

    /**
     * Converts a JSON object to a ProgrammeList object.
     *
     * @param jsonObject the JSON object representing the programme list
     * @return the ProgrammeList object created from the JSON data
     */
    private ProgrammeList programmeListFromJson(JsonObject jsonObject) {
        Gson gson = new Gson();
        logger.log(Level.INFO, "Programme list converted from Json for loading.");
        return gson.fromJson(jsonObject, ProgrammeList.class);
    }

    /**
     * Converts a History object to a JSON object.
     *
     * @param history the History object to convert
     * @return a JSON object representing the history
     */
    private JsonObject historyToJson(History history) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom serializer for LocalDate
                .setPrettyPrinting()
                .create();

        JsonObject historyJson = new JsonObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LinkedHashMap<LocalDate, DailyRecord> historyMap = history.getHistory(); //To access the Hashmap

        for (LocalDate date : historyMap.keySet()) {
            DailyRecord dailyRecord = historyMap.get(date);
            historyJson.add(date.format(formatter), gson.toJsonTree(dailyRecord));
        }
        logger.log(Level.INFO, "History converted to Json for saving.");
        return historyJson;
    }

    /**
     * Converts a JSON object to a History object.
     *
     * @param jsonObject the JSON object representing the history
     * @return the History object created from the JSON data
     */
    private History historyFromJson(JsonObject jsonObject) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom deserializer for LocalDate
                .create();
        History history = new History();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LinkedHashMap<LocalDate, DailyRecord> historyMap = history.getHistory(); //To access the Hashmap


        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey(), formatter);
            DailyRecord dailyRecord = gson.fromJson(entry.getValue(), DailyRecord.class);
            historyMap.put(date, dailyRecord);
        }
        logger.log(Level.INFO, "historyJson converted from Json for loading.");
        return history;
    }

    /**
     * Sets the FileManager instance for testing purposes.
     *
     * @param mockFileManager the mocked FileManager to be used for testing.
     */
    public void setFileManager(FileManager mockFileManager) {
        this.fileManager = mockFileManager;
    }
}
