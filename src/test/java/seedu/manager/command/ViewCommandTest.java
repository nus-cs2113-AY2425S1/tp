package seedu.manager.command;

import org.junit.jupiter.api.Test;
import seedu.manager.event.Event;
import seedu.manager.event.EventList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewCommandTest {
    @Test
    public void viewParticipant_success() {
        EventList eventList = new EventList();

        eventList.addEvent("Event 1", "2024-10-10 10:00", "Venue A");
        eventList.addParticipantToEvent("Tom", "Event 1");

        Event event = eventList.getEvent(0);
        assertEquals("Tom", event.getParticipantList().get(0));
        assertEquals(1, event.getParticipantCount());
    }
}
