package seedu.duke.command;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class ListSchoolCommand extends Command {

    @Override
    public void execute(String userInput) {
        String filePath = "./data/database.json"; // File path to the JSON file

        try (JsonReader jsonReader = Json.createReader(new FileReader(filePath))) {
            JsonObject jsonObject = jsonReader.readObject();

            Set<String> universityNames = jsonObject.keySet();
            for (String universityName : universityNames) {
                System.out.println(universityName);
            }
        } catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
