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
import javax.json.JsonArrayBuilder;

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

    public void addCourse(Course course) {
        try {
            JsonObject jsonObject = loadFromJson();
            JsonArrayBuilder coursesArray = Json.createArrayBuilder(jsonObject.getJsonArray("courses"));
            JsonObject newCourse = Json.createObjectBuilder()
                    .add("NUS Course", course.getNusCourseCode())
                    .add("PU", course.getPartnerUniversity())
                    .add("PU Course", course.getPuCourseCode())
                    .build();
            coursesArray.add(newCourse);

            JsonObject updatedJson = Json.createObjectBuilder()
                    .add("courses", coursesArray)
                    .build();

            saveToJson(updatedJson);
        } catch (IOException e) {
            System.err.println("Failed to add course to myList.json");
        }
    }

}
