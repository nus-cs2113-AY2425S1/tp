package programme;

import com.google.gson.Gson;

public class Day {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Day fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Day.class);
    }
}
