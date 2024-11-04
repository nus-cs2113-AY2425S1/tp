package seedu.manager.storage;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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
            List<String[]> lines = getFileLines(filePath);
            for (String[] line : lines) {
                parseEventFileLine(events, line, formatter);
            }
        } catch (IOException | CsvException exception) {
            throw new IOException("Error loading events from file: " + filePath + ".");
        }
    }

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
            List<String[]> lines = getFileLines(filePath);
            for (String[] line : lines) {
                parseParticipantFileLine(events, line);
            }
        } catch (IOException | CsvException exception) {
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
            List<String[]> lines = getFileLines(filePath);
            for (String[] line : lines) {
                parseItemFileLine(events, line);
            }
        } catch (IOException | CsvException exception) {
            throw new IOException("Error loading events from file: " + filePath + ".");
        }
    }

    /**
     * Parses one line of the CSV file containing event details into a {@code Event} in the given event list.
     *
     * @param events the given event list.
     * @param fields the fields of the line of the CSV file being parsed.
     * @param formatter the given date-time formatter (to parse event date and time).
     * @throws IOException if line cannot be parsed successfully.
     */
    private void parseEventFileLine(EventList events, String[] fields, DateTimeFormatter formatter) throws IOException {
        try {
            String eventName = fields[0].trim();
            LocalDateTime time = LocalDateTime.parse(fields[1].trim(), formatter);
            String venue = fields[2].trim();
            Priority priority = Priority.valueOf(fields[3].trim().toUpperCase());
            boolean isDone = getIsMarked(fields[4].trim());
            events.addEvent(eventName, time, venue, priority, isDone);
        } catch (DateTimeParseException | IndexOutOfBoundsException | NullPointerException
                | IllegalArgumentException exception) {
            logger.log(WARNING, "File line cannot be parsed, event not loaded");
        }
    }

    /**
     * Parses one line of the CSV file containing participant details into a {@code Participant} in an
     *         {@code Event} in the given event list.
     *
     * @param events the given event list.
     * @param fields the fields in the line of the CSV file being parsed.
     * @throws IOException if line cannot be parsed successfully.
     */
    private void parseParticipantFileLine(EventList events, String[] fields) throws IOException {
        try {
            String participantName = fields[0].trim();
            String number = fields[1].trim();
            String email = fields[2].trim();
            boolean isPresent = getIsMarked(fields[3].trim());
            String eventName = fields[4].trim();
            boolean isLoaded = events.addParticipantToEvent(participantName, number, email,
                    isPresent, eventName);
            logOnUnsuccessfulLoad(isLoaded);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            logger.log(WARNING, "File line cannot be parsed, participant not loaded");
        }
    }

    /**
     * Parses one line of the CSV file containing item details into a {@code Item} in an
     *         {@code Event} in the given event list.
     *
     * @param events the given event list.
     * @param fields the fields in the line of the CSV file being parsed.
     * @throws IOException if line cannot be parsed successfully.
     */
    private void parseItemFileLine(EventList events, String[] fields) throws IOException {
        try {
            String itemName = fields[0].trim();
            boolean isPresent = getIsMarked(fields[1].trim());
            String eventName = fields[2].trim();
            boolean isLoaded = events.addItemToEvent(itemName, isPresent, eventName);
            logOnUnsuccessfulLoad(isLoaded);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            logger.log(WARNING, "File line cannot be parsed, item not loaded");
        }
    }

    /**
     * Returns a list of fields for each line of a {@code .csv} file at a given file path.
     *
     * @param filePath the file path of the {@code .csv} file.
     * @return a list of fields for each line of the file at filePath.
     * @throws IOException if the file at the specified file path could not be read.
     * @throws CsvException if the reader could not read the file.
     */
    private List<String[]> getFileLines(String filePath) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build();
        List<String[]> lines = reader.readAll();
        reader.close();
        return lines;
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

    /**
     * Logs a warning message if a file entry was not loaded.
     *
     * @param isLoaded {@code true} if the entry was loaded, {@code false} otherwise.
     */
    private void logOnUnsuccessfulLoad(boolean isLoaded) {
        if (!isLoaded) {
            logger.log(WARNING, "Associated event not found, entry not loaded");
        }
    }
}
