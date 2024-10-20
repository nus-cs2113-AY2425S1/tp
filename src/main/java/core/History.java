package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import programme.Day;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class History {

    // LinkedHashMap to store Day with its respective date in the order of insertion
    private final LinkedHashMap<LocalDate, Day> history;  // Use LocalDate and LinkedHashMap to preserve insertion order
    private final static Logger logger = Logger.getLogger(History.class.getName());

    // Constructor
    public History() {
        history = new LinkedHashMap<>();
    }

    // Logs a completed day into the history with a given date
    public void logDay(Day day, LocalDate date) {
        history.put(date, day);  // This will overwrite if a day with the same date exists
    }

    // Get a specific Day object by date (used for test comparisons)
    public Day getDayByDate(LocalDate date) {
        return history.get(date);
    }

    // Converts the History object to a JSON string
    public JsonObject toJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom serializer for LocalDate
                .setPrettyPrinting()
                .create();

        JsonObject historyJson = new JsonObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (LocalDate date : history.keySet()) {
            Day day = history.get(date);
            // Add each entry in the LinkedHashMap to the JsonObject, using the date as the key
            historyJson.add(date.format(formatter), gson.toJsonTree(day));
        }
        logger.log(Level.INFO, "History converted to Json for saving.");
        return historyJson;
    }

    // Creates a History object from a JSON string
    public static History fromJson(JsonObject jsonObject) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom deserializer for LocalDate
                .create();
        History history = new History();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Iterate through the JSON keys (dates) and deserialize them as LocalDate and Day objects
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey(), formatter);  // Convert key to LocalDate
            Day day = gson.fromJson(entry.getValue(), Day.class);  // Deserialize the Day object
            history.history.put(date, day);  // Add to the LinkedHashMap
        }
        logger.log(Level.INFO, "historyJson converted from Json for loading.");
        return history;
    }

    // Standard toString method for History class that represents the history
    @Override
    public String toString() {
        StringBuilder historyString = new StringBuilder();

        if (history.isEmpty()) {
            return "No workout history available.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Iterate over the history LinkedHashMap in insertion order
        for (LocalDate date : history.keySet()) {
            Day day = history.get(date);

            // Use the Day class's toString directly
            historyString.append(day.toString());

            // Append the formatted date at the end
            historyString.append(String.format("Completed On: %s%n%n", date.format(formatter)));
        }

        return historyString.toString();
    }
}

