package programme;

import com.google.gson.Gson;

public class Exercise {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Exercise fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Exercise.class);
    }
}
