package seedu.exchangecoursemapper.command;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;

public abstract class Command {
    protected static final String FILE_PATH = "/path/to/file.json";  // Replace with actual path

    public JsonObject createJsonObject() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(FILE_PATH);
        if (inputStream == null) {
            throw new IOException();
        }
        JsonReader jsonReader = Json.createReader(inputStream);
        return jsonReader.readObject();
    }
}