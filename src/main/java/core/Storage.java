package core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import programme.ProgrammeList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents the storage system for saving and loading tasks.
 * The <code>Storage</code> class handles reading from and writing to the file specified by the user.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(ProgrammeList programmeList, History history) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the JSON content into a JsonObject
            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();

            // Extract the ProgrammeList JSON and deserialize it using the fromJson method
            JsonObject programmeListJson = jsonObject.getAsJsonObject("programmeList");
            ProgrammeList loadedProgrammeList = ProgrammeList.fromJson(programmeListJson.toString());
            //programmeList.insertProgramme(loadedProgrammeList.getProgrammes());

            // Extract the History JSON and deserialize it using the fromJson method
            JsonObject historyJson = jsonObject.getAsJsonObject("history");
            History loadedHistory = History.fromJson(historyJson.toString());
            //history.setEntries(loadedHistory.getEntries());

            System.out.println("Programmes and history have been loaded from: " + filePath);
        } catch (Exception e) {
            System.out.println("Oh first time here? Welcome to a life of Fitness, lets start!");
        }
    }


    //SAVE
    public void save(ProgrammeList programmeList, History history) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Create a string that starts with an opening brace for the combined JSON object
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");

            // Add the serialized ProgrammeList using its toJson method
            jsonBuilder.append("\"programmeList\": ");
            jsonBuilder.append(programmeList.toJson());
            jsonBuilder.append(",\n"); // Add a comma between programmeList and history

            // Add the serialized History using its toJson method
            jsonBuilder.append("\"history\": ");
            jsonBuilder.append(history.toJson());
            jsonBuilder.append("\n"); // No trailing comma for the last entry

            // Close the JSON object
            jsonBuilder.append("}");

            // Write the entire JSON string to the file
            writer.write(jsonBuilder.toString());
            writer.write("\n");

            System.out.println("Saving done, Good Job!");
        } catch (IOException e) {
            System.out.println("An error has occurred when saving data: " + e.getMessage());
        }
    }
}
