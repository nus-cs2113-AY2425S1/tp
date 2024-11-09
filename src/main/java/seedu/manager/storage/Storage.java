package seedu.manager.storage;

import com.opencsv.CSVWriter;
import seedu.manager.event.EventList;
import seedu.manager.event.Event;
import seedu.manager.item.Item;
import seedu.manager.item.Participant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.nio.charset.StandardCharsets;

//@@author KuanHsienn
/**
 * This class is responsible for handling the storage of event data.
 * It provides methods to save and load event information from a CSV file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where event data will be stored.
     * @throws AssertionError If the environment is not a test and the file path is invalid.
     */
    public Storage(String filePath) {
        if (!isTestEnvironment()) {
            assert isValidFilePath(filePath) : "Invalid file path: " + filePath;
        }
        this.filePath = filePath;
    }

    /**
     * Saves the event information to the specified file.
     *
     * @param events The EventList containing the events to be saved.
     * @throws IOException If there is an error saving data to the file.
     */
    public void saveInfo(EventList events) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for (Event event : events.getList()) {
                writer.writeNext(getEventFields(event));

                for (Participant participant : event.getParticipantList()) {
                    writer.writeNext(getParticipantFields(participant, event));
                }

                for (Item item : event.getItemList()) {
                    writer.writeNext(getItemFields(event, item));
                }
            }
        } catch (IOException exception) {
            throw new IOException("Error saving data to file: " + filePath + ". Kindly close any opened files.");
        }
    }

    /**
     * Loads the event information from the specified file.
     *
     * @param events The EventList to load events into.
     * @throws IOException If there is an error loading data from the file.
     */
    public void loadInfo(EventList events) throws IOException {
        FileParser parser = new FileParser();
        parser.parseFile(events, filePath);
    }

    /**
     * Checks if the provided file path is valid.
     *
     * @param filePath The file path to validate.
     * @return true if the file path is valid; false otherwise.
     */
    private boolean isValidFilePath(String filePath) {
        String expectedPath = "data.csv";
        return filePath.equals(expectedPath);
    }

    /**
     * Checks if the current environment is a test environment.
     *
     * @return true if the test environment property is set; false otherwise.
     */
    private boolean isTestEnvironment() {
        return "true".equals(System.getProperty("test.environment"));
    }

    /**
     * Retrieves the fields of an event as a String array for CSV writing.
     *
     * @param event The event to get fields from.
     * @return A String array representing the event fields.
     */
    private static String[] getEventFields(Event event) {
        List<String> fieldsList = List.of("EVENT", event.getEventName(), event.getEventTimeString(),
                event.getEventVenue(), event.getEventPriorityString(), event.markIfDone());
        return fieldsList.toArray(new String[6]);
    }

    /**
     * Retrieves the fields of a participant as a String array for CSV writing.
     *
     * @param participant The participant to get fields from.
     * @param event      The event associated with the participant.
     * @return A String array representing the participant fields.
     */
    private String[] getParticipantFields(Participant participant, Event event) {
        List<String> fieldsList = List.of("PARTICIPANT", participant.getName(), participant.getNumber(),
                participant.getEmail(), event.getEventName(), participant.markFileLineIfPresent());
        return fieldsList.toArray(new String[6]);
    }

    /**
     * Retrieves the fields of an item as a String array for CSV writing.
     *
     * @param event The event associated with the item.
     * @param item  The item to get fields from.
     * @return A String array representing the item fields.
     */
    private String[] getItemFields(Event event, Item item) {
        List<String> fieldsList = List.of("ITEM", item.getName(), event.getEventName(), item.markFileLineIfPresent());
        return fieldsList.toArray(new String[4]);
    }
}
