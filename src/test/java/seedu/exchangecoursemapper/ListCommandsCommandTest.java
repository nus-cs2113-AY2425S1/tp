package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.exchangecoursemapper.command.ListCommandsCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListCommandsCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect system output to the ByteArrayOutputStream to capture it for testing
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testListCommands() {
        // Test the ListCommandsCommand directly
        ListCommandsCommand listCommandsCommand = new ListCommandsCommand();
        listCommandsCommand.execute("");

        // Check if the output contains the expected list of commands
        String expectedCommand1 = "filter <subject code>";
        String expectedCommand2 = "set <SCHOOL_NAME>";
        String expectedCommand3 = "list schools";
        String expectedCommand4 = "add <NUS_COURSE_CODE> /pu <NAME_OF_PU> /coursepu <PU_COURSE_CODE>";

        assertTrue(outputStreamCaptor.toString().contains(expectedCommand1));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand2));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand3));
        assertTrue(outputStreamCaptor.toString().contains(expectedCommand4));
    }
}
