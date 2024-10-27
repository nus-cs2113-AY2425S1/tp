package seedu.manager.storage;

import seedu.manager.event.EventList;
import seedu.manager.event.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//@@author KuanHsienn
/**
 * Represents the storage component for saving and loading events.
 *
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of events to the file.
     *
     * @param events The EventList to be saved.
     * @throws IOException if there's an error writing to the file.
     */
    public void saveEvents(EventList events) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Event event : events.getList()) {
                writer.write(event.getEventName() + "," + event.getEventTime() + ","
                        + event.getEventVenue() + "\n"); // Save event details in CSV format
            }
        } catch (IOException exception) {
            throw new IOException("Error saving events to file: " + filePath);
        }
    }

    /**
     * Loads events from the file and returns an EventList.
     *
     * @throws IOException if there's an error reading from the file.
     */
    public void loadEvents(EventList events) throws IOException {
        try {
            for (String line : Files.readAllLines(Paths.get(filePath))) {
                String[] parts = line.split(","); // CSV format
                if (parts.length == 3) {
                    String eventName = parts[0].trim();
                    String time = parts[1].trim();
                    String venue = parts[2].trim();
                    events.addEvent(eventName, time, venue);
                }
            }
        } catch (IOException exception) {
            throw new IOException("Error loading events from file: " + filePath + ".");
        }
    }
}


