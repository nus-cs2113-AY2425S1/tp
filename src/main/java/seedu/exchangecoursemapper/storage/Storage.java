package seedu.exchangecoursemapper.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import seedu.exchangecoursemapper.courses.Course;

import javax.json.Json;
import javax.json.JsonObject;

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

}
