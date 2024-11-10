package seedu.manager.storage;

import seedu.manager.event.EventList;
import seedu.manager.event.Event;
import seedu.manager.enumeration.Priority;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author KuanHsienn
public class StorageTest {
    private static final String TEST_FILE_PATH = "test_file.csv";
    private static final String NON_EXISTENT_FILE_PATH = "non_existent_file.txt";

    private Storage storage;
    private EventList eventList;
    private DateTimeFormatter formatter;

    @BeforeEach
    public void setUp() {
        System.setProperty("test.environment", "true"); // Set the system property for testing
        storage = new Storage(TEST_FILE_PATH);
        eventList = new EventList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @AfterEach
    public void tearDown() {
        File itemsFile = new File(TEST_FILE_PATH);

        // Clean up the test files after each test
        if (itemsFile.exists()) {
            itemsFile.delete();
        }
    }

    @Test
    public void testLoadEventsFileNotExists() {
        Storage nonExistentFileStorage = new Storage(NON_EXISTENT_FILE_PATH);

        IOException exception = assertThrows(IOException.class, () -> {
            nonExistentFileStorage.loadInfo(eventList);
        });

        assertEquals("Error loading data from file: " + NON_EXISTENT_FILE_PATH +
                ". New file will be created since it does not exists.", exception.getMessage());
    }

    @Test
    public void testLoadEvents() {
        try {
            FileWriter writer = new FileWriter(TEST_FILE_PATH);
            writer.append("EVENT,Test Event,2024-11-04 12:00,Test Venue,LOW,N\n");
            writer.append("PARTICIPANT,Alice,alice@example.com,Test Event,N\n");
            writer.append("ITEM,Test Item,Test Event,N\n");
            writer.close();
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        try {
            storage.loadInfo(eventList);
        } catch (IOException e) {
            fail("Loading info failed: " + e.getMessage());
        }

        // Verify that the event was loaded correctly
        assertEquals(1, eventList.getList().size());
        Event loadedEvent = eventList.getList().get(0);
        assertEquals("Test Event", loadedEvent.getEventName());
        assertEquals(1, loadedEvent.getParticipantList().size());
        assertEquals("Alice", loadedEvent.getParticipantList().get(0).getName());
        assertEquals(1, loadedEvent.getItemList().size());
        assertEquals("Test Item", loadedEvent.getItemList().get(0).getName());
    }

    @Test
    public void testSaveEvents() {
        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);
        eventList.addParticipantToEvent("Alice", "alice@example.com", false, "Meeting");
        eventList.addItemToEvent("Projector", false, "Meeting");

        eventList.addEvent("Workshop", LocalDateTime.parse("2024-10-26 14:00", formatter),
                "Main Hall", Priority.MEDIUM);
        eventList.addParticipantToEvent("Bob", "bob@example.com", false, "Workshop");
        eventList.addItemToEvent("Whiteboard", false, "Workshop");

        try {
            storage.saveInfo(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when saving events: " + exception.getMessage());
        }

        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "The file should exist after saving events.");

        try {
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            String expectedContent = "\"EVENT\",\"Meeting\",\"2024-10-25 10:00\",\"Conference Room\",\"HIGH\",\"N\"\n"
                    + "\"PARTICIPANT\",\"Alice\",\"alice@example.com\",\"Meeting\",\"N\"\n"
                    + "\"ITEM\",\"Projector\",\"Meeting\",\"N\"\n"
                    + "\"EVENT\",\"Workshop\",\"2024-10-26 14:00\",\"Main Hall\",\"MEDIUM\",\"N\"\n"
                    + "\"PARTICIPANT\",\"Bob\",\"bob@example.com\",\"Workshop\",\"N\"\n"
                    + "\"ITEM\",\"Whiteboard\",\"Workshop\",\"N\"\n";
            assertEquals(expectedContent, content, "The file content does not match the expected output.");
        } catch (IOException exception) {
            fail("Exception should not be thrown when reading the saved file: " + exception.getMessage());
        }
    }

    @Test
    public void loadParticipants_validEntry_success() {
        try {
            FileWriter writer = new FileWriter(TEST_FILE_PATH);
            writer.append("PARTICIPANT,John Doe,jdoe@gmail.com,Meeting,N\n");
            writer.close();
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);

        try {
            storage.loadInfo(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when loading events: " + exception.getMessage());
        }

        assertEquals(1, eventList.getEventByName("Meeting").get().getParticipantCount());
    }

    @Test
    public void loadParticipants_invalidEntry_failure() {
        try {
            FileWriter writer = new FileWriter(TEST_FILE_PATH);
            writer.append("PARTICIPANT,John Doe,,,jb,N\n");
            writer.close();
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);

        try {
            storage.loadInfo(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when loading events: " + exception.getMessage());
        }

        assertEquals(0, eventList.getEventByName("Meeting").get().getParticipantCount());
    }

    @Test
    public void loadItems_validEntry_success() {
        try {
            FileWriter writer = new FileWriter(TEST_FILE_PATH);
            writer.append("ITEM,Black Pens,Meeting,N\n");
            writer.close();
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);

        try {
            storage.loadInfo(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when loading events: " + exception.getMessage());
        }

        assertEquals(1, eventList.getEventByName("Meeting").get().getItemCount());
    }
}


