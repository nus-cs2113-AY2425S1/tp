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

//@@author KuanHsienn
/**
 * This class is responsible for parsing event data from a CSV file.
 * It handles the extraction of event, participant, and item information
 * and adds them to the provided EventList.
 */
public class FileParser {
    private static final Logger logger = Logger.getLogger(FileParser.class.getName());

    /**
     * Parses the specified CSV file and populates the given EventList with the loaded data.
     *
     * @param events   The EventList to load events into.
     * @param filePath The path to the CSV file to be parsed.
     * @throws IOException If there is an error reading from the file.
     */
    public void parseFile(EventList events, String filePath) throws IOException {
        try {
            logger.info("Loading data from file");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            List<String[]> lines = getFileLines(filePath);
            for (String[] line : lines) {
                parseLine(events, line, formatter);
            }
        } catch (IOException | CsvException exception) {
            throw new IOException("Error loading data from file: " + filePath +
                    ". New file will be created since it does not exists.");
        }
    }

    /**
     * Reads all lines from the specified CSV file and returns them as a list of String arrays.
     *
     * @param filePath The path to the CSV file to be read.
     * @return A list of String arrays, each representing a line in the CSV file.
     * @throws IOException If there is an error reading the file.
     * @throws CsvException If there is an error parsing the CSV data.
     */
    private List<String[]> getFileLines(String filePath) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build();
        List<String[]> lines = reader.readAll();
        reader.close();
        return lines;
    }

    /**
     * Parses a single line of CSV data and adds the corresponding event, participant, or item to the EventList.
     *
     * @param events   The EventList to populate.
     * @param fields   The fields of the line to parse.
     * @param formatter The DateTimeFormatter to use for parsing date and time.
     */
    private void parseLine(EventList events, String[] fields, DateTimeFormatter formatter) {
        String type = fields[0];
        try {
            switch (type) {
            case "EVENT":
                parseEventFileLine(events, fields, formatter);
                break;
            case "PARTICIPANT":
                parseParticipantFileLine(events, fields);
                break;
            case "ITEM":
                parseItemFileLine(events, fields);
                break;
            default:
                logger.warning("Unknown entry type in file");
            }
        } catch (Exception exception) {
            logger.warning("File line cannot be parsed, entry not loaded");
        }
    }

    /**
     * Parses a line corresponding to an event and adds it to the EventList.
     *
     * @param events   The EventList to populate.
     * @param fields   The fields of the event to parse.
     * @param formatter The DateTimeFormatter to use for parsing date and time.
     * @throws IOException If there is an error adding the event to the list.
     */
    private void parseEventFileLine(EventList events, String[] fields, DateTimeFormatter formatter) throws IOException {
        try {
            String eventName = fields[1].trim();
            LocalDateTime time = LocalDateTime.parse(fields[2].trim(), formatter);
            String venue = fields[3].trim();
            Priority priority = Priority.valueOf(fields[4].trim().toUpperCase());
            boolean isDone = getIsMarked(fields[5].trim());
            events.addEvent(eventName, time, venue, priority, isDone);
        } catch (DateTimeParseException | IndexOutOfBoundsException | NullPointerException exception) {
            logger.warning("File line cannot be parsed, event not loaded");
        }
    }

    /**
     * Parses a line corresponding to a participant and adds it to the associated event in the EventList.
     *
     * @param events The EventList to populate.
     * @param fields The fields of the participant to parse.
     * @throws IOException If there is an error adding the participant to the event.
     */
    private void parseParticipantFileLine(EventList events, String[] fields) throws IOException {
        try {
            String participantName = fields[1].trim();
            String email = fields[2].trim();
            String eventName = fields[3].trim();
            boolean isPresent = getIsMarked(fields[4].trim());
            boolean isLoaded = events.addParticipantToEvent(participantName, email, isPresent, eventName);
            eventUnsuccessfulLoad(isLoaded);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            logger.warning("File line cannot be parsed, participant not loaded");
        }
    }

    /**
     * Parses a line corresponding to an item and adds it to the associated event in the EventList.
     *
     * @param events The EventList to populate.
     * @param fields The fields of the item to parse.
     * @throws IOException If there is an error adding the item to the event.
     */
    private void parseItemFileLine(EventList events, String[] fields) throws IOException {
        try {
            String itemName = fields[1].trim();
            String eventName = fields[2].trim();
            boolean isPresent = getIsMarked(fields[3].trim());
            boolean isLoaded = events.addItemToEvent(itemName, isPresent, eventName);
            eventUnsuccessfulLoad(isLoaded);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            logger.warning("File line cannot be parsed, item not loaded");
        }
    }

    /**
     * Converts a mark status string to a boolean value.
     *
     * @param markStatus The mark status string, expected to be "Y" or "N".
     * @return true if mark status is "Y"; false if it is "N".
     */
    private boolean getIsMarked(String markStatus) {
        if (markStatus.equalsIgnoreCase("Y")) {
            return true;
        } else if (markStatus.equalsIgnoreCase("N")) {
            return false;
        } else {
            logger.warning("Cannot parse mark status, setting to false");
            return false;
        }
    }

    /**
     * Logs a warning if an event associated with a participant or item could not be loaded.
     *
     * @param isLoaded Indicates whether the loading was successful.
     */
    private void eventUnsuccessfulLoad(boolean isLoaded) {
        if (!isLoaded) {
            logger.warning("Associated event not found, entry not loaded");
        }
    }
}
