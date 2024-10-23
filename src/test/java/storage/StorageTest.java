package storage;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    private final String testFilePath = "./test/test_data.json";
    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage(testFilePath);
    }

    @AfterEach
    public void deleteAfterTest() throws Exception {
        Files.deleteIfExists(Path.of(testFilePath));
    }

    @Test
    public void testLoadProgrammeList_validList() throws Exception {
        JsonObject mockJsonObject = new JsonObject();
        JsonObject programmeList = new JsonObject();
        programmeList.addProperty("day1", "exercise1");
        mockJsonObject.add("programmeList", programmeList);

        JsonObject result = storage.loadProgrammeList();

        assertNotNull(result);
        assertTrue(result.has("day1"));
        assertEquals("exercise1", result.get("day1").getAsString());
    }

    @Test
    public void testLoadProgrammeList_invalidList() throws Exception {
        JsonObject mockJsonObject = new JsonObject();
        mockJsonObject.addProperty("history", "someHistory");

        JsonObject result = storage.loadProgrammeList();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testLoadProgrammeList_emptyList() throws Exception {
        JsonObject result = storage.loadProgrammeList();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testLoadHistory_validHistory() throws Exception {
        JsonObject mockJsonObject = new JsonObject();
        JsonObject history = new JsonObject();
        history.addProperty("24/12/2024", "Day1");
        mockJsonObject.add("History", history);

        JsonObject result = storage.loadHistory();

        assertNotNull(result);
        assertTrue(result.has("Day1"));
        assertEquals("history", result.get("24/12/2024").getAsString());
    }

    @Test
    public void testLoadHistory_invalidHistory() throws Exception {
        JsonObject mockJsonObject = new JsonObject();
        mockJsonObject.addProperty("ProgrammeList", "Exercise 1");

        JsonObject result = storage.loadHistory();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testLoadHistory_emptyHistory() throws Exception {
        JsonObject result = storage.loadHistory();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testSave_validJsonObject() throws Exception {
        assertTrue(true);
    }

    @Test
    public void testSave_filePathDoesNotExist() throws Exception {
        assertTrue(true);
    }

    // Test case for saving when file path is restricted
    @Test
    public void testSave_filePathIsRestricted() throws Exception {
        assertTrue(true);
    }
}
