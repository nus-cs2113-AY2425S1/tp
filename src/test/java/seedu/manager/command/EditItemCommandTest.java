package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.enumeration.Priority;
import seedu.manager.event.EventList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditItemCommandTest {
    private EventList eventList;
    private DateTimeFormatter formatter;

    @BeforeEach
    public void testSetUp() {
        eventList = new EventList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Test
    public void edit_item_success() {
        eventList.addEvent(
                "Event 1",
                LocalDateTime.parse("2024-10-20 21:00", formatter),
                "Venue A",
                Priority.HIGH
        );
        eventList.addItemToEvent(
                "table",
                "Event 1"
        );
        eventList.addItemToEvent(
                "Foolscap",
                "Event 1"
        );

        EditItemCommand editItemCommand = new EditItemCommand(
                "Foolscap",
                "origami paper",
                "Event 1"
        );
        editItemCommand.setData(eventList);
        editItemCommand.execute();

        assertEquals("origami paper", eventList.getEvent(0).getItemList().get(1).getName());
    }

    @Test
    public void editItem_invalidEvent_failure() {
        eventList.addEvent(
                "Event 1",
                LocalDateTime.parse("2024-10-20 21:00", formatter),
                "Venue A",
                Priority.HIGH
        );
        eventList.addItemToEvent(
                "table",
                "Event 1"
        );
        eventList.addItemToEvent(
                "Foolscap",
                "Event 1"
        );

        EditItemCommand editItemCommand = new EditItemCommand(
                "Foolscap",
                "origami paper",
                "Event 2"
        );
        editItemCommand.setData(eventList);
        editItemCommand.execute();

        String expectedMessage = "Event/Item not found!";
        assertEquals(expectedMessage, editItemCommand.getMessage());
    }

    @Test
    public void editItem_invalidItem_failure() {
        eventList.addEvent(
                "Event 1",
                LocalDateTime.parse("2024-10-20 21:00", formatter),
                "Venue A",
                Priority.HIGH
        );
        eventList.addItemToEvent(
                "table",
                "Event 1"
        );
        eventList.addItemToEvent(
                "Foolscap",
                "Event 1"
        );

        EditItemCommand editItemCommand = new EditItemCommand(
                "chair",
                "origami paper",
                "Event 1"
        );
        editItemCommand.setData(eventList);
        editItemCommand.execute();

        String expectedMessage = "Event/Item not found!";
        assertEquals(expectedMessage, editItemCommand.getMessage());
    }
}
