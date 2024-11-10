package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;
import seedu.manager.exception.DuplicateDataException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    private EventList eventList;
    private DateTimeFormatter formatter;

    @BeforeEach
    public void testSetUp() {
        eventList = new EventList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Test
    public void add_event_success() {
        eventList.addEvent("Event 1",
                LocalDateTime.parse("2024-10-20 21:00", formatter),
                "Venue A", Priority.HIGH);
        assertEquals(1, eventList.getListSize());
    }

    @Test
    public void add_twoParticipant_success() {
        eventList.addEvent("Event 1",
                LocalDateTime.parse("2024-10-10 10:00", formatter),
                "Venue A", Priority.HIGH);
        eventList.addParticipantToEvent(
                "Tom",
                "example@gmail.com",
                "Event 1"
        );
        eventList.addParticipantToEvent(
                "Harry",
                "example@gmail.com",
                "Event 1"
        );


        assertEquals(2, eventList.getEvent(0).getParticipantCount());
    }

    @Test
    public void add_oneParticipantWrongly_success() {
        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-10 10:00", formatter),
                "Venue A", Priority.HIGH);
        eventList.addParticipantToEvent(
                "Tom",
                "example@gmail.com",
                "Event 1"
        );
        eventList.addParticipantToEvent(
                "Harry",
                "example@gmail.com",
                "Event 2"
        );

        assertEquals(1, eventList.getEvent(0).getParticipantCount());
    }

    @Test
    public void add_oneParticipantInvalidEvent_failure() {
        String expectedMessage = "Event not found!";

        AddCommand addCommand = new AddCommand("Tom", "example@gmail.com", "Event 1");
        addCommand.setData(eventList);
        addCommand.execute();

        assertEquals(expectedMessage, addCommand.getMessage());
    }

    //@@author jemehgoh
    @Test
    public void add_duplicateEvent_throwsException() {
        EventList eventList = new EventList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-10 10:00", formatter),
                "Venue A", Priority.HIGH);

        AddCommand addCommand = new AddCommand("Event 1", LocalDateTime.parse("2024-10-10 10:00",
                formatter), "Venue A", Priority.HIGH);
        addCommand.setData(eventList);

        assertThrows(DuplicateDataException.class, addCommand::execute);
    }

    @Test
    public void add_duplicateParticipant_throwsException() {
        EventList eventList = new EventList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-10 10:00", formatter),
                "Venue A", Priority.HIGH);
        eventList.addParticipantToEvent("John", "example1@gmail.com", "Event 1");

        AddCommand addCommand = new AddCommand("Event 1", LocalDateTime.parse("2024-10-10 10:00",
                formatter), "Venue A", Priority.HIGH);
        addCommand.setData(eventList);

        assertThrows(DuplicateDataException.class, addCommand::execute);
    }

    @Test
    public void add_oneItem_success() {
        String expectedMessage = "Item added successfully";
        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-25 16:00", formatter),
                "Venue 1", Priority.MEDIUM);

        AddCommand addCommand = new AddCommand("Foolscap paper", "Event 1");
        addCommand.setData(eventList);
        addCommand.execute();

        assertEquals(expectedMessage, addCommand.getMessage());
    }

    @Test
    public void add_oneItemInvalidEvent_failure() {
        String expectedMessage = "Event not found!";
        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-25 16:00", formatter),
                "Venue 1", Priority.MEDIUM);

        AddCommand addCommand = new AddCommand("Graph paper", "Event 2");
        addCommand.setData(eventList);
        addCommand.execute();

        assertEquals(expectedMessage, addCommand.getMessage());
    }

    @Test
    public void add_duplicateItem_throwsException() {
        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-25 16:00", formatter),
                "Venue 1", Priority.MEDIUM);
        eventList.addItemToEvent("Crepe paper", "Event 1");

        AddCommand addCommand = new AddCommand("Crepe paper", "Event 1");
        addCommand.setData(eventList);

        assertThrows(DuplicateDataException.class, addCommand::execute);
    }
}

