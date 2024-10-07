package programme;

import java.util.ArrayList;
import com.google.gson.Gson;

public class ProgrammeList {
    public Programme insertProgramme(String name, ArrayList<ArrayList<ArrayList<String>>> contents) {
        return new Programme();
    }

    public Programme startProgramme(int startIndex) {
        return new Programme();
    }

    public Programme getProgramme(int progIndex) {
        return new Programme();
    }

    // getDay receives progIndex, dayIndex and returns the specified Day object
    // if progIndex is null, assume they want to use the active programme instead
    public Day getDay(int progIndex, int dayIndex) {
        return new Day();
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ProgrammeList fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ProgrammeList.class);
    }
}
