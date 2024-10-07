package programme;

import com.google.gson.Gson;

public class Programme {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Programme fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Programme.class);
    }
}
