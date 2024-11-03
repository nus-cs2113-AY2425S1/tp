package seedu.manager.storage;

import seedu.manager.enumeration.Priority;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author KuanHsienn
public class StorageTest {
    private static final String TEST_EVENTS_FILE_PATH = "test_events.csv";
    private static final String TEST_PARTICIPANTS_FILE_PATH = "test_participants.csv";
    private static final String TEST_ITEMS_FILE_PATH = "test_items.csv";
    private static final String NON_EXISTENT_FILE_PATH = "non_existent_file.txt";

    private Storage storage;
    private EventList eventList;
    private DateTimeFormatter formatter;

    @BeforeEach
    public void setUp() {
        System.setProperty("test.environment", "true"); // Set the system property for testing
        storage = new Storage(TEST_EVENTS_FILE_PATH, TEST_PARTICIPANTS_FILE_PATH, TEST_ITEMS_FILE_PATH);
        eventList = new EventList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @AfterEach
    public void tearDown() {
        File eventsFile = new File(TEST_EVENTS_FILE_PATH);
        File participantsFile = new File(TEST_PARTICIPANTS_FILE_PATH);
        File itemsFile = new File(TEST_ITEMS_FILE_PATH);

        // Clean up the test files after each test
        if (eventsFile.exists()) {
            eventsFile.delete();
        }

        if (participantsFile.exists()) {
            participantsFile.delete();
        }

        if (itemsFile.exists()) {
            itemsFile.delete();
        }
    }

    @Test
    public void testLoadEventsFileNotExists() {
        Storage nonExistentFileStorage = new Storage(NON_EXISTENT_FILE_PATH, NON_EXISTENT_FILE_PATH,
                NON_EXISTENT_FILE_PATH);

        IOException exception = assertThrows(IOException.class, () -> {
            nonExistentFileStorage.loadEvents(eventList);
        });

        assertEquals("Error loading events from file: " + NON_EXISTENT_FILE_PATH + ".", exception.getMessage());
    }

    @Test
    public void testLoadEvents() {
        try {
            java.nio.file.Files.writeString(
                    java.nio.file.Paths.get(TEST_EVENTS_FILE_PATH),
                    "Meeting,2024-10-25 10:00,Conference Room,HIGH,Y\n" +
                            "Workshop,2024-10-26 14:00,Main Hall,MEDIUM,N\n"
            );
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        try {
            storage.loadEvents(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when loading events: " + exception.getMessage());
        }

        assertEquals(2, eventList.getList().size());

        Event firstEvent = eventList.getList().get(0);
        assertEquals("Meeting", firstEvent.getEventName());
        assertEquals("2024-10-25 10:00", firstEvent.getEventTimeString());
        assertEquals("Conference Room", firstEvent.getEventVenue());

        Event secondEvent = eventList.getList().get(1);
        assertEquals("Workshop", secondEvent.getEventName());
        assertEquals("2024-10-26 14:00", secondEvent.getEventTimeString());
        assertEquals("Main Hall", secondEvent.getEventVenue());
    }

    @Test
    public void testSaveEvents() {
        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);
        eventList.addEvent("Workshop", LocalDateTime.parse("2024-10-26 14:00", formatter),
                "Main Hall", Priority.MEDIUM);

        try {
            storage.saveEvents(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when saving events: " + exception.getMessage());
        }

        File file = new File(TEST_EVENTS_FILE_PATH);
        assertTrue(file.exists());

        try {
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            String expectedContent = "\"Meeting\",\"2024-10-25 10:00\",\"Conference Room\",\"HIGH\",\"N\"\n"
                    + "\"Workshop\",\"2024-10-26 14:00\",\"Main Hall\",\"MEDIUM\",\"N\"\n";
            assertEquals(expectedContent, content, "The file content does not match the expected output.");
        } catch (IOException exception) {
            fail("Exception should not be thrown when reading the saved file: " + exception.getMessage());
        }
    }

    //@@author jemehgoh
    @Test
    public void loadParticipants_validEntry_success() {
        try {
            java.nio.file.Files.writeString(java.nio.file.Paths.get(TEST_PARTICIPANTS_FILE_PATH),
                    "John Doe,9451 3230,jdoe@gmail.com,N,Meeting\n");
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);

        try {
            storage.loadParticipants(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when loading events: " + exception.getMessage());
        }

        assertEquals(1, eventList.getEventByName("Meeting").get().getParticipantCount());
    }

    @Test
    public void loadParticipants_invalidEntry_failure() {
        try {
            java.nio.file.Files.writeString(java.nio.file.Paths.get(TEST_PARTICIPANTS_FILE_PATH),
                    "John Doe,,,jb,\n");
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);

        try {
            storage.loadParticipants(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when loading events: " + exception.getMessage());
        }

        assertEquals(0, eventList.getEventByName("Meeting").get().getParticipantCount());
    }

    @Test
    public void loadItems_validEntry_success() {
        try {
            java.nio.file.Files.writeString(java.nio.file.Paths.get(TEST_ITEMS_FILE_PATH),
                    "Black pens,N,Meeting\n");
        } catch (IOException exception) {
            fail("Failed to set up the test data file: " + exception.getMessage());
        }

        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);

        try {
            storage.loadItems(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when loading events: " + exception.getMessage());
        }

        assertEquals(1, eventList.getEventByName("Meeting").get().getItemCount());
    }

    @Test
    public void saveParticipants_validItem_success() {
        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);
        eventList.addParticipantToEvent("John Doe", "9451 3230", "jdoe@gmail.com", "Meeting");

        try {
            storage.saveParticipants(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when saving events: " + exception.getMessage());
        }

        File file = new File(TEST_PARTICIPANTS_FILE_PATH);
        assertTrue(file.exists());

        try {
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            String expectedContent = "\"John Doe\",\"9451 3230\",\"jdoe@gmail.com\",\"N\",\"Meeting\"\n";
            assertEquals(expectedContent, content, "The file content does not match the expected output.");
        } catch (IOException exception) {
            fail("Exception should not be thrown when reading the saved file: " + exception.getMessage());
        }
    }

    @Test
    public void saveItems_validItem_success() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        eventList.addEvent("Meeting", LocalDateTime.parse("2024-10-25 10:00", formatter),
                "Conference Room", Priority.HIGH);
        eventList.addItemToEvent("Black pens", "Meeting");

        try {
            storage.saveItems(eventList);
        } catch (IOException exception) {
            fail("Exception should not be thrown when saving events: " + exception.getMessage());
        }

        File file = new File(TEST_ITEMS_FILE_PATH);
        assertTrue(file.exists());

        try {
            String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
            String expectedContent = "\"Black pens\",\"N\",\"Meeting\"\n";
            assertEquals(expectedContent, content, "The file content does not match the expected output.");
        } catch (IOException exception) {
            fail("Exception should not be thrown when reading the saved file: " + exception.getMessage());
        }
    }
}


