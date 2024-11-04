package seedu.manager.storage;

import com.opencsv.CSVWriter;
import seedu.manager.event.EventList;
import seedu.manager.event.Event;
import seedu.manager.item.Item;
import seedu.manager.item.Participant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

//@@author KuanHsienn
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        if (!isTestEnvironment()) {
            assert isValidFilePath(filePath) : "Invalid file path: " + filePath;
        }
        this.filePath = filePath;
    }

    public void saveInfo(EventList events) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
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
            throw new IOException("Error saving data to file: " + filePath);
        }
    }

    public void loadInfo(EventList events) throws IOException {
        FileParser parser = new FileParser();
        parser.parseFile(events, filePath);
    }

    private boolean isValidFilePath(String filePath) {
        String expectedPath = "data.csv";
        return filePath.equals(expectedPath);
    }

    private boolean isTestEnvironment() {
        return "true".equals(System.getProperty("test.environment"));
    }

    private static String[] getEventFields(Event event) {
        List<String> fieldsList = List.of("EVENT", event.getEventName(), event.getEventTimeString(),
                event.getEventVenue(), event.getEventPriorityString(), event.markIfDone());
        return fieldsList.toArray(new String[6]);
    }

    private String[] getParticipantFields(Participant participant, Event event) {
        List<String> fieldsList = List.of("PARTICIPANT", participant.getName(), participant.getNumber(),
                participant.getEmail(), event.getEventName(), participant.markFileLineIfPresent());
        return fieldsList.toArray(new String[6]);
    }

    private String[] getItemFields(Event event, Item item) {
        List<String> fieldsList = List.of("ITEM", item.getName(), event.getEventName(), item.markFileLineIfPresent());
        return fieldsList.toArray(new String[4]);
    }
}
