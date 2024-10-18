package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.event.EventList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkCommandTest {
    private EventList eventList;

    @BeforeEach
    public void testSetUp() {
        eventList = new EventList();
        eventList.addEvent("Event 1", "2024-10-10 1600", "Venue 1");
    }

    @Test
    public void execute_eventPresent_success() {
        MarkCommand command = new MarkCommand("Event 1");
        command.setData(eventList);
        command.execute();
        assertEquals("Event marked as done", command.getMessage());
    }

    @Test
    public void execute_eventAbsent_failure() {
        MarkCommand command = new MarkCommand("Event 2");
        command.setData(eventList);
        command.execute();
        assertEquals("Event not found!", command.getMessage());
    }
}
