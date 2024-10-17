package core;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.ProgrammeList;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StorageTest {
    private final String TEST_FILE_PATH = "./test/test_data.json";
    private Storage storage;

    @BeforeEach
    public void setup() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void deleteAfterTest() throws Exception {
        Files.deleteIfExists(Path.of(TEST_FILE_PATH));
    }

    @Test
    public void testLoad_emptyFile_returnsEmptyList() throws Exception {
        Files.createFile(Path.of(TEST_FILE_PATH));

        JsonObject loadedProgrammeList = storage.loadProgrammeList();
        assertNotNull(loadedProgrammeList);
        assertEquals(0, loadedProgrammeList.size(), "Loaded Programme List should be empty");


        JsonObject loadedHistory = storage.loadHistory();
        assertNotNull(loadedHistory);
        assertEquals(0, loadedHistory.size(), "Loaded History should be empty");
    }

    @Test
    public void testLoad_nonemptyFile_returnsLists() throws Exception {
        String jsonData = "{\"programmeList\":{\"currentActiveProgramme\":0,\"programmeList\":[{\"programmeName\":\"Starter\",\"dayList\":[]}]},\"history\":{\"history\":{}}}";

        Files.write(Path.of(TEST_FILE_PATH), jsonData.getBytes());

        JsonObject loadedProgrammeList = storage.loadProgrammeList();
        assertNotNull(loadedProgrammeList);
        assertTrue(loadedProgrammeList.has("programmeList"), "Loaded Programme List should have programmeList");

        JsonObject loadedHistory = storage.loadHistory();
        assertNotNull(loadedHistory);
        assertTrue(loadedHistory.has("history"), "Loaded History should have history entries");
    }

    @Test
    public void testLoad_invalidFile_throwsException() {
        assertThrows(RuntimeException.class, () -> storage.loadProgrammeList(), "Expected exception for invalid file");
    }

    @Test
    public void testSave_createsFileAndDirectory() throws Exception {
        JsonObject programmeList = new JsonObject();
        JsonObject history = new JsonObject();
        programmeList.add("programmes", new JsonObject());
        history.add("history", new JsonObject());

        ProgrammeList mockProgrammeList = mock(ProgrammeList.class);
        when(mockProgrammeList.toJson()).thenReturn(programmeList);

        History mockHistory = mock(History.class);
        when(mockHistory.toJson()).thenReturn(history);

        storage.save(mockProgrammeList, mockHistory);

        File savedFile = new File(TEST_FILE_PATH);
        assertTrue(savedFile.exists(), "File should be created after saving");

        String savedContent = Files.readString(Path.of(TEST_FILE_PATH));
        assertTrue(savedContent.contains("programmeList"), "File content should include programme list");
        assertTrue(savedContent.contains("history"), "File content should include history");
    }

}
