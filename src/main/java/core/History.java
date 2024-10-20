package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import programme.Day;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class History {

    private final HashMap<LocalDate, Day> history;  // HashMap to store Day with its respective date

    // Constructor
    public History() {
        history = new HashMap<>();
    }

    // Logs a completed day into the history with a given date
    public void logDay(Day day, LocalDate date) {
        history.put(date, day);  // Use HashMap to store or update the day with its date
    }

    // Converts the History object to a JSON string
    public JsonObject toJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())
                .setPrettyPrinting()
                .create();
        JsonObject historyJson = new JsonObject();
        for (LocalDate date : history.keySet()) {
            Day day = history.get(date);
            // Add each entry in the HashMap to the JsonObject, using the date as the key
            historyJson.add(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), gson.toJsonTree(day));
        }

        return historyJson;
    }

    // Creates a History object from a JSON string
    public static History fromJson(JsonObject jsonObject) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())
                .create();
        History history = new History();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Iterate through the JSON keys (dates) and deserialize them as LocalDate and Day objects
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey(), formatter);  // Convert key to LocalDate
            Day day = gson.fromJson(entry.getValue(), Day.class);  // Deserialize the Day object
            history.history.put(date, day);  // Add to the HashMap
        }

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

        // Iterate over the history HashMap
        for (LocalDate date : history.keySet()) {
            Day day = history.get(date);
            historyString.append(String.format("Day: %s%nCompleted On:%s%n%n",day,date.format(formatter)));
        }

        return historyString.toString();
    }
}

