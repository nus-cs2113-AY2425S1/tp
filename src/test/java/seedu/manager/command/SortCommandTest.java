package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.event.EventList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SortCommandTest {
    EventList eventList = new EventList();

    @BeforeEach
    public void setUp(){
        eventList.addEvent("C-Event", "2024-10-23 21:00", "Venue C");
        eventList.addEvent("B-Event", "2024-10-23 21:00", "Venue B");
        eventList.addEvent("A-Event", "2024-10-23 21:00", "Venue A");
        assertEquals(3, eventList.getListSize());
    }

    @Test
    public void execute_sortByName_success() {

        SortCommand sortCommand = new SortCommand("name");

        sortCommand.setData(eventList);
        sortCommand.execute();

        String expectedMessage = """
                Successfully sort events by name!
                1. Event name: A-Event / Event time: 2024-10-23 21:00 / Event venue: Venue A / Done: N
                2. Event name: B-Event / Event time: 2024-10-23 21:00 / Event venue: Venue B / Done: N
                3. Event name: C-Event / Event time: 2024-10-23 21:00 / Event venue: Venue C / Done: N
                """;

        assertEquals(expectedMessage, sortCommand.getMessage());
        assertFalse(sortCommand.getCanExit());
    }
}
