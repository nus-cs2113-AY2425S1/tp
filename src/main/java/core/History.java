package core;

import com.google.gson.JsonObject;
import programme.Day;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class History {
    private HashMap<Date, Day> history;

    public void logDay(Day completed, LocalDateTime date) {

    }

    @Override
    public String toString() {
        return "History";
    }

    // Converts the History object to a JSON string
    public JsonObject toJson() {
        Gson gson = new Gson();
        return gson.toJsonTree(this).getAsJsonObject();
    }

    // Creates a History object from a JSON string
    public static History fromJson(JsonObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, History.class);
    }
}
