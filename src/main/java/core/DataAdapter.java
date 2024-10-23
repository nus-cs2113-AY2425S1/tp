package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import history.DateSerializer;
import history.History;
import programme.Day;
import storage.Storage;
import programme.ProgrammeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    Data Manager acts as an adapter layer between the Storage and BuffBuddy classes,
    translating between JSON and objects
*/
public class DataAdapter {

    private static final Logger logger = Logger.getLogger(DataAdapter.class.getName());
    private final Storage storage;

    public DataAdapter(String path) {
        this.storage = new Storage(path);
    }

    public ProgrammeList loadProgrammeList() {
        try {
            JsonObject programmeListJson = storage.loadProgrammeList();
            return programmeListFromJson(programmeListJson);
        } catch (Exception e) {
            return new ProgrammeList();
        }
    }

    public History loadHistory() {
        try {
            JsonObject historyJson = storage.loadHistory();
            return historyFromJson(historyJson);
        } catch (Exception e) {
            return new History();
        }
    }

    public void saveData(ProgrammeList pList, History history) {
        assert pList != null : "programmeList must not be null";
        assert history != null : "history must not be null";

        JsonObject jsonObject = createJSON(pList, history);
        logger.info("JsonObject containing programme list and history created.");

        try{
            storage.save(jsonObject);
        } catch (Exception ignored) {
            // For now, leave this as a quiet failure for simplicity
            // User will be notified of corrupted data when next loading app
        }
    }

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

    private JsonObject programmeListToJson(ProgrammeList programmeList) {
        Gson gson = new Gson();
        logger.log(Level.INFO, "Programme list converted to Json for saving.");
        return gson.toJsonTree(programmeList).getAsJsonObject();
    }

    private static ProgrammeList programmeListFromJson(JsonObject jsonObject) {
        Gson gson = new Gson();
        logger.log(Level.INFO, "Programme list converted from Json for loading.");
        return gson.fromJson(jsonObject, ProgrammeList.class);
    }

    private JsonObject historyToJson(History history) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom serializer for LocalDate
                .setPrettyPrinting()
                .create();

        JsonObject historyJson = new JsonObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LinkedHashMap<LocalDate, Day> historyMap = history.getHistory(); //To access the Hashmap

        for (LocalDate date : historyMap.keySet()) {
            Day day = historyMap.get(date);
            historyJson.add(date.format(formatter), gson.toJsonTree(day));
        }
        logger.log(Level.INFO, "History converted to Json for saving.");
        return historyJson;
    }

    private static History historyFromJson(JsonObject jsonObject) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom deserializer for LocalDate
                .create();
        History history = new History();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LinkedHashMap<LocalDate, Day> historyMap = history.getHistory(); //To access the Hashmap


        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey(), formatter);
            Day day = gson.fromJson(entry.getValue(), Day.class);
            historyMap.put(date, day);
        }
        logger.log(Level.INFO, "historyJson converted from Json for loading.");
        return history;
    }
}
