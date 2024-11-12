package seedu.manager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.manager.event.EventList;
import seedu.manager.parser.Parser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//@@author glenn-chew
public class ViewCommandTest {

    private EventList eventList;
    private Command viewCommand;

    @BeforeEach
    public void setUp() throws IOException {
        Command command;
        eventList = new EventList();

        command = new Parser().parseCommand("add -e Event 1 -t 2025-10-10 10:00 -v Venue A -u high");
        command.setData(eventList);
        command.execute();

        command = new Parser().parseCommand("add -p Tom -email example@gmail.com -e Event 1");
        command.setData(eventList);
        command.execute();

        command = new Parser().parseCommand("add -m Plastic chair -e Event 1");
        command.setData(eventList);
        command.execute();
    }

    @Test
    public void execute_twoEvents_success() throws IOException {
        String expectedMessage = "There is 1 participant in Event 1! Here is your participant:\n"
                + "1. Name: Tom / Email: example@gmail.com [ ]\n";

        viewCommand = new Parser().parseCommand("view -e Event 1 -y participant");
        viewCommand.setData(eventList);
        viewCommand.execute();

        assertEquals(expectedMessage, viewCommand.getMessage());
        assertFalse(viewCommand.getCanExit());
    }

    //@@author jemehgoh
    @Test
    public void execute_invalidEvent_failure() throws IOException {
        String expectedMessage = "Event not found!";

        viewCommand = new Parser().parseCommand("view -e Event 2 -y participant");
        viewCommand.setData(eventList);
        viewCommand.execute();

        assertEquals(expectedMessage, viewCommand.getMessage());
    }

    @Test
    public void execute_viewItems_success() throws IOException {
        String expectedMessage = "There is 1 item in Event 1! Here is your item:\n"
                + "1. Plastic chair [ ]\n";

        viewCommand = new Parser().parseCommand("view -e Event 1 -y item");
        viewCommand.setData(eventList);
        viewCommand.execute();

        assertEquals(expectedMessage, viewCommand.getMessage());
    }
}
