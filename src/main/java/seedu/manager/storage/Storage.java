package seedu.manager.storage;

import com.opencsv.CSVWriter;
import seedu.manager.event.EventList;
import seedu.manager.event.Event;
import seedu.manager.item.Item;
import seedu.manager.item.Participant;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//@@author KuanHsienn
/**
 * Represents the storage component for saving and loading events.
 *
 */
public class Storage {
    private static final String CSV_LINE_FORMAT = "%s,%s,%s,%s,%s\n";
    private static final String ITEM_CSV_LINE_FORMAT = "%s,%s,%s\n";

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
        try (CSVWriter writer = new CSVWriter(new FileWriter(eventFilePath))) {
            for (Event event : events.getList()) {
                String[] fields = getEventFields(event);
                writer.writeNext(fields);
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
        try (CSVWriter writer = new CSVWriter(new FileWriter(participantFilePath))) {
            for (Event event : events.getList()) {
                saveEventParticipants(event, writer);
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
        try (CSVWriter writer = new CSVWriter(new FileWriter(itemFilePath))) {
            for (Event event : events.getList()) {
                saveEventItems(event, writer);
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

    //@@author jemehgoh
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

    //@@author jemehgoh
    /**
     * Saves the data of the participants in a given event to a given file writer.
     *
     * @param event the event containing the participant data.
     * @param writer the file writer.
     * @throws IOException if the participant cannot be written into the file.
     */
    private void saveEventParticipants(Event event, CSVWriter writer) throws IOException {
        ArrayList<Participant> participants = event.getParticipantList();
        for (Participant participant : participants) {
            String[] fields = getParticipantFields(participant, event);
            writer.writeNext(fields);
        }
    }

    /**
     * Saves the data of the items in a given event to a given file writer.
     *
     * @param event the event containing the item data.
     * @param writer the file writer.
     * @throws IOException if the participant cannot be written into the file.
     */
    private void saveEventItems(Event event, CSVWriter writer) throws IOException {
        ArrayList<Item> items = event.getItemList();
        for (Item item : items) {
            String[] fields = getItemFields(event, item);
            writer.writeNext(fields);
        }
    }

    /**
     * Returns an array of the fields for a given {@link Event}.
     *
     * @param event the given {@link Event}.
     * @return an array of the fields of `event`.
     */
    private static String[] getEventFields(Event event) {
        List<String> fieldsList = List.of(event.getEventName(), event.getEventTimeString(),
                event.getEventVenue(), event.getEventPriorityString(), event.markIfDone());
        return fieldsList.toArray(new String[5]);
    }

    /**
     * Returns an array of the fields for a given {@link Participant} in a given {@link Event}.
     *
     * @param participant the given {@link Participant}.
     * @param event the given {@link Event}.
     * @return an array of the fields of `event`.
     */
    private String[] getParticipantFields(Participant participant, Event event) {
        List<String> fieldsList = List.of(participant.getName(), participant.getNumber(),
                participant.getEmail(), participant.markFileLineIfPresent(), event.getEventName());
        return fieldsList.toArray(new String[5]);
    }

    /**
     * Returns an array of the fields for a given {@link Item} in a given {@link Event}.
     *
     * @param item the given {@link Item}.
     * @param event the given {@link Event}.
     * @return an array of the fields of `event`.
     */
    private String[] getItemFields(Event event, Item item) {
        List<String> fieldsList = List.of(item.getName(), item.markFileLineIfPresent(),
                event.getEventName());
        return fieldsList.toArray(new String[3]);
    }
}


