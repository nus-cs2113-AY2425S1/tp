package core;

import programme.Day;
import com.google.gson.Gson;

public class History {
    public void logDay(Day completed, String date) {
    }

    // Converts the History object to a JSON string
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Creates a History object from a JSON string
    public static History fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, History.class);
    }
}
