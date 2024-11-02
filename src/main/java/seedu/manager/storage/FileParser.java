package seedu.manager.storage;

import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

/**
 * Represents the file parser for EventManagerCLI
 */
public class FileParser {
    private static final Logger logger = Logger.getLogger(FileParser.class.getName());

    //@@author KuanHsienn
    /**
     * Parses a CSV file containing event details and loads the events into the specified EventList.
     *
     * This method reads each line from the specified file, expecting the format to be:
     * <pre>
     * eventName, eventTime, eventVenue, eventPriority
     * </pre>
     * where:
     * - eventName is a String representing the name of the event.
     * - eventTime is a String formatted as "yyyy-MM-dd HH:mm" that will be parsed into a LocalDateTime object.
     * - eventVenue is a String representing the venue of the event.
     * - eventPriority is a String representing the priority level of the event.
     *
     * If a line does not contain exactly three parts, it is skipped.
     *
     * @param events The EventList where the parsed events will be added.
     * @param filePath The path to the file containing the event details.
     * @throws IOException If there is an error reading from the file or if the file cannot be found.
     */
    public void parseEventsFile(EventList events, String filePath) throws IOException {
        try {
            logger.log(INFO, "Loading events from file");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            for (String line : Files.readAllLines(Paths.get(filePath))) {
                String[] parts = line.split(","); // CSV format
                if (parts.length == 4) {
                    String eventName = parts[0].trim();
                    LocalDateTime time = LocalDateTime.parse(parts[1].trim(), formatter);
                    String venue = parts[2].trim();
                    Priority priority = Priority.valueOf(parts[3].trim().toUpperCase());
                    events.addEvent(eventName, time, venue, priority);
                } else {
                    logger.log(WARNING, "File line cannot be parsed, event not loaded");
                }
            }
        } catch (IOException exception) {
            throw new IOException("Error loading events from file: " + filePath + ".");
        }
    }

    //@@author jemehgoh
    /**
     * Parses a CSV file containing participant details and loads the item into the corresponding Event in
     *         the specified EventList.
     *
     * @param events The EventList of Events to which the participants will be added.
     * @param filePath The path to the file containing the participant details.
     * @throws IOException If there is an error reading from the file or if the file cannot be found.
     */
    public void parseParticipantsFile(EventList events, String filePath) throws IOException {
        try {
            logger.log(INFO, "Loading event participants from file");
            for (String line : Files.readAllLines(Paths.get(filePath))) {
                String[] parts = line.split(","); // CSV format
                if (parts.length == 4) {
                    String participantName = parts[0].trim();
                    String number = parts[1].trim();
                    String email = parts[2].trim();
                    String eventName = parts[3].trim();
                    events.addParticipantToEvent(participantName, number, email, eventName);
                } else {
                    logger.log(WARNING, "File line cannot be parsed, participant not loaded");
                }
            }
        } catch (IOException exception) {
            throw new IOException("Error loading events from file: " + filePath + ".");
        }
    }

    /**
     * Parses a CSV file containing item details and loads the item into the corresponding Event in
     *         the specified EventList.
     *
     * @param events The EventList of Events to which the items will be added.
     * @param filePath The path to the file containing the item details.
     * @throws IOException If there is an error reading from the file or if the file cannot be found.
     */
    public void parseItemsFile(EventList events, String filePath) throws IOException {
        try {
            logger.log(INFO, "Loading event items from file");
            for (String line : Files.readAllLines(Paths.get(filePath))) {
                String[] parts = line.split(","); // CSV format
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    String eventName = parts[1].trim();
                    events.addItemToEvent(itemName, eventName);
                } else {
                    logger.log(WARNING, "File line cannot be parsed, item not loaded");
                }
            }
        } catch (IOException exception) {
            throw new IOException("Error loading events from file: " + filePath + ".");
        }
    }
}
