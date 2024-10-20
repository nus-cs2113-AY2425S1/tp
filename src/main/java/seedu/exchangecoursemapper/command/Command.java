package seedu.exchangecoursemapper.command;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public abstract class Command {
    public static final String FILE_PATH = "/database.json";
    public abstract void execute(String userInput);

    public JsonObject createJsonObject() {
        JsonReader jsonReader = Json.createReader(getClass().getResourceAsStream(FILE_PATH));
        return jsonReader.readObject();
    }
}
