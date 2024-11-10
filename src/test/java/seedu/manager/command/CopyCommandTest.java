package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@@author LTK-1606
public class CopyCommandTest {
    EventList eventList = new EventList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    public void setUp(){
        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-23 21:05", formatter),
                "Venue B", Priority.HIGH);
        eventList.addEvent("Event 2", LocalDateTime.parse("2023-10-23 21:00", formatter),
                "Venue A", Priority.HIGH);
        assertEquals(2, eventList.getListSize());
        eventList.addParticipantToEvent("John Doe", "example1@gmail.com", "Event 1");
        eventList.addParticipantToEvent("Jane Doe", "example2@gmail.com","Event 1");
        eventList.addParticipantToEvent("Peter Parker", "example3@gmail.com","Event 1");
    }

    @Test
    public void execute_copyCommand_success(){
        CopyCommand copyCommand = new CopyCommand("Event 1", "Event 2");

        copyCommand.setData(eventList);
        copyCommand.execute();

        String expectedMessage = "Participant list copied over successfully!";

        assertEquals(expectedMessage, copyCommand.getMessage());
        assertFalse(copyCommand.getCanExit());
    }

    @Test
    public void execute_copyCommand_eventNotFound(){
        CopyCommand copyCommand = new CopyCommand("Event 2", "Event 4");

        copyCommand.setData(eventList);
        copyCommand.execute();

        String expectedMessage = "Event(s) not found!";

        assertEquals(expectedMessage, copyCommand.getMessage());
        assertFalse(copyCommand.getCanExit());
    }

    @Test
    public void execute_copyCommand_participantListEmpty(){
        CopyCommand copyCommand = new CopyCommand("Event 2", "Event 1");

        copyCommand.setData(eventList);
        copyCommand.execute();

        String expectedMessage = "Participant list is empty!";

        assertEquals(expectedMessage, copyCommand.getMessage());
        assertFalse(copyCommand.getCanExit());
    }
}
