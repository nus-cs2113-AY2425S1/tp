package seedu.manager.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.command.Command;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;
import seedu.manager.exception.DuplicateDataException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserIntegrationTest {
    private EventList events;
    private Parser parser;
    private DateTimeFormatter formatter;

    @BeforeEach
    void setUp() {
        events = new EventList();
        parser = new Parser();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        events.addEvent("Event 1", LocalDateTime.parse("2024-10-21 16:00", formatter),
                "Venue 1", Priority.HIGH, false);
    }

    @Test
    void parseCommand_addCommandAddEvent_success() {
        String commandString = "add -e Event 2 -t 2024-10-21 16:00 -v Venue 1 -u HIGH";
        Command command = parser.parseCommand(commandString);
        command.setData(events);
        command.execute();

        assertEquals(2, events.getListSize());
    }

    @Test
    void parseCommand_addCommandDuplicateEvent_throwsException() {
        String commandString = "add -e Event 1 -t 2024-10-21 16:00 -v Venue 1 -u HIGH";
        Command command = parser.parseCommand(commandString);
        command.setData(events);

        assertThrows(DuplicateDataException.class, () -> {
            command.execute();});
    }

    @Test
    void parseCommand_addCommandAddParticipant_success() {
        String commandString = "add -p John Doe -n 92138961 -email johndoe@gmail.com -e Event 1";
        Command command = parser.parseCommand(commandString);
        command.setData(events);
        command.execute();

        assertEquals(1, events.getEventByName("Event 1").get().getParticipantCount());
    }

    @Test
    void parseCommand_removeCommandRemoveEvent_success() {
        String commandString = "remove -e Event 1";
        Command command = parser.parseCommand(commandString);
        command.setData(events);
        command.execute();

        assertEquals(0, events.getListSize());
    }

    @Test
    void parseCommand_markCommandMarkEvent_success() {
        String commandString = "mark -e Event 1 -s done";
        Command command = parser.parseCommand(commandString);
        command.setData(events);
        command.execute();

        assertEquals("Y", events.getEventByName("Event 1").get().markIfDone());
    }
}