package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkItemCommandTest {
    private EventList eventList;
    private DateTimeFormatter formatter;

    @BeforeEach
    public void testSetUp() {
        eventList = new EventList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        eventList.addEvent("Event 1", LocalDateTime.parse("2024-10-10 16:00", formatter),
                "Venue 1", Priority.HIGH);
        eventList.addItemToEvent("Worksheet", "Event 1");
    }

    @Test
    public void execute_validItem_success() {
        String expectedMessage = "Item accounted for.";

        MarkItemCommand command = new MarkItemCommand("Worksheet", "Event 1", true);
        command.setData(eventList);
        command.execute();

        assertEquals(expectedMessage, command.getMessage());
    }

    @Test
    public void execute_invalidItem_failure() {
        String expectedMessage = "Item not found!";

        MarkItemCommand command = new MarkItemCommand("Cutter", "Event 1", false);
        command.setData(eventList);
        command.execute();

        assertEquals(expectedMessage, command.getMessage());
    }

    @Test
    public void execute_invalidEvent_failure() {
        String expectedMessage = "Event not found!";

        MarkItemCommand command = new MarkItemCommand("Lined paper", "Event 2", true);
        command.setData(eventList);
        command.execute();

        assertEquals(expectedMessage, command.getMessage());
    }
}
