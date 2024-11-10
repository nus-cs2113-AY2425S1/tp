package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditEventCommandTest {
    private EventList eventList;
    private DateTimeFormatter formatter;

    @BeforeEach
    public void testSetUp() {
        eventList = new EventList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Test
    public void edit_event_success() {
        eventList.addEvent(
                "Event 1",
                LocalDateTime.parse("2024-10-20 21:00", formatter),
                "Venue A",
                Priority.HIGH
        );
        eventList.addParticipantToEvent(
                "Tom",
                "example@gmail.com",
                "Event 1"
        );

        EditEventCommand editEventCommand = new EditEventCommand(
                "Event 1",
                "newEvent 1",
                LocalDateTime.parse("2024-10-31 00:00", formatter),
                "new_Venue",
                Priority.LOW
        );

        editEventCommand.setData(eventList);
        editEventCommand.execute();

        assertEquals("newEvent 1", eventList.getEvent(0).getEventName());
        assertEquals(LocalDateTime.parse("2024-10-31 00:00", formatter), eventList.getEvent(0).getEventTime());
        assertEquals("new_Venue", eventList.getEvent(0).getEventVenue());
        assertEquals(Priority.LOW, eventList.getEvent(0).getEventPriority());

    }

    @Test
    public void editEvent_invalidEvent_failure() {
        eventList.addEvent(
                "Event 1",
                LocalDateTime.parse("2024-10-20 21:00", formatter),
                "Venue A",
                Priority.HIGH
        );
        eventList.addParticipantToEvent(
                "Tom",
                "example@gmail.com",
                "Event 1"
        );
        String expectedMessage = "Event not found!";
        EditEventCommand editEventCommand = new EditEventCommand(
                "Non-Existent Event",
                "newEvent 1",
                LocalDateTime.parse("2024-10-31 00:00", formatter),
                "new_Venue",
                Priority.LOW
        );
        editEventCommand.setData(eventList);
        editEventCommand.execute();

        assertEquals(expectedMessage, editEventCommand.getMessage());
    }
}
