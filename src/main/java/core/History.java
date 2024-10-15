package core;

import com.google.gson.JsonObject;
import programme.Day;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class History {
    private final HashMap<LocalDateTime, Day> history;

    public History() {
        history = new HashMap<>();
    }
    
    public void logDay(Day completed, LocalDateTime date) {
        history.put(date, completed);
    }

    @Override
    public String toString() {
        StringBuilder historyString = new StringBuilder();

        if (history.isEmpty()) {
            return "No workout history available.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Iterate over the history HashMap
        for (LocalDateTime date : history.keySet()) {
            Day day = history.get(date);
            historyString.append(String.format("Day: %s%nCompleted On:%s%n%n",day,date.format(formatter)));
        }

        return historyString.toString();
    }

    // Converts the History object to a JSON string
    public JsonObject toJson() {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new DateSerializer())
            .create();

        return gson.toJsonTree(this).getAsJsonObject();
    }

    // Creates a History object from a JSON string
    public static History fromJson(JsonObject jsonObject) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new DateSerializer())
            .create();
        return gson.fromJson(jsonObject, History.class);
    }
}
