//@@author Bev-low

package storage;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import history.History;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import programme.ProgrammeList;

public class StorageTest {
    private FileManager mockFileManager;
    private Storage storage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        mockFileManager = Mockito.mock(FileManager.class);
        storage = new Storage("./src/test/resources/test_data.json");
        storage.setFileManager(mockFileManager);
    }

    @AfterEach
    public void tearDown() {
        storage = null;
        mockFileManager = null;
    }

    private JsonObject loadJsonFromFile() throws Exception {
        try (FileReader reader = new FileReader("./src/test/resources/validJson_data.json")) {
            JsonElement element = JsonParser.parseReader(reader);
            return element.getAsJsonObject();
        } catch(IOException e) {
            throw new RuntimeException("Failed to load data due to: " + e.getMessage());
        }
    }

    @Test
    public void testLoadProgrammeList_validJson() throws Exception {
        JsonObject jsonObject = loadJsonFromFile();
        JsonObject programmeListJsonObject = jsonObject.getAsJsonObject("programmeList");

        when(mockFileManager.loadProgrammeList()).thenReturn(programmeListJsonObject);

        ProgrammeList programmeList = storage.loadProgrammeList();

        assertNotNull(programmeList);
        assertEquals(1, programmeList.getProgrammeList().size());
        assertEquals("Starter", programmeList.getProgrammeList().get(0).getProgrammeName());
    }

    @Test
    public void testLoadProgrammeList_emptyJson() throws Exception {
        JsonObject jsonObject = new JsonObject();
        when(mockFileManager.loadProgrammeList()).thenReturn(jsonObject);
        ProgrammeList programmeList = storage.loadProgrammeList();

        assertNotNull(programmeList);
        assertEquals(0, programmeList.getProgrammeList().size());
    }

    @Test
    public void testLoadProgrammeList_null() throws Exception {
        JsonObject jsonObject = null;
        when(mockFileManager.loadProgrammeList()).thenReturn(jsonObject);
        ProgrammeList programmeList = storage.loadProgrammeList();
        assertNotNull(programmeList);
    }

    @Test
    public void testLoadHistory_validJson() throws Exception {
        JsonObject jsonObject = loadJsonFromFile();
        JsonObject historyJsonObject = jsonObject.getAsJsonObject("history");
        when(mockFileManager.loadHistory()).thenReturn(historyJsonObject);
        History history = storage.loadHistory();

        assertNotNull(history);
        assertEquals(1, history.getHistorySize());
    }

    @Test
    public void testLoadHistory_emptyJson() throws Exception {
        JsonObject jsonObject = new JsonObject();
        when(mockFileManager.loadProgrammeList()).thenReturn(jsonObject);
        History history = storage.loadHistory();

        assertNotNull(history);
        assertEquals(0, history.getHistorySize());
    }

    @Test
    public void testLoadHistory_null() throws Exception {
        JsonObject jsonObject = null;
        when(mockFileManager.loadHistory()).thenReturn(jsonObject);
        History history = storage.loadHistory();
        assertNotNull(history);
    }

    @Test
    public void testSave_validJsonObject() throws Exception {
        ProgrammeList programmeList = new ProgrammeList();
        History history = new History();
        storage.saveData(programmeList, history);
        verify(mockFileManager).save(any(JsonObject.class));
    }

    @Test
    public void testSaveData_nullProgrammeList() {
        History history = new History();
        assertThrows(AssertionError.class, () -> storage.saveData(null, history), "programmeList must not be null");
    }

    @Test
    public void testSaveData_nullHistory() {
        ProgrammeList programmeList = new ProgrammeList();
        assertThrows(AssertionError.class, () -> storage.saveData(programmeList, null), "history must not be null");
    }

    @Test
    public void testSave_filePathDoesNotExist() throws Exception {
        doThrow(new IOException("Simulated IO error")).when(mockFileManager).save(any(JsonObject.class));
        ProgrammeList programmeList = new ProgrammeList();
        History history = new History();

        storage.saveData(programmeList, history);
        verify(mockFileManager).save(any(JsonObject.class));
    }

}
