package core;

import com.google.gson.JsonObject;
import programme.Day;
import com.google.gson.Gson;

public class History {
    public void logDay(Day completed, String date) {
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
