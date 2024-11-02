package seedu.manager.storage;

import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                parseEventFileLine(events, line, formatter);
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
                parseParticipantFileLine(events, line);
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
                parseItemFileLine(events, line);
            }
        } catch (IOException exception) {
            throw new IOException("Error loading events from file: " + filePath + ".");
        }
    }

    //@@author KuanHsienn
    /**
     * Parses one line of the CSV file containing event details into a {@code Event} in the given event list.
     *
     * @param events the given event list.
     * @param line the line of the CSV file being parsed.
     * @param formatter the given date-time formatter (to parse event date and time).
     * @throws IOException if line cannot be parsed successfully.
     */
    private void parseEventFileLine(EventList events, String line, DateTimeFormatter formatter) throws IOException {
        String[] parts = line.split(",");
        try {
            String eventName = parts[0].trim();
            LocalDateTime time = LocalDateTime.parse(parts[1].trim(), formatter);
            String venue = parts[2].trim();
            Priority priority = Priority.valueOf(parts[3].trim().toUpperCase());
            boolean isDone = getIsMarked(parts[4].trim());
            events.addEvent(eventName, time, venue, priority, isDone);
        } catch (DateTimeParseException | IndexOutOfBoundsException | NullPointerException exception) {
            logger.log(WARNING, "File line cannot be parsed, event not loaded");
        }
    }

    //@@author jemehgoh
    /**
     * Parses one line of the CSV file containing participant details into a {@code Participant} in an
     *         {@code Event} in the given event list.
     *
     * @param events the given event list.
     * @param line the line of the CSV file being parsed.
     * @throws IOException if line cannot be parsed successfully.
     */
    private void parseParticipantFileLine(EventList events, String line) throws IOException {
        String[] parts = line.split(",");
        try {
            String participantName = parts[0].trim();
            String number = parts[1].trim();
            String email = parts[2].trim();
            boolean isPresent = getIsMarked(parts[3].trim());
            String eventName = parts[4].trim();
            events.addParticipantToEvent(participantName, number, email, isPresent, eventName);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            logger.log(WARNING, "File line cannot be parsed, participant not loaded");
        }
    }

    /**
     * Parses one line of the CSV file containing item details into a {@code Item} in an
     *         {@code Event} in the given event list.
     *
     * @param events the given event list.
     * @param line the line of the CSV file being parsed.
     * @throws IOException if line cannot be parsed successfully.
     */
    private void parseItemFileLine(EventList events, String line) throws IOException {
        String[] parts = line.split(",");
        try {
            String itemName = parts[0].trim();
            boolean isPresent = getIsMarked(parts[1].trim());
            String eventName = parts[2].trim();
            events.addItemToEvent(itemName, isPresent, eventName);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            logger.log(WARNING, "File line cannot be parsed, item not loaded");
        }
    }

    /**
     * Returns true if the given mark status is "Y", returns false otherwise.
     * 
     * @param markStatus the given mark status.
     * @return {@code true} if the markStatus is "Y", {@code false} otherwise.
     */
    private boolean getIsMarked(String markStatus) {
        if (markStatus.equalsIgnoreCase("Y")) {
            return true;
        } else if (markStatus.equalsIgnoreCase("N")) {
            return false;
        } else {
            logger.log(WARNING, "Cannot parse mark status, setting to false");
            return false;
        }
    }
}
