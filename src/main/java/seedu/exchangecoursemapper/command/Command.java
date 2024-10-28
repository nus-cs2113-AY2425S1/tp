package seedu.exchangecoursemapper.command;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;

public abstract class Command {
    protected static final String FILE_PATH = "/database.json";

    /**
     * Creates and returns a JsonObject by reading from the JSON file at {@code FILE_PATH}.
     *
     * @return a JsonObject representing the data in the JSON file.
     * @throws IOException if the file at {@code FILE_PATH} cannot be found or read.
     */
    public JsonObject createJsonObject() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(FILE_PATH);
        if (inputStream == null) {
            throw new IOException();
        }
        JsonReader jsonReader = Json.createReader(inputStream);
        return jsonReader.readObject();
    }
}
