package core;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import programme.ProgrammeList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

/**
 * Represents the storage system for saving and loading tasks.
 * The <code>Storage</code> class handles reading from and writing to the file specified by the user.
 */
public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public JsonObject loadProgrammeList() {
        return load().getAsJsonObject("programmeList");
    }

    public JsonObject loadHistory() {
        return load().getAsJsonObject("history");
    }

    private JsonObject load() {
        try (FileReader reader = new FileReader(path)){
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch(IOException e){
            throw new RuntimeException("Failed to load data due to: " + e.getMessage());
        }
    }

    public void save(ProgrammeList programmeList, History history) throws IOException {
        createDirIfNotExists();
        createFileIfNotExists();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = createJSON(programmeList, history);

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            throw new IOException("Failed to save data due to: " + e.getMessage());
        }
    }

    private JsonObject createJSON(ProgrammeList programmeList, History history) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("programmeList", programmeList.toJson());
        jsonObject.add("history", history.toJson());
        return jsonObject;
    }

    private void createDirIfNotExists() throws IOException {
        File dir = new File(path).getParentFile();

        if (dir == null || dir.exists()){
            return;
        }

        boolean isSuccess = dir.mkdirs();

        if (!isSuccess){
            throw new IOException("Failed to create directory: " + dir.getAbsolutePath());
        }
    }

    private void createFileIfNotExists() throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return;
        }

        boolean isSuccess = file.createNewFile();

        if (!isSuccess) {
            throw new IOException("Failed to create file: " + file.getAbsolutePath());
        }
    }
}
