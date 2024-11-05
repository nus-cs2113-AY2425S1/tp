//@@author Bev-low

package storage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import history.History;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import programme.ProgrammeList;

public class StorageTest {
    private FileManager mockFileManager;
    private Storage storage;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        mockFileManager = Mockito.mock(FileManager.class);
        storage = new Storage("./src/test/resources/test_data.json");
    }

    @AfterEach
    public void tearDown() {
        storage = null;
        mockFileManager = null;
    }

    private JsonObject loadJsonFromFile(String fileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStreamReader reader = new InputStreamReader(classLoader.getResourceAsStream(fileName))) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    @Test
    public void testLoadProgrammeList_validJson() throws Exception {
        //JsonObject jsonObject = loadJsonFromFile("validJson_data.json");

        //when(mockFileManager.loadProgrammeList()).thenReturn(jsonObject.getAsJsonObject("programmeList"));

        //ProgrammeList programmeList = storage.loadProgrammeList();

        //assertNotNull(programmeList);
        //assertEquals(1, programmeList.getProgrammeList().size());
        //assertEquals("Starter", programmeList.getProgrammeList().get(0).getProgrammeName());
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
    public void testLoadHistory_validHistory() throws Exception {
        //JsonObject jsonObject = loadJsonFromFile("validJson_data.json");
        //when(mockFileManager.loadHistory()).thenReturn(jsonObject);
        //History history = storage.loadHistory();

        //assertNotNull(history);
        //assertEquals(1, history.getHistorySize());
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
    public void testLoadHistory_emptyHistory() throws Exception {
        JsonObject jsonObject = null;
        when(mockFileManager.loadHistory()).thenReturn(jsonObject);
        History history = storage.loadHistory();
        assertNotNull(history);
    }

    @Test
    public void testSave_validJsonObject() throws Exception {
    }

    @Test
    public void testSave_filePathDoesNotExist() throws Exception {
    }

}
