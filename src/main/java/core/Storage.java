package core;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import programme.ProgrammeList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.Gson;

/**
 * Represents the storage system for saving and loading tasks.
 * The <code>Storage</code> class handles reading from and writing to the file specified by the user.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(DataWrapper dataWrapper) {
        try {
            Path directoryPath = Paths.get("data");
            Path filePath = directoryPath.resolve("data.json");

            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }

            if (!Files.exists(filePath)) {
                System.out.println("Oh first time here? Welcome to a life of Fitness, lets start!");
                return;
            }

            try (Reader reader = Files.newBufferedReader(filePath)){

                // Parse the JSON content into a JsonObject
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                // Extract the ProgrammeList JSON and deserialize it using the fromJson method
                JsonObject programmeListJson = jsonObject.getAsJsonObject("programmeList");
                JsonObject historyJson = jsonObject.getAsJsonObject("history");

                ProgrammeList loadedProgrammeList = ProgrammeList.fromJson(programmeListJson);
                History loadedHistory = History.fromJson(historyJson);

                dataWrapper.setProgrammeList(loadedProgrammeList);
                dataWrapper.setHistory(loadedHistory);

                System.out.println("Programmes and history have been loaded from: " + filePath);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            System.out.println("Oh first time here? Welcome to a life of Fitness, lets start!");
        }
    }

    public void save(ProgrammeList programmeList, History history) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            JsonObject jsonObject = new JsonObject();

            // Add the serialized ProgrammeList to the JsonObject
            jsonObject.add("programmeList", programmeList.toJson());
            jsonObject.add("history", history.toJson());

            // Use GSON to write the entire JsonObject to the file
            gson.toJson(jsonObject, writer);

            System.out.println("Saving done, Good Job!");
        } catch (IOException e) {
            System.out.println("An error has occurred when saving data: " + e.getMessage());
        }
    }
}
