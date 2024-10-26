package seedu.exchangecoursemapper.storage;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import seedu.exchangecoursemapper.courses.Course;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class Storage {
    // In Storage class
    public static final String MYLIST_FILE_PATH = "/myList.json";

    // Constructor
    public Storage() {
        initializeMyList();
    }

    private void initializeMyList() {
        try (InputStream inputStream = getClass().getResourceAsStream(MYLIST_FILE_PATH)) {
            if (inputStream == null) {
                JsonObject emptyList = Json.createObjectBuilder().add("courses", Json.createArrayBuilder()).build();
                saveToJson(emptyList);
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize myList.json");
        }
    }

    private void saveToJson(JsonObject jsonObject) throws IOException {
        try (OutputStream os = new FileOutputStream(MYLIST_FILE_PATH)) {
            JsonWriter writer = Json.createWriter(os);
            writer.writeObject(jsonObject);
        }
    }

    private JsonObject loadFromJson() throws IOException {
        try (InputStream is = new FileInputStream(MYLIST_FILE_PATH)) {
            JsonReader reader = Json.createReader(is);
            return reader.readObject();
        }
    }
}
