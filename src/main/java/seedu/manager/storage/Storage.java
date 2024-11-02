package seedu.manager.storage;

import seedu.manager.event.EventList;
import seedu.manager.event.Event;
import seedu.manager.item.Item;
import seedu.manager.item.Participant;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//@@author KuanHsienn
/**
 * Represents the storage component for saving and loading events.
 *
 */
public class Storage {
    private final String eventFilePath;
    private final String participantFilePath;
    private final String itemFilePath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param eventFilePath The path to the storage file.
     */
    public Storage(String eventFilePath, String participantFilePath, String itemFilePath) {
        if (!isTestEnvironment()) {
            assert isValidFilePath(eventFilePath) : "Invalid file path: " + eventFilePath;
        }
        this.eventFilePath = eventFilePath;
        this.participantFilePath = participantFilePath;
        this.itemFilePath = itemFilePath;
    }

    /**
     * Saves the list of events to the file.
     *
     * @param events The EventList to be saved.
     * @throws IOException if there's an error writing to the file.
     */
    public void saveEvents(EventList events) throws IOException {
        try (FileWriter writer = new FileWriter(eventFilePath)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            for (Event event : events.getList()) {
                String eventTimeString = formatter.format(event.getEventTime());
                writer.write(event.getEventName() + "," + eventTimeString + ","
                        + event.getEventVenue() + ","
                        + event.getEventPriority() + "\n"); // Save event details in CSV format
            }
        } catch (IOException exception) {
            throw new IOException("Error saving events to file: " + eventFilePath);
        }
    }

    //@@author jemehgoh
    /**
     * Saves the participants stored in the events in the event list to the file.
     *
     * @param events The event list with events containing participant data.
     * @throws IOException if there is an error writing to the file.
     */
    public void saveParticipants(EventList events) throws IOException {
        try (FileWriter writer = new FileWriter(participantFilePath)) {
            for (Event event : events.getList()) {
                ArrayList<Participant> participants = event.getParticipantList();
                for (Participant participant : participants) {
                    writer.write(participant.getName() + "," + participant.getNumber() + ","
                            + participant.getEmail() + "," + event.getEventName()  + "\n"); // Save event details in CSV format
                }
            }
        } catch (IOException exception) {
            throw new IOException("Error saving events to file: " + participantFilePath);
        }
    }

    /**
     * Saves the items stored in the events in the event list to the file.
     *
     * @param events The event list with events containing item data.
     * @throws IOException if there is an error writing to the file.
     */
    public void saveItems(EventList events) throws IOException {
        try (FileWriter writer = new FileWriter(itemFilePath)) {
            for (Event event : events.getList()) {
                ArrayList<Item> items = event.getItemList();
                for (Item item : items) {
                    writer.write(item.getName() + "," + event.getEventName()  + "\n"); // Save event details in CSV format
                }
            }
        } catch (IOException exception) {
            throw new IOException("Error saving events to file: " + itemFilePath);
        }
    }

    //@@author KuanHsienn
    /**
     * Loads events from the file and returns an EventList.
     *
     * @throws IOException if there's an error reading from the file.
     */
    public void loadEvents(EventList events) throws IOException {
        FileParser parser = new FileParser();
        parser.parseEventsFile(events, eventFilePath);
    }

    /**
     * Loads participants from the file into the specified Events in EventList.
     *
     * @throws IOException if there is an error reading from the file.
     */
    public void loadParticipants(EventList events) throws IOException {
        FileParser parser = new FileParser();
        parser.parseParticipantsFile(events, participantFilePath);
    }


    /**
     * Loads items from the file into the specified Events in EventList.
     *
     * @throws IOException if there is an error reading from the file.
     */
    public void loadItems(EventList events) throws IOException {
        FileParser parser = new FileParser();
        parser.parseItemsFile(events, itemFilePath);
    }

    //@@author KuanHsienn
    /**
     * Checks if the given file path is valid and matches the expected path.
     *
     * @param filePath The path to check.
     * @return True if valid and matches, false otherwise.
     */
    private boolean isValidFilePath(String filePath) {
        String expectedPath = "events.csv"; // You can change this if needed
        return filePath.equals(expectedPath);
    }

    /**
     * Checks if the code is running in a test environment.
     *
     * @return True if in test environment, false otherwise.
     */
    private boolean isTestEnvironment() {
        return "true".equals(System.getProperty("test.environment"));
    }
}


