package seedu.manager.storage;

import seedu.manager.event.EventList;
import seedu.manager.event.Event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;


public class StorageTest {
    private static final String TEST_FILE_PATH = "test_events.txt";
    private static final String NON_EXISTENT_FILE_PATH = "non_existent_file.txt";
    private Storage storage;
    private EventList eventList;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
        eventList = new EventList();
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        // Clean up the test file after each test
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testLoadEvents_FileNotExists() {
        Storage nonExistentFileStorage = new Storage(NON_EXISTENT_FILE_PATH);

        IOException exception = assertThrows(IOException.class, () -> {
            nonExistentFileStorage.loadEvents(eventList);
        });

        assertEquals("Error loading events from file: " + NON_EXISTENT_FILE_PATH + ".", exception.getMessage());
    }

    @Test
    public void testLoadEvents() {
        try {
            java.nio.file.Files.writeString(
                    java.nio.file.Paths.get(TEST_FILE_PATH),
                    "Meeting,2024-10-25 10:00,Conference Room\n" +
                            "Workshop,2024-10-26 14:00,Main Hall\n"
            );
        } catch (IOException e) {
            fail("Failed to set up the test data file: " + e.getMessage());
        }

        try {
            storage.loadEvents(eventList);
        } catch (IOException e) {
            fail("Exception should not be thrown when loading events: " + e.getMessage());
        }

        assertEquals(2, eventList.getList().size());

        Event firstEvent = eventList.getList().get(0);
        assertEquals("Meeting", firstEvent.getEventName());
        assertEquals("2024-10-25 10:00", firstEvent.getEventTime());
        assertEquals("Conference Room", firstEvent.getEventVenue());

        Event secondEvent = eventList.getList().get(1);
        assertEquals("Workshop", secondEvent.getEventName());
        assertEquals("2024-10-26 14:00", secondEvent.getEventTime());
        assertEquals("Main Hall", secondEvent.getEventVenue());
    }

    @Test
    public void testSaveEvents() {
        eventList.addEvent("Meeting", "2024-10-25 10:00", "Conference Room");
        eventList.addEvent("Workshop", "2024-10-26 14:00", "Main Hall");

        try {
            storage.saveEvents(eventList);
        } catch (IOException e) {
            fail("Exception should not be thrown when saving events: " + e.getMessage());
        }

        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists());

        try {
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            String expectedContent = "Meeting,2024-10-25 10:00,Conference Room\n"
                    + "Workshop,2024-10-26 14:00,Main Hall\n";
            assertEquals(expectedContent, content, "The file content does not match the expected output.");
        } catch (IOException e) {
            fail("Exception should not be thrown when reading the saved file: " + e.getMessage());
        }
    }


}


