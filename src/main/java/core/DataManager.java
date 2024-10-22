package core;

import com.google.gson.JsonObject;
import history.History;
import storage.Storage;
import programme.ProgrammeList;

/* Data Manager acts as an adapter layer between the Storage and BuffBuddy classes, translating between JSON and objects */
public class DataManager {

    private final Storage storage;

    public DataManager(String path) {
        this.storage = new Storage(path);
    }

    public ProgrammeList loadProgrammeList() {
        try {
            JsonObject programmeListJson = storage.loadProgrammeList();
            return ProgrammeList.fromJson(programmeListJson);
        } catch (Exception e) {
            return new ProgrammeList();
        }
    }

    public History loadHistory() {
        try {
            JsonObject historyJson = storage.loadHistory();
            return History.fromJson(historyJson);
        } catch (Exception e) {
            return new History();
        }
    }

    public void saveData(ProgrammeList pList, History history) {
        try{
            storage.save(pList, history);
        } catch (Exception ignored) {
            // For now, leave this as a quiet failure for simplicity
            // User will be notified of corrupted data when next loading app
        }
    }
}
