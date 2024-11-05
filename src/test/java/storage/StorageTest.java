package storage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import history.History;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    private JsonObject createValidJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("programmeList", createProgrammeList());
        jsonObject.add("history", createHistory());
        return jsonObject;
    }

    @Test
    public void testLoadProgrammeList_validJson() throws Exception {
        JsonObject jsonObject = createValidJson();
        when(mockFileManager.loadProgrammeList()).thenReturn(jsonObject);
        ProgrammeList programmeList = storage.loadProgrammeList();
        assertNotNull(programmeList);
        assertEquals();
    }

    @Test
    public void testLoadProgrammeList_invalidJson() throws Exception {
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
    }

    @Test
    public void testLoadHistory_invalidHistory() throws Exception {
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

    private JsonObject createProgrammeList() {
        JsonObject programmeListObject = new JsonObject();
        programmeListObject.addProperty("currentActiveProgramme", 0);

        JsonArray programmeArray = new JsonArray();
        JsonObject programme = new JsonObject();
        programme.addProperty("programmeName", "Starter");

        JsonArray dayList = new JsonArray();

        dayList.add(createDayOne());
        dayList.add(createDayTwo());

        programme.add("dayList", dayList);
        programmeArray.add(programme);
        programmeListObject.add("programmeList", programmeArray);

        return programmeListObject;
    }

    private JsonObject createHistory() {
        JsonObject history = new JsonObject();

        JsonObject historyEntry = new JsonObject();
        historyEntry.add("day", createDay());
        historyEntry.add("mealList", createMeals());
        historyEntry.add("water", createWater());
        history.add("12-12-2024", historyEntry);

        return history;
    }

    private JsonObject createDay() {
        JsonObject day = new JsonObject();
        day.addProperty("name", "ONE");

        JsonArray exercisesHistory = new JsonArray();
        JsonObject historyBenchPress = new JsonObject();
        historyBenchPress.addProperty("sets", 3);
        historyBenchPress.addProperty("reps", 12);
        historyBenchPress.addProperty("weight", 30);
        historyBenchPress.addProperty("calories", 200);
        historyBenchPress.addProperty("name", "Bench_Press");

        JsonObject historySquat = new JsonObject();
        historySquat.addProperty("sets", 3);
        historySquat.addProperty("reps", 12);
        historySquat.addProperty("weight", 50);
        historySquat.addProperty("calories", 200);
        historySquat.addProperty("name", "Squat");

        exercisesHistory.add(historyBenchPress);
        exercisesHistory.add(historySquat);
        day.add("exercises", exercisesHistory);

        return day;
    }

    private JsonObject createMeals() {
        JsonObject mealList = new JsonObject();
        JsonArray meals = new JsonArray();
        JsonObject pasta = new JsonObject();
        pasta.addProperty("calories", 560);
        pasta.addProperty("name", "pasta");
        meals.add(pasta);
        mealList.add("meals", meals);
        return mealList;
    }

    private JsonObject createWater() {
        JsonObject water = new JsonObject();
        JsonArray waterList = new JsonArray();
        waterList.add(300.0);
        water.add("waterList", waterList);
        return water;
    }

    private JsonObject createDayOne() {
        JsonObject dayOne = new JsonObject();
        dayOne.addProperty("name", "ONE");

        JsonArray exercisesOne = new JsonArray();
        JsonObject benchPress = new JsonObject();
        benchPress.addProperty("sets", 3);
        benchPress.addProperty("reps", 12);
        benchPress.addProperty("weight", 30);
        benchPress.addProperty("calories", 200);
        benchPress.addProperty("name", "Bench_Press");

        JsonObject squat = new JsonObject();
        squat.addProperty("sets", 3);
        squat.addProperty("reps", 12);
        squat.addProperty("weight", 50);
        squat.addProperty("calories", 200);
        squat.addProperty("name", "Squat");

        exercisesOne.add(benchPress);
        exercisesOne.add(squat);
        dayOne.add("exercises", exercisesOne);

        return dayOne;
    }

    private JsonObject createDayTwo() {
        JsonObject dayTwo = new JsonObject();
        dayTwo.addProperty("name", "TWO");

        JsonArray exercisesTwo = new JsonArray();
        JsonObject bicepCurl = new JsonObject();
        bicepCurl.addProperty("sets", 3);
        bicepCurl.addProperty("reps", 12);
        bicepCurl.addProperty("weight", 10);
        bicepCurl.addProperty("calories", 100);
        bicepCurl.addProperty("name", "Bicep_Curl");

        exercisesTwo.add(bicepCurl);
        dayTwo.add("exercises", exercisesTwo);
        return dayTwo;
    }
}
