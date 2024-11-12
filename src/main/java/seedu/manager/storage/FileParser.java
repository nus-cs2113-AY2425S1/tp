package seedu.manager.storage;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

//@@author KuanHsienn
/**
 * This class is responsible for parsing event data from a CSV file.
 * It handles the extraction of event, participant, and item information
 * and adds them to the provided EventList.
 */
public class FileParser {
    private final Logger logger;

    /**
     * Constructs a new FileParser.
     */
    public FileParser(){
        logger = Logger.getLogger(FileParser.class.getName());
        logger.setUseParentHandlers(false);
    }

    /**
     * Parses the specified CSV file and populates the given EventList with the loaded data.
     *
     * @param events   The EventList to load events into.
     * @param filePath The path to the CSV file to be parsed.
     * @throws IOException If there is an error reading from the file.
     */
    public void parseFile(EventList events, String filePath) throws IOException {
        try {
            logInfo("Loading data from file");
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
    private void parseLine(EventList events, String[] fields, DateTimeFormatter formatter) throws IOException {
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
                logWarning("Unknown entry type in file");
            }
        } catch (Exception exception) {
            logWarning("File line cannot be parsed, entry not loaded");
        }
    }

    /**
     * Parses a line corresponding to an event and adds it to the EventList.
     *
     * @param events   The EventList to populate.
     * @param fields   The fields of the event to parse.
     * @param formatter The DateTimeFormatter to use for parsing date and time.
     * @throws IOException If there is an error adding the event to the list, or if the log file cannot be written to.
     */
    private void parseEventFileLine(EventList events, String[] fields, DateTimeFormatter formatter) throws IOException {
        try {
            String eventName = fields[1].trim();
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateTimeFormat.setLenient(false);
            Date parsedDateTime = dateTimeFormat.parse(fields[2].trim());
            LocalDateTime time = LocalDateTime.ofInstant(parsedDateTime.toInstant(), ZoneId.systemDefault());
            String venue = fields[3].trim();
            Priority priority = Priority.valueOf(fields[4].trim().toUpperCase());
            boolean isDone = getIsMarked(fields[5].trim());
            events.addEvent(eventName, time, venue, priority, isDone);
        } catch (ParseException | IndexOutOfBoundsException
                 | NullPointerException | IllegalArgumentException exception) {
            logWarning("File line cannot be parsed, event not loaded");
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
            String isLoaded = events.addParticipantToEvent(participantName, email, isPresent, eventName);
            eventUnsuccessfulLoad(isLoaded);
        } catch (IndexOutOfBoundsException | NullPointerException | IllegalArgumentException exception) {
            logWarning("File line cannot be parsed, participant not loaded");
        }
    }

    /**
     * Parses a line corresponding to an item and adds it to the associated event in the EventList.
     *
     * @param events The EventList to populate.
     * @param fields The fields of the item to parse.
     * @throws IOException If there is an error adding the item to the event, or if
     *         the log file cannot be written to.
     */
    private void parseItemFileLine(EventList events, String[] fields) throws IOException {
        try {
            String itemName = fields[1].trim();
            String eventName = fields[2].trim();
            boolean isPresent = getIsMarked(fields[3].trim());
            String isLoaded = events.addItemToEvent(itemName, isPresent, eventName);
            eventUnsuccessfulLoad(isLoaded);
        } catch (IndexOutOfBoundsException | NullPointerException | IllegalArgumentException exception) {
            logWarning("File line cannot be parsed, item not loaded");
        }
    }

    /**
     * Converts a mark status string to a boolean value.
     *
     * @param markStatus The mark status string, expected to be "Y" or "N".
     * @return true if mark status is "Y"; false if it is "N".
     * @throws IOException if the log file cannot be written to.
     */
    private boolean getIsMarked(String markStatus) throws IOException {
        if (markStatus.equalsIgnoreCase("Y")) {
            return true;
        } else if (markStatus.equalsIgnoreCase("N")) {
            return false;
        } else {
            logWarning("Cannot parse mark status, setting to false");
            throw new IllegalArgumentException();
        }
    }

    /**
     * Logs a warning if an event associated with a participant or item could not be loaded.
     *
     * @param isLoaded Indicates whether the loading was successful.
     * @throws IOException if the log file cannot be written to.
     */
    private void eventUnsuccessfulLoad(String isLoaded) throws IOException {
        if (isLoaded.equalsIgnoreCase("")) {
            logWarning("Associated event not found, entry not loaded");
        }
    }

    /**
     * Logs an info message to a log file.
     *
     * @param message the given warning message.
     * @throws IOException if the log file cannot be written to.
     */
    private void logInfo(String message) throws IOException {
        FileHandler handler = new FileHandler("logs.txt", true);
        logger.addHandler(handler);
        logger.info(message);
        handler.close();
    }

    /**
     * Logs a warning message to a log file.
     *
     * @param message the given warning message.
     * @throws IOException if the log file cannot be written to.
     */
    private void logWarning(String message) throws IOException {
        FileHandler handler = new FileHandler("logs.txt", true);
        logger.addHandler(handler);
        logger.warning(message);
        handler.close();
    }
}
