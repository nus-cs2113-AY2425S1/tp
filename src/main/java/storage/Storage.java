//@@author Bev-low

package storage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ParserException;
import exceptions.StorageException;
import history.DailyRecord;
import history.History;
import programme.ProgrammeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static common.Utils.validate;

/*
    Storage acts as an adapter layer between the FileManager and BuffBuddy classes,
    translating between JSON and programmeList or History objects
*/
public class Storage {

    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private FileManager fileManager;
    private String message;
    private boolean isProgrammeListEmpty = false;
    private boolean isProgrammeListCorrupted = false;
    private boolean isHistoryEmpty = false;
    private boolean isHistoryCorrupted = false;
    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param path the path to the file used for storing data
     */
    public Storage(String path) {
        this.fileManager = new FileManager(path);
        message = null;
    }

    /**
     * Loads the programme list from the JSON object obtained via the FileManager.
     * <p>
     * This method retrieves the JSON data containing the programme list from the FileManager. If no
     * programme list data is found, it initializes an empty ProgrammeList.
     *
     * @return the ProgrammeList object containing programme data, or an empty ProgrammeList if not found
     */
    public ProgrammeList loadProgrammeList() {
        try {
            JsonObject programmeListJson = fileManager.loadProgrammeList();

            if(programmeListJson == null || programmeListJson.size() == 0) {
                isProgrammeListEmpty = true;
                return new ProgrammeList();
            }

            try {
                validateProgrammeList(programmeListJson);
            } catch (ParserException e) {
                throw StorageException.corruptedFile("Programme List.");
            }

            logger.info("Loading programmeList");
            message = "Welcome back!";
            return programmeListFromJson(programmeListJson);
        } catch (StorageException e ) {
            logger.info("Programme list corrupted, empty list initialised");
            isProgrammeListCorrupted = true;
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
    public History loadHistory() {
        try {
            JsonObject historyJson = fileManager.loadHistory();
            if (historyJson == null || historyJson.size() == 0) {
                isHistoryEmpty = true;
                return new History();
            }

            try {
                validateHistory(historyJson);
            } catch (ParserException e) {
                throw StorageException.corruptedFile("History.");
            }

            logger.info("Loading history");
            return historyFromJson(historyJson);
        } catch (StorageException e) {
            logger.info("history corrupted, empty history initialised");
            isHistoryCorrupted = true;
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
     * To set message about status of data
     *
     * @return String value that to tell user what is the state of the file.
     */
    public String getMessage() {
        if(isProgrammeListEmpty && isHistoryEmpty) {
            message = "First time here, welcome to BuffBuddy!";
        } else if (isHistoryCorrupted && isProgrammeListCorrupted) {
            message = "data is corrupted, initialise new ProgrammeList and History";
        } else if (isHistoryCorrupted) {
            message = "History is corrupted, initialise new History, loaded ProgrammeList";
        } else if (isProgrammeListCorrupted) {
            message = "ProgrammeList is corrupted, initialise new ProgrammeList, loaded History";
        } else {
            message = "Welcome back!";
        }
        return message;
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

    /**
     * Validates the given JSON object representing a programme list.
     *
     * @param programmeList the JSON object containing the programme data to be validated
     * @throws ParserException if any validation fails for the integer, float, or string fields
     */
    private void validateProgrammeList(JsonObject programmeList) {
        JsonArray programmeArray = programmeList.getAsJsonArray("programmeList");
        validateProgramme(programmeArray);
    }

    /**
     * Validates the given JSON object representing a history.
     *
     * @param history the JSON object containing the history data to be validated
     * @throws ParserException if any validation fails for the date, day, exercise, meal, or water fields
     */
    private void validateHistory(JsonObject history) {
        for (Map.Entry<String, JsonElement> entry : history.entrySet()) {
            String date = entry.getKey();
            JsonObject record = entry.getValue().getAsJsonObject();

            validateDate(date);

            JsonObject day = record.getAsJsonObject("day");
            String dayName = day.get("name").getAsString();
            validate(dayName);

            JsonArray exercises = day.getAsJsonArray("exercises");
            validateExercise(exercises);

            JsonObject mealList = record.getAsJsonObject("mealList");
            JsonArray meals = mealList.getAsJsonArray("meals");
            validateMeal(meals);

            JsonObject water = record.getAsJsonObject("water");
            JsonArray waterList = water.getAsJsonArray("waterList");
            validateWater(waterList);
        }
    }

    /**
     * Validates a date string.
     *
     * @param dateString the date string to be validated in the format "dd-MM-yyyy"
     * @throws ParserException if the date string is invalid or cannot be parsed
     */
    private void validateDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        validate(date);
    }

    /**
     * Validates an array of programmes.
     *
     * @param programmeList the JSON array of programme objects to be validated
     * @throws ParserException if any validation fails for the programme or its nested day data
     */
    private void validateProgramme(JsonArray programmeList) {
        for (JsonElement programmeElement : programmeList) {
            JsonObject programme = programmeElement.getAsJsonObject();
            String programmeName = programme.get("programmeName").getAsString();
            validate(programmeName);

            JsonArray dayList = programme.getAsJsonArray("dayList");
            validateDay(dayList);
        }

    }

    /**
     * Validates an array of days.
     *
     * @param dayList the JSON array of day objects to be validated
     * @throws ParserException if any validation fails for the day or its nested exercise data
     */
    private void validateDay(JsonArray dayList) {
        for (JsonElement dayElement : dayList) {
            JsonObject day = dayElement.getAsJsonObject();
            String dayName = day.get("name").getAsString();
            validate(dayName);

            JsonArray exercises = day.getAsJsonArray("exercises");
            validateExercise(exercises);
        }
    }


    /**
     * Validates an array of exercises.
     *
     * @param exercises the JSON array of exercise objects to be validated
     * @throws ParserException if any validation fails for the sets, reps, weight, calories, or name of exercises
     */
    private void validateExercise(JsonArray exercises) {
        for (JsonElement exerciseElement : exercises) {
            JsonObject exercise = exerciseElement.getAsJsonObject();
            int sets = exercise.get("sets").getAsInt();
            int reps = exercise.get("reps").getAsInt();
            float weight = exercise.get("weight").getAsInt();
            int calories = exercise.get("calories").getAsInt();
            String exerciseName = exercise.get("name").getAsString();

            validate(sets);
            validate(reps);
            validate(weight);
            validate(calories);
            validate(exerciseName);
        }
    }

    /**
     * Validates an array of meals.
     *
     * @param meals the JSON array of meal strings to be validated
     * @throws ParserException if any validation fails for the meal name or calories
     */
    private void validateMeal(JsonArray meals) {
        for (JsonElement mealElement : meals) {
            JsonObject mealObject = mealElement.getAsJsonObject();
            String mealName = mealObject.get("name").getAsString();
            int calories = mealObject.get("calories").getAsInt();

            validate(mealName);
            validate(calories);
        }
    }

    /**
     * Validates an array of water amounts.
     *
     * @param waterList the JSON array of water amounts to be validated
     * @throws ParserException if any validation fails for the water amount
     */
    private void validateWater(JsonArray waterList) {
        for (JsonElement waterElement : waterList) {
            float waterAmount = waterElement.getAsFloat();
            validate(waterAmount);
        }
    }
}
