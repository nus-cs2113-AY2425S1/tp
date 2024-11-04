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
public class FileParser {
    private static final Logger logger = Logger.getLogger(FileParser.class.getName());

    public void parseFile(EventList events, String filePath) throws IOException {
        try {
            logger.info("Loading data from file");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            List<String[]> lines = getFileLines(filePath);
            for (String[] line : lines) {
                parseLine(events, line, formatter);
            }
        } catch (IOException | CsvException exception) {
            throw new IOException("Error loading data from file: " + filePath + ".");
        }
    }

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

    private void parseParticipantFileLine(EventList events, String[] fields) throws IOException {
        try {
            String participantName = fields[1].trim();
            String number = fields[2].trim();
            String email = fields[3].trim();
            String eventName = fields[4].trim();
            boolean isPresent = getIsMarked(fields[5].trim());
            boolean isLoaded = events.addParticipantToEvent(participantName, number, email, isPresent, eventName);
            eventUnsuccessfulLoad(isLoaded);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            logger.warning("File line cannot be parsed, participant not loaded");
        }
    }

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

    private List<String[]> getFileLines(String filePath) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).build();
        List<String[]> lines = reader.readAll();
        reader.close();
        return lines;
    }

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

    private void eventUnsuccessfulLoad(boolean isLoaded) {
        if (!isLoaded) {
            logger.warning("Associated event not found, entry not loaded");
        }
    }
}
