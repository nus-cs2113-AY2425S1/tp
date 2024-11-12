package fittrack.storage;

import fittrack.exception.InvalidSaveDataException;
import fittrack.fitnessgoal.Goal;
import fittrack.healthprofile.FoodEntry;
import fittrack.healthprofile.FoodWaterIntake;
import fittrack.healthprofile.WaterEntry;
import fittrack.reminder.Reminder;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StorageTest {

    private static final String TEMP_SAVE_FILE_PATH = "data/tempSaveFile.txt";
    private User testUser;
    private ArrayList<TrainingSession> sessionList;
    private ArrayList<Goal> goalList;
    private ArrayList<Reminder> reminderList;
    private FoodWaterIntake foodWaterList;

    @BeforeEach
    void setUp() {

        // Set up a temporary file for testing
        Storage.setSaveFilePath(TEMP_SAVE_FILE_PATH);

        // Set up sample data for each list
        testUser = new User("MALE", "24");

        sessionList = new ArrayList<TrainingSession>();
        sessionList.add(new TrainingSession(LocalDateTime.now(), "Running", testUser));

        goalList = new ArrayList<Goal>();
        goalList.add(new Goal("Lose weight", LocalDateTime.now().plusDays(30)));

        reminderList = new ArrayList<Reminder>();
        reminderList.add(new Reminder("Drink water", LocalDateTime.now().plusHours(1), testUser));

        foodWaterList = new FoodWaterIntake();
        foodWaterList.addFood(new FoodEntry("Apple", 52, LocalDateTime.now()));
        foodWaterList.addWater(new WaterEntry(500, LocalDateTime.now()));
    }

    @AfterEach
    void tearDown() {
        // Delete the temporary save file after each test
        File file = new File(TEMP_SAVE_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testInitialiseSaveFileCreatesFileAndDirectory() {
        Storage.initialiseSaveFile();

        File saveFile = new File(TEMP_SAVE_FILE_PATH);
        assertTrue(saveFile.exists(), "Save file should exist after initialization.");
        assertTrue(saveFile.isFile(), "Save file should be a regular file.");
    }

    @Test
    void testLoadSaveFileCorrectlyLoadsData() throws IOException, InvalidSaveDataException {
        // Prepare a save file with serialized data for each object type
        try (FileWriter writer = new FileWriter(TEMP_SAVE_FILE_PATH)) {
            writer.write(testUser.toSaveString() + "\n");
            writer.write(sessionList.get(0).toSaveString() + "\n");
            writer.write(goalList.get(0).toSaveString() + "\n");
            writer.write(reminderList.get(0).toSaveString() + "\n");
            writer.write(foodWaterList.getFoodList().get(0).toSaveString() + "\n");
            writer.write(foodWaterList.getWaterList().get(0).toSaveString() + "\n");
        }

        ArrayList<Saveable> loadList = new ArrayList<>();
        Storage.loadSaveFile(loadList);

        // Validate that each object was loaded correctly
        assertEquals(6, loadList.size(), "Expected 6 items in load list.");
        assertEquals(User.class, loadList.get(0).getClass(), "First item should be User.");
        assertEquals(TrainingSession.class, loadList.get(1).getClass(), "Second item should be " +
                "TrainingSession.");
        assertEquals(Goal.class, loadList.get(2).getClass(), "Third item should be Goal.");
        assertEquals(Reminder.class, loadList.get(3).getClass(), "Fourth item should be Reminder.");
        assertEquals(FoodEntry.class, loadList.get(4).getClass(), "Fifth item should be FoodEntry.");
        assertEquals(WaterEntry.class, loadList.get(5).getClass(), "Sixth item should be WaterEntry.");
    }

    @Test
    void testLoadSaveFileWithInvalidData() throws IOException {
        // Write invalid data to the save file to simulate a corrupted file
        try (FileWriter writer = new FileWriter(TEMP_SAVE_FILE_PATH)) {
            writer.write("InvalidData|Random|Data\n");
        }

        ArrayList<Saveable> loadList = new ArrayList<>();

        // Call the method and expect an exception or log
        Storage.loadSaveFile(loadList);

        // Verify that no items were loaded and loading stopped
        assertEquals(0, loadList.size(), "No items should be loaded when invalid data is encountered.");
    }

    @Test
    void testLoadSaveFileFileNotFound() {
        // Delete the save file to simulate a FileNotFoundException
        File tempSaveFile = Storage.getSaveFile();
        tempSaveFile.delete();

        ArrayList<Saveable> loadList = new ArrayList<>();

        // Call the method and check for graceful handling (no items added to loadList)
        Storage.loadSaveFile(loadList);

        // Verify that no items were loaded
        assertEquals(0, loadList.size(), "No items should be loaded when file is not found.");
    }

    @Test
    void testCreateSaveableFromStringValidData() throws InvalidSaveDataException {
        // Check each Saveable type with valid data
        String sessionString = sessionList.get(0).toSaveString();
        Saveable session = Storage.createSaveableFromString(sessionString);
        assertInstanceOf(TrainingSession.class, session, "Should create a TrainingSession instance.");

        String goalString = goalList.get(0).toSaveString();
        Saveable goal = Storage.createSaveableFromString(goalString);
        assertInstanceOf(Goal.class, goal, "Should create a Goal instance.");

        String reminderString = reminderList.get(0).toSaveString();
        Saveable reminder = Storage.createSaveableFromString(reminderString);
        assertInstanceOf(Reminder.class, reminder, "Should create a Reminder instance.");

        String foodString = foodWaterList.getFoodList().get(0).toSaveString();
        Saveable foodEntry = Storage.createSaveableFromString(foodString);
        assertInstanceOf(FoodEntry.class, foodEntry, "Should create a FoodEntry instance.");

        String waterString = foodWaterList.getWaterList().get(0).toSaveString();
        Saveable waterEntry = Storage.createSaveableFromString(waterString);
        assertInstanceOf(WaterEntry.class, waterEntry, "Should create a WaterEntry instance.");
    }

    @Test
    void testCreateSaveableFromStringInvalidData() {
        // Test with invalid data to trigger exception
        String invalidString = "InvalidData|Random|Data";
        assertThrows(InvalidSaveDataException.class, () -> Storage.createSaveableFromString(invalidString),
                "Should throw InvalidSaveDataException for invalid data.");
    }

    @Test
    void testUpdateSaveFileWritesCorrectData() throws IOException {
        // Write data to save file using updateSaveFile
        Storage.updateSaveFile(testUser, sessionList, goalList, reminderList, foodWaterList);

        // Read back the file and verify its contents
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(TEMP_SAVE_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }

        // Validate the serialized format and contents of each line
        assertEquals(6, lines.size(), "Expected 6 lines in save file.");
        assertEquals(testUser.toSaveString(), lines.get(0), "First line should be User data.");
        assertEquals(sessionList.get(0).toSaveString(), lines.get(1), "Second line should be " +
                "TrainingSession data.");
        assertEquals(goalList.get(0).toSaveString(), lines.get(2), "Third line should be Goal data.");
        assertEquals(reminderList.get(0).toSaveString(), lines.get(3), "Fourth line should be Reminder data.");
        assertEquals(foodWaterList.getFoodList().get(0).toSaveString(), lines.get(4), "Fifth line should be " +
                "FoodEntry data.");
        assertEquals(foodWaterList.getWaterList().get(0).toSaveString(), lines.get(5), "Sixth line should " +
                "be WaterEntry data.");
    }
}
